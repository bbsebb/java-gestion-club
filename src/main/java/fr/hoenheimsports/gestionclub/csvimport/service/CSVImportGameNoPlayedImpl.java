package fr.hoenheimsports.gestionclub.csvimport.service;

import fr.hoenheimsports.gestionclub.game.model.*;
import fr.hoenheimsports.gestionclub.game.service.*;
import fr.hoenheimsports.gestionclub.csvimport.exception.CsvDataException;
import fr.hoenheimsports.gestionclub.csvimport.exception.CsvException;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionException;

import javax.validation.ConstraintViolationException;
import java.util.Map;

@Service("NoPlayed")
@Log4j2
public class CSVImportGameNoPlayedImpl extends AbstractCSVImport {
     static final String[] HEADER = {
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


    public CSVImportGameNoPlayedImpl(fr.hoenheimsports.gestionclub.game.service.GameService GameService, CompetitionService competitionService, PoolService poolService, ContributorService contributorService, HalleService halleService, TeamService teamService, ClubService clubService, CategoryService categoryService, InfoTeamExtract extractorHome, InfoTeamExtract extractorVisiting) {
        super(HEADER, GameService, competitionService, poolService, contributorService, halleService, teamService, clubService, categoryService, extractorHome, extractorVisiting);
    }


    @Override
    protected void extractLine(String[] line,Map<String, Integer> headerMap) throws CsvException {
        try {

            Pool pool = this.poolService.createOrUpdate(line[headerMap.get("num poule")], line[headerMap.get("poule")]);
            Competition competition = this.competitionService.createOrUpdate(line[headerMap.get("competition")],pool);
            Address address = this.halleService.addressCreate(line[headerMap.get("adresse salle")], line[headerMap.get("CP")], line[headerMap.get("Ville")]);
            Halle halle = this.halleService.createOrUpdate(line[headerMap.get("nom salle")], address,line[headerMap.get("colle")] );

            Contributor referee1 = this.contributorService.createOrUpdate(line[headerMap.get("arb1 designe")]);
            Contributor referee2 = this.contributorService.createOrUpdate(line[headerMap.get("arb2 designe")]);

            Score score = new Score(0, 0);
            FDME fdme = new FDME();

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


            Contributor coachHome = this.contributorService.createOrUpdate(line[headerMap.get("Ent. Rec")]);
            Contributor coachVisiting = this.contributorService.createOrUpdate(line[headerMap.get("Ent. Vis")]);

            Team teamHome = this.teamService.createOrUpdate(clubHome, categoryHome, extractorHome.getGender(), extractorHome.getTeamNumber(), TeamsColorExtract.extract(line[headerMap.get("Coul. Rec")],line[headerMap.get("Coul. Gard. Rec")]),coachHome);
            Team teamVisiting = this.teamService.createOrUpdate(clubVisiting, categoryVisiting, extractorVisiting.getGender(), extractorVisiting.getTeamNumber(),TeamsColorExtract.extract(line[headerMap.get("Coul. Vis")],line[headerMap.get("Coul. Gard. Vis")]),coachVisiting);

            this.GameService.createOrUpdate(
                    line[headerMap.get("code renc")],
                    halle,
                    fdme,
                    score,
                    line[headerMap.get("J")],
                    line[headerMap.get("le")],
                    line[headerMap.get("horaire")],
                    competition,
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
                CSVImportGameNoPlayedImpl.log.error(errorMsg);
                throw new CsvDataException(errorMsg, te);
            }
        } catch (DataIntegrityViolationException de) {
            String errorMsg = "Incompatibilité des données du fichier CSV avec la structure de la base de donnée";
            if(de.getRootCause() != null) {
                errorMsg = de.getRootCause().getLocalizedMessage();
            }
            CSVImportGameNoPlayedImpl.log.error(errorMsg, de);
            throw new CsvDataException("Incompatibilité des données du fichier CSV avec la structure de la base de donnée", de);
        }
    }

}
