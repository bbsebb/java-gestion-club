package fr.hoenheimsports.gestionclub.game.service;

import fr.hoenheimsports.gestionclub.game.model.*;

public interface TeamService {


    Team createOrUpdate(Club club, Category category, char gender, int numTeam, TeamsColor teamsColor, Contributor coach);

    Team createOrUpdate(Club club, Category category, char gender, int numTeam);
}
