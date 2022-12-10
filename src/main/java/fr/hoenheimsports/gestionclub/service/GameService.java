package fr.hoenheimsports.gestionclub.service;

import fr.hoenheimsports.gestionclub.model.*;

import java.util.List;

public interface GameService {



    Game createOrUpdate(String code,
                        Halle halle,
                        FDME fdme,
                        Score score,
                        String day,
                        String date,
                        String time,
                        Competition competition,
                        Contributor ref1,
                        Contributor ref2,
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
                        Competition competition,
                        Contributor ref1,
                        Contributor ref2,
                        Team homeTeam,
                        Team visitingTeam,
                        boolean isPlayed
    );

    List<Game> findAll();
}
