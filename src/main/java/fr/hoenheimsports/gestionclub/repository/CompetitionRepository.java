package fr.hoenheimsports.gestionclub.repository;

import fr.hoenheimsports.gestionclub.entity.Category;
import fr.hoenheimsports.gestionclub.entity.Competition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CompetitionRepository extends JpaRepository<Competition, Long> {

    Optional<Competition> findByName(String name);
}