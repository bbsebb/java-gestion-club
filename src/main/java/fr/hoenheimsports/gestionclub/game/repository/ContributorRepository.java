package fr.hoenheimsports.gestionclub.game.repository;

import fr.hoenheimsports.gestionclub.game.model.Contributor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContributorRepository extends JpaRepository<Contributor, Long> {
    Optional<Contributor> findByName(String name);
}