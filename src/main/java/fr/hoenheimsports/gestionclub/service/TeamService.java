package fr.hoenheimsports.gestionclub.service;

import fr.hoenheimsports.gestionclub.model.*;

public interface TeamService {


    Team createOrUpdate(Club club, Category category, char gender, int numTeam, TeamsColor teamsColor, Contributor coach);

    Team createOrUpdate(Club club, Category category, char gender, int numTeam);
}
