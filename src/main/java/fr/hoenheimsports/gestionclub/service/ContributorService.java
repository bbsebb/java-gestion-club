package fr.hoenheimsports.gestionclub.service;

import fr.hoenheimsports.gestionclub.model.Contributor;

public interface ContributorService {
    Contributor createOrUpdate(String name);

    Contributor createOrUpdate(String name, String tel);
}
