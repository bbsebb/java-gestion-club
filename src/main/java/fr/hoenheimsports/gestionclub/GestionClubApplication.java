package fr.hoenheimsports.gestionclub;

import fr.hoenheimsports.gestionclub.entity.Role;
import fr.hoenheimsports.gestionclub.entity.User;
import fr.hoenheimsports.gestionclub.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class GestionClubApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(GestionClubApplication.class, args);
    }


    @Autowired
            @Qualifier("noPLayed")
    CSVImport importCsvNoPlayedGames;

    @Autowired
    @Qualifier("played")
    CSVImport importCsvPlayedGames;

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;


    @Override
    public void run(String... args) throws Exception {

       importCsvNoPlayedGames.extract("static/test.csv");
       importCsvPlayedGames.extract("static/test2.csv");
       Role r = new Role();
        r.setName("USER");
        Role r1 = new Role();
        r.setName("BARMAN");
        Role r2 = new Role();
        r.setName("ADMIN");

        User user = new User();
        user.setName("Burckhardt");
        user.setForename("Sébastien");
        user.setEmail("sebastien.burckhardt@gmail.com");
        user.setPassword("1234");
        r = roleService.save(r);
        r1 = roleService.save(r1);
        r2 = roleService.save(r2);

        user.addRole(r);
        user.addRole(r1);
        user.addRole(r2);
        userService.save(user);

    }
}

