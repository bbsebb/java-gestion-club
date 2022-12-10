package fr.hoenheimsports.gestionclub.service;

import fr.hoenheimsports.gestionclub.model.Category;

public interface CategoryService {
    Category createOrUpdate(String shortName, String longName);
}
