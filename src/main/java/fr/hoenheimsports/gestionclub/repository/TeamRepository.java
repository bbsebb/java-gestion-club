package fr.hoenheimsports.gestionclub.repository;

import fr.hoenheimsports.gestionclub.model.Category;
import fr.hoenheimsports.gestionclub.model.Club;
import fr.hoenheimsports.gestionclub.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team, Long> {
    Optional<Team> findByClubAndCategoryAndGenderAndNumTeam(Club club, Category category, char gender, int numTeam);
}