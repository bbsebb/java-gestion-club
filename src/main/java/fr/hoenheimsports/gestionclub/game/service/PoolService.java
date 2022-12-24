package fr.hoenheimsports.gestionclub.game.service;

import fr.hoenheimsports.gestionclub.game.model.Pool;

public interface PoolService {
    Pool createOrUpdate(String num, String name);
}
