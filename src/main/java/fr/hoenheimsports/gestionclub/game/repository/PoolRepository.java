package fr.hoenheimsports.gestionclub.game.repository;

import fr.hoenheimsports.gestionclub.game.model.Pool;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PoolRepository extends JpaRepository<Pool, String> {
}