package fr.hoenheimsports.gestionclub.service;

import fr.hoenheimsports.gestionclub.entity.Category;

public interface CategoryService {
    Category createOrUpdate(String shortName, String longName);
}
