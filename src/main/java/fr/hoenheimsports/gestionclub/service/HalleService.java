package fr.hoenheimsports.gestionclub.service;

import fr.hoenheimsports.gestionclub.model.Address;
import fr.hoenheimsports.gestionclub.model.Halle;

public interface HalleService {
    Halle createOrUpdate(String name, Address address, String glue);
    Address addressCreate(String street, String cpStr, String city);

}
