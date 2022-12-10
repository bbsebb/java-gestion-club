package fr.hoenheimsports.gestionclub.service;

import fr.hoenheimsports.gestionclub.model.Competition;
import fr.hoenheimsports.gestionclub.model.Pool;

public interface CompetitionService {

    Competition createOrUpdate(String name, Pool pool);
}
