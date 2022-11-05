package fr.hoenheimsports.gestionclub.service;

import fr.hoenheimsports.gestionclub.entity.Competition;
import fr.hoenheimsports.gestionclub.entity.Pool;

public interface PoolService {
    Pool createOrUpdate(String num, String name, Competition c);
}
