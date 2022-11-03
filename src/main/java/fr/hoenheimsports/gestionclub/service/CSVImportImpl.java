package fr.hoenheimsports.gestionclub.service;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import fr.hoenheimsports.gestionclub.entity.*;
import fr.hoenheimsports.gestionclub.repository.*;
import fr.hoenheimsports.gestionclub.service.util.ExtractInfoTeam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.input.BOMInputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Data
@RequiredArgsConstructor
@Log4j2
public class CSVImportImpl implements CSVImport {

    final private GameRepository gameRepository;

    final private CompetitionRepository competitionRepository;

    final private PoolRepository poolRepository;

    final private RefereeRepository refereeRepository;

    final private HalleRepository halleRepository;

    final private TeamRepository teamRepository;

    final private ClubRepository clubRepository;

    final private CategoryRepository categoryRepository;
    final private ExtractInfoTeam extractorHome;
    final private  ExtractInfoTeam extractorVisiting;


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

    private Competition competitionSave(String name) {

        Optional<Competition> optionalCompetition = this.competitionRepository.findByName(name);
        return optionalCompetition.orElseGet(() -> this.competitionCreate(name));
    }

    private Competition competitionCreate(String name) {
        Competition newCompetition = new Competition();
        newCompetition.setName(name);
        return competitionRepository.save(newCompetition);
    }

    private Pool poolSave(String num, String name, Competition c) {

        Optional<Pool> optionalPool = this.poolRepository.findById(num);
        return optionalPool.orElseGet(() -> this.poolCreate(num, name, c));
    }

    private Pool poolCreate(String num, String name, Competition c) {
        Pool newPool = new Pool();
        newPool.setName(name);
        newPool.setNum(num);
        newPool.setCompetition(c);
        return this.poolRepository.save(newPool);
    }

    private Halle halleSave(String name, Address address) {

        Optional<Halle> optionalHalle = this.halleRepository.findByNameAndAddress(name, address);
        return optionalHalle.orElseGet(() -> this.halleCreate(name, address));
    }

    private Halle halleCreate(String name, Address address) {
        Halle newHalle = new Halle();
        newHalle.setName(name);
        newHalle.setAddress(address);
        return this.halleRepository.save(newHalle);
    }

    private Address addressCreate(String street, String cpStr, String city) {
        Address address = new Address();
        address.setStreet(street);
        int cp = 0;
        try {
            cp = Integer.parseInt(cpStr);
        } catch (NumberFormatException nfe) {
        }
        address.setCp(cp);
        address.setCity(city);
        return address;
    }

    private Referee refereeSave(String name) {

        Optional<Referee> optionalReferee = this.refereeRepository.findByName(name);
        return optionalReferee.orElseGet(() -> this.refereeCreate(name));
    }

    private Referee refereeCreate(String name) {
        Referee newReferee = new Referee();
        newReferee.setName(name);
        return this.refereeRepository.save(newReferee);
    }

    private Club clubSave(String num, String name) {

        Optional<Club> optionalClub = this.clubRepository.findByName(name);
        return optionalClub.orElseGet(() -> this.clubCreate(num, name));
    }

    private Club clubCreate(String num, String name) {
        Club newClub = new Club();
        newClub.setName(name);
        newClub.setNum(num);
        return this.clubRepository.save(newClub);
    }

    private Category categorySave(String shortName, String longName) {

        Optional<Category> optionalCategory = this.categoryRepository.findByShortName(shortName);
        return optionalCategory.orElseGet(() -> this.categoryCreate(shortName, longName));
    }

    private Category categoryCreate(String shortName, String longName) {
        Category newCategory = new Category();
        newCategory.setShortName(shortName);
        newCategory.setLongName(longName);
        return this.categoryRepository.save(newCategory);
    }
    private Team teamSave(Club club, Category category,char gender,int numTeam) {

        Optional<Team> optionalTeam = this.teamRepository.findByClubAndCategoryAndGenderAndNumTeam(club, category,gender,numTeam);
        return optionalTeam.orElseGet(() -> this.teamCreate(club, category,gender,numTeam));
    }

    private Team teamCreate(Club club, Category category,char gender,int numTeam) {
        Team newTeam = new Team();
        newTeam.setNumTeam(numTeam);
        newTeam.setCategory(category);
        newTeam.setClub(club);
        newTeam.setGender(gender);
        return this.teamRepository.save(newTeam);
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

                Competition competition = this.competitionSave(line[headerMap.get("competition")]);
                Pool pool = this.poolSave(line[headerMap.get("num poule")], line[headerMap.get("poule")], competition);

                Address address = this.addressCreate(line[headerMap.get("adresse salle")], line[headerMap.get("CP")], line[headerMap.get("Ville")]);
                Halle halle = this.halleSave(line[headerMap.get("nom salle")], address);

                Referee referee1 = this.refereeSave(line[headerMap.get("arb1 designe")]);
                Referee referee2 = this.refereeSave(line[headerMap.get("arb2 designe")]);

                Score score = new Score(0, 0);
                FDME fdme = new FDME(null);

                extractorHome.setCsvNumPool(line[headerMap.get("num poule")]);
                extractorHome.setCsvTeamName(line[headerMap.get("club rec")]);
                extractorHome.extract();

                extractorVisiting.setCsvNumPool(line[headerMap.get("num poule")]);
                extractorVisiting.setCsvTeamName(line[headerMap.get("club vis")]);
                extractorVisiting.extract();

                Club clubHome = this.clubSave(line[headerMap.get("Num rec")], extractorHome.getClubName());
                Club clubVisiting = this.clubSave(line[headerMap.get("Num vis")], extractorVisiting.getClubName());

                Category categoryHome = this.categorySave(extractorHome.getCategoryShortName(),extractorHome.getCategoryName());
                Category categoryVisiting = this.categorySave(extractorVisiting.getCategoryShortName(),extractorVisiting.getCategoryName());

                Team teamHome = this.teamSave(clubHome,categoryHome,extractorHome.getGender(),extractorHome.getTeamNumber());
                Team teamVisiting = this.teamSave(clubVisiting,categoryVisiting,extractorVisiting.getGender(),extractorVisiting.getTeamNumber());


                Game game = new Game();
                game.setCode(line[headerMap.get("code renc")]);
                game.setHalle(halle);
                game.setFdme(fdme);
                game.setScore(score);
                game.setDateTime(new Date());
                game.setDay(Integer.parseInt(line[headerMap.get("J")]));
                game.setPool(pool);
                game.setReferee1(referee1);
                game.setReferee2(referee2);
                game.setVisitingTeam(teamVisiting);
                game.setHomeTeam(teamHome);
                game = gameRepository.save(game);


            }
        }
    }
}
