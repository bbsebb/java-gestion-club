package fr.hoenheimsports.gestionclub.game.service;

import fr.hoenheimsports.gestionclub.game.model.Category;

public interface CategoryService {
    Category createOrUpdate(String shortName, String longName);
}
