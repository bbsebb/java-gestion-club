package fr.hoenheimsports.gestionclub.service;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import fr.hoenheimsports.gestionclub.entity.*;
import fr.hoenheimsports.gestionclub.repository.*;
import fr.hoenheimsports.gestionclub.service.util.ExtractInfoTeam;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.input.BOMInputStream;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@Data
@RequiredArgsConstructor
@Log4j2
public class CSVImportImpl implements CSVImport {
    final private GameService GameService;
    final private CompetitionService competitionService;
    final private PoolService poolService;
    final private RefereeService refereeService;
    final private HalleService halleService;
    final private TeamService teamService;
    final private ClubService clubService;
    final private CategoryService categoryService;
    final private ExtractInfoTeam extractorHome;
    final private ExtractInfoTeam extractorVisiting;


    private final static String[] HEADER = {
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


    private List<String[]> CSVLines;


    public void setResource(String resourceString) {
        Resource resource = new ClassPathResource(resourceString);
        try (InputStreamReader reader = new InputStreamReader(new BOMInputStream(resource.getInputStream()))) {
            // Création du CSV READER
            CSVReader csvReader = new CSVReaderBuilder(reader).withCSVParser(new CSVParserBuilder().withSeparator(';').build()).build();
            this.CSVLines = csvReader.readAll();
        } catch (FileNotFoundException fnfe) {
            throw new RuntimeException(fnfe);
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        } catch (CsvException e) {
            throw new RuntimeException(e);
        }
    }

    public void run() throws Exception {
        if (this.CSVLines == null || this.CSVLines.isEmpty()) {
            throw new Exception();
        }
        //Lecture entete
        String[] header = this.CSVLines.remove(0);

        //Création d'une map nom entete - index pour retrouver facilement l'index
        Map<String, Integer> headerMap = new Hashtable<>();
        for (int i = 0; i < HEADER.length; i++) {
            headerMap.put(header[i], i);
        }
        //On verifie si l'header est correct
        if (Arrays.equals(header, HEADER)) {
            int i = 0;
            for (String[] line : this.CSVLines) {

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


                Game game = this.GameService.createOrUpdate(
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
            }
        }
    }
}
