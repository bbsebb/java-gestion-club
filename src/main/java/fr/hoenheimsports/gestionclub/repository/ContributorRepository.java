package fr.hoenheimsports.gestionclub.repository;

import fr.hoenheimsports.gestionclub.model.Contributor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContributorRepository extends JpaRepository<Contributor, Long> {
    Optional<Contributor> findByName(String name);
}