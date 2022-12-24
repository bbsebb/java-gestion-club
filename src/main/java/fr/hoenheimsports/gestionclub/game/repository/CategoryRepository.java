package fr.hoenheimsports.gestionclub.game.repository;

import fr.hoenheimsports.gestionclub.game.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByShortName(String shortName);

}