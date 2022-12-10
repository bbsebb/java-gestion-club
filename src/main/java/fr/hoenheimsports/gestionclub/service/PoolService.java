package fr.hoenheimsports.gestionclub.service;

import fr.hoenheimsports.gestionclub.model.Competition;
import fr.hoenheimsports.gestionclub.model.Pool;

public interface PoolService {
    Pool createOrUpdate(String num, String name);
}
