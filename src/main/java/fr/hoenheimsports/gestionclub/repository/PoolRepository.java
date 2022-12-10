package fr.hoenheimsports.gestionclub.repository;

import fr.hoenheimsports.gestionclub.model.Pool;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PoolRepository extends JpaRepository<Pool, String> {
}