package fr.hoenheimsports.gestionclub.service;

import fr.hoenheimsports.gestionclub.entity.Address;
import fr.hoenheimsports.gestionclub.entity.Halle;

public interface HalleService {
    Halle createOrUpdate(String name, Address address);
    Address addressCreate(String street, String cpStr, String city);

}
