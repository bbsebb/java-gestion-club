package fr.hoenheimsports.gestionclub.service;

import fr.hoenheimsports.gestionclub.entity.Address;
import fr.hoenheimsports.gestionclub.entity.Halle;

public interface HalleService {
    public Halle createOrUpdate(String name, Address address);
    public Address addressCreate(String street, String cpStr, String city);

}
