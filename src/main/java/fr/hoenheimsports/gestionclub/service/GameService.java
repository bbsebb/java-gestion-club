package fr.hoenheimsports.gestionclub.service;

import fr.hoenheimsports.gestionclub.entity.*;

import java.util.List;

public interface GameService {



    Game createOrUpdate(String code,
                        Halle halle,
                        FDME fdme,
                        Score score,
                        String day,
                        String date,
                        String time,
                        Pool pool,
                        Referee ref1,
                        Referee ref2,
                        Team homeTeam,
                        Team visitingTeam,
                        boolean isPlayed
    );

    Game createOrUpdate(String code,
                        FDME fdme,
                        Score score,
                        String day,
                        String date,
                        String time,
                        Pool pool,
                        Referee ref1,
                        Referee ref2,
                        Team homeTeam,
                        Team visitingTeam,
                        boolean isPlayed
    );

    List<Game> findAll();
}
