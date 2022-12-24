package fr.hoenheimsports.gestionclub.game.repository;

import fr.hoenheimsports.gestionclub.game.model.Competition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface CompetitionRepository extends JpaRepository<Competition, Long> {

    Optional<Competition> findByName(String name);
}