package fr.hoenheimsports.gestionclub.service.csvimport;

import fr.hoenheimsports.gestionclub.model.*;
import fr.hoenheimsports.gestionclub.exception.csvimportexception.CsvDataException;
import fr.hoenheimsports.gestionclub.exception.csvimportexception.CsvException;
import fr.hoenheimsports.gestionclub.service.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionException;

import javax.validation.ConstraintViolationException;
import java.net.MalformedURLException;
import java.util.Map;

@Service("played")
@Log4j2
public class CSVImportGamePlayedImpl extends AbstractCSVImport {
    protected static final String[] HEADER = {
            "semaine",
            "num poule",
            "competition",
            "poule",
            "J",
            "le",
            "horaire",
            "club rec",
            "club vis",
            "sc rec",
            "sc vis",
            "fdme rec",
            "fdme vis",
            "pen. rec",
            "pen. vis",
            "forf. rec",
            "forf. vis",
            "arb1 designe",
            "arb2 designe",
            "arb1 sifle",
            "arb2 sifle",
            "secretaire",
            "chronometreur",
            "observateur",
            "delegue",
            "resp salle",
            "tuteur table",
            "code renc",
            "Num rec",
            "Num vis",
            "Etat",
            "Forfait",
            "Penalite",
            "FDME",
            "Date Arrivee"
    };


    public CSVImportGamePlayedImpl(fr.hoenheimsports.gestionclub.service.GameService GameService, CompetitionService competitionService, PoolService poolService, ContributorService contributorService, HalleService halleService, TeamService teamService, ClubService clubService, CategoryService categoryService, InfoTeamExtract extractorHome, InfoTeamExtract extractorVisiting) {
        super(HEADER, GameService, competitionService, poolService, contributorService, halleService, teamService, clubService, categoryService, extractorHome, extractorVisiting);
    }


    @Override
    protected void extractLine(String[] line, Map<String, Integer> headerMap) throws CsvException {
        try {

            Pool pool = this.poolService.createOrUpdate(line[headerMap.get("num poule")], line[headerMap.get("poule")]);
            Competition competition = this.competitionService.createOrUpdate(line[headerMap.get("competition")],pool);
            Contributor referee1 = this.contributorService.createOrUpdate(line[headerMap.get("arb1 designe")]);
            Contributor referee2 = this.contributorService.createOrUpdate(line[headerMap.get("arb2 designe")]);

            Contributor realReferee1 = this.contributorService.createOrUpdate(line[headerMap.get("arb1 sifle")]);
            Contributor realReferee2 = this.contributorService.createOrUpdate(line[headerMap.get("arb2 sifle")]);

            Score score = new Score(line[headerMap.get("fdme rec")], line[headerMap.get("fdme vis")]);
            FDME fdme = new FDME(line[headerMap.get("FDME")]);

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
                    true
            );
        } catch (TransactionException te) {
            if (te.getRootCause() instanceof ConstraintViolationException cve) {
                String errorMsg = "Incompatibilité des données du fichier CSV";
                if (cve.getConstraintViolations().stream().findFirst().isPresent()) {
                    errorMsg = cve.getConstraintViolations().stream().findFirst().get().getMessage();
                }
                CSVImportGamePlayedImpl.log.error(errorMsg);
                throw new CsvDataException(errorMsg, te);
            }
        } catch (DataIntegrityViolationException de) {
            String errorMsg = "Incompatibilité des données du fichier CSV avec la structure de la base de donnée";
            if (de.getRootCause() != null) {
                errorMsg = de.getRootCause().getLocalizedMessage();
            }
            CSVImportGamePlayedImpl.log.error(errorMsg, de);
            throw new CsvDataException("Incompatibilité des données du fichier CSV avec la structure de la base de donnée", de);
        } catch(NumberFormatException nfe) {
            throw new CsvDataException("La colonne fdme score du fichier CSV n'est pas compatible avec un nombre");
        } catch(MalformedURLException mfe) {
            throw new CsvDataException("La colonne FDME devrait être une url valide");
        }
    }

}
