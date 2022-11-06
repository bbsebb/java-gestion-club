package fr.hoenheimsports.gestionclub.service;

import fr.hoenheimsports.gestionclub.entity.*;
import fr.hoenheimsports.gestionclub.exception.csvimportexception.CsvDataException;
import fr.hoenheimsports.gestionclub.exception.csvimportexception.CsvException;
import fr.hoenheimsports.gestionclub.service.util.ExtractInfoTeam;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionException;

import javax.validation.ConstraintViolationException;
import java.util.Map;

@Service
@Log4j2
public class CSVImportGamePlayedImpl extends AbstractCSVImport {
    protected static String[] HEADER = {
            "semaine",
            "num poule",
            "competition",
            "poule",
            "J",
            "le",
            "horaire",
            "club rec",
            "club vis",
            "club hote",
            "arb1 designe",
            "arb2 designe",
            "observateur",
            "delegue",
            "code renc",
            "nom salle",
            "adresse salle",
            "CP",
            "Ville",
            "colle",
            "Coul. Rec",
            "Coul. Gard. Rec",
            "Coul. Vis",
            "Coul. Gard. Vis",
            "Ent. Rec",
            "Tel Ent. Rec",
            "Corresp. Rec",
            "Tel Corresp. Rec",
            "Ent. Vis",
            "Tel Ent. Vis",
            "Corresp. Vis",
            "Tel Corresp. Vis",
            "Num rec",
            "Num vis"
    };


    public CSVImportGamePlayedImpl(GameService GameService, CompetitionService competitionService, PoolService poolService, RefereeService refereeService, HalleService halleService, TeamService teamService, ClubService clubService, CategoryService categoryService, ExtractInfoTeam extractorHome, ExtractInfoTeam extractorVisiting) {
        super(HEADER, GameService, competitionService, poolService, refereeService, halleService, teamService, clubService, categoryService, extractorHome, extractorVisiting);
    }


    @Override
    protected void extractLine(String[] line,Map<String, Integer> headerMap) throws CsvException {
        try {
            Competition competition = this.competitionService.createOrUpdate(line[headerMap.get("competition")]);
            Pool pool = this.poolService.createOrUpdate(line[headerMap.get("num poule")], line[headerMap.get("poule")], competition);

            Address address = this.halleService.addressCreate(line[headerMap.get("adresse salle")], line[headerMap.get("CP")], line[headerMap.get("Ville")]);
            Halle halle = this.halleService.createOrUpdate(line[headerMap.get("nom salle")], address);

            Referee referee1 = this.refereeService.createOrUpdate(line[headerMap.get("arb1 designe")]);
            Referee referee2 = this.refereeService.createOrUpdate(line[headerMap.get("arb2 designe")]);

            Score score = new Score(0, 0);
            FDME fdme = new FDME(null);

            extractorHome.setCsvNumPool(line[headerMap.get("num poule")]);
            extractorHome.setCsvTeamName(line[headerMap.get("club rec")]);
            extractorHome.extract();

            extractorVisiting.setCsvNumPool(line[headerMap.get("num poule")]);
            extractorVisiting.setCsvTeamName(line[headerMap.get("club vis")]);
            extractorVisiting.extract();

            Club clubHome = this.clubService.save(line[headerMap.get("Num rec")], extractorHome.getClubName());
            Club clubVisiting = this.clubService.save(line[headerMap.get("Num vis")], extractorVisiting.getClubName());

            Category categoryHome = this.categoryService.createOrUpdate(extractorHome.getCategoryShortName(), extractorHome.getCategoryName());
            Category categoryVisiting = this.categoryService.createOrUpdate(extractorVisiting.getCategoryShortName(), extractorVisiting.getCategoryName());

            Team teamHome = this.teamService.createOrUpdate(clubHome, categoryHome, extractorHome.getGender(), extractorHome.getTeamNumber());
            Team teamVisiting = this.teamService.createOrUpdate(clubVisiting, categoryVisiting, extractorVisiting.getGender(), extractorVisiting.getTeamNumber());

            this.GameService.createOrUpdate(
                    line[headerMap.get("code renc")],
                    halle,
                    fdme,
                    score,
                    line[headerMap.get("J")],
                    line[headerMap.get("le")],
                    line[headerMap.get("horaire")],
                    pool,
                    referee1,
                    referee2,
                    teamHome,
                    teamVisiting,
                    false
            );
        } catch (TransactionException te) {
            if (te.getRootCause() instanceof ConstraintViolationException cve) {
                String errorMsg = "Incompatibilité des données du fichier CSV";
                if(cve.getConstraintViolations().stream().findFirst().isPresent()) {
                    errorMsg = cve.getConstraintViolations().stream().findFirst().get().getMessage();
                }
                CSVImportGamePlayedImpl.log.error(errorMsg);
                throw new CsvDataException(errorMsg, te);
            }
        } catch (DataIntegrityViolationException de) {
            String errorMsg = "Incompatibilité des données du fichier CSV avec la structure de la base de donnée";
            if(de.getRootCause() != null) {
                errorMsg = de.getRootCause().getLocalizedMessage();
            }
            CSVImportGamePlayedImpl.log.error(errorMsg, de);
            throw new CsvDataException("Incompatibilité des données du fichier CSV avec la structure de la base de donnée", de);
        }
    }

}
