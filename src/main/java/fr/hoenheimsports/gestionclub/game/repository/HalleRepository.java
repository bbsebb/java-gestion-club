package fr.hoenheimsports.gestionclub.game.repository;

import fr.hoenheimsports.gestionclub.game.model.Address;
import fr.hoenheimsports.gestionclub.game.model.Halle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HalleRepository extends JpaRepository<Halle, Long> {
    Optional<Halle> findByNameAndAddress(String name, Address address);
}