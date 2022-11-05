package fr.hoenheimsports.gestionclub.service;

import fr.hoenheimsports.gestionclub.entity.Referee;

public interface RefereeService {
    Referee createOrUpdate(String name);
}
