package fr.hoenheimsports.gestionclub.game.service;

import fr.hoenheimsports.gestionclub.game.model.Address;
import fr.hoenheimsports.gestionclub.game.model.Halle;

public interface HalleService {
    Halle createOrUpdate(String name, Address address, String glue);
    Address addressCreate(String street, String cpStr, String city);

}
