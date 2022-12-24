package fr.hoenheimsports.gestionclub.game.service;

import fr.hoenheimsports.gestionclub.game.model.Contributor;

public interface ContributorService {
    Contributor createOrUpdate(String name);

    Contributor createOrUpdate(String name, String tel);
}
