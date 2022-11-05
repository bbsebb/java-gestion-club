package fr.hoenheimsports.gestionclub.repository;

import fr.hoenheimsports.gestionclub.entity.Pool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface PoolRepository extends JpaRepository<Pool, String> {
}