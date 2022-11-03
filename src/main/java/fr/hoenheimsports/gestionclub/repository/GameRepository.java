package fr.hoenheimsports.gestionclub.repository;

import fr.hoenheimsports.gestionclub.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, String> {
}