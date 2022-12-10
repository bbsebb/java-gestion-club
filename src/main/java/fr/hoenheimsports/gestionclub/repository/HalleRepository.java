package fr.hoenheimsports.gestionclub.repository;

import fr.hoenheimsports.gestionclub.model.Address;
import fr.hoenheimsports.gestionclub.model.Halle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HalleRepository extends JpaRepository<Halle, Long> {
    Optional<Halle> findByNameAndAddress(String name, Address address);
}