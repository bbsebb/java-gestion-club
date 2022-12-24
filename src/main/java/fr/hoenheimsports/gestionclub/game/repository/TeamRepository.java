package fr.hoenheimsports.gestionclub.game.repository;

import fr.hoenheimsports.gestionclub.game.model.Category;
import fr.hoenheimsports.gestionclub.game.model.Club;
import fr.hoenheimsports.gestionclub.game.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team, Long> {
    Optional<Team> findByClubAndCategoryAndGenderAndNumTeam(Club club, Category category, char gender, int numTeam);
}