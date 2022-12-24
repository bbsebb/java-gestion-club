package fr.hoenheimsports.gestionclub.game.service;

import fr.hoenheimsports.gestionclub.game.model.Competition;
import fr.hoenheimsports.gestionclub.game.model.Pool;

public interface CompetitionService {

    Competition createOrUpdate(String name, Pool pool);
}
