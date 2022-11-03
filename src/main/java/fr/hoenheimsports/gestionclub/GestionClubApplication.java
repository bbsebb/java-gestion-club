package fr.hoenheimsports.gestionclub;

import fr.hoenheimsports.gestionclub.service.CSVImportImpl;
import fr.hoenheimsports.gestionclub.service.util.ExtractInfoTeam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;


@SpringBootApplication
public class GestionClubApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(GestionClubApplication.class, args);
    }


    @Autowired
    CSVImportImpl importCsv;


    @Override
    public void run(String... args) throws Exception {

        importCsv.setResource("static/test.csv");
        importCsv.run();

    }
}

