package fr.hoenheimsports.gestionclub.repository;

import com.fasterxml.jackson.annotation.OptBoolean;
import fr.hoenheimsports.gestionclub.entity.Address;
import fr.hoenheimsports.gestionclub.entity.Halle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HalleRepository extends JpaRepository<Halle, Long> {
    Optional<Halle> findByNameAndAddress(String name, Address address);
}