package fr.hoenheimsports.gestionclub.repository;

import fr.hoenheimsports.gestionclub.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, String> {
}