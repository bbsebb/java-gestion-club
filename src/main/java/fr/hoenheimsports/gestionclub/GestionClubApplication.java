package fr.hoenheimsports.gestionclub;

import fr.hoenheimsports.gestionclub.service.CSVImportGamePlayedImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class GestionClubApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(GestionClubApplication.class, args);
    }


    @Autowired
    CSVImportGamePlayedImpl importCsv;


    @Override
    public void run(String... args) throws Exception {

        importCsv.extract("static/test.csv");

    }
}

