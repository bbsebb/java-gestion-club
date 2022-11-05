package fr.hoenheimsports.gestionclub.service;

import fr.hoenheimsports.gestionclub.entity.Category;
import fr.hoenheimsports.gestionclub.entity.Club;
import fr.hoenheimsports.gestionclub.entity.Team;

public interface TeamService {
    Team createOrUpdate(Club club, Category category, char gender, int numTeam);
}
