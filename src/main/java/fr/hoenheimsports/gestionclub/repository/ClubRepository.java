package fr.hoenheimsports.gestionclub.repository;

import fr.hoenheimsports.gestionclub.model.Club;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClubRepository extends JpaRepository<Club, String> {
    Optional<Club> findByName(String name);
}