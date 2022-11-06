package fr.hoenheimsports.gestionclub.service;

import fr.hoenheimsports.gestionclub.entity.Competition;

public interface CompetitionService {

    Competition createOrUpdate(String name);
}
