package fr.hoenheimsports.gestionclub.service;

import fr.hoenheimsports.gestionclub.entity.Category;
import fr.hoenheimsports.gestionclub.entity.Club;
import fr.hoenheimsports.gestionclub.entity.Team;
import fr.hoenheimsports.gestionclub.repository.TeamRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class TeamServiceImpl implements TeamService {
    final private TeamRepository teamRepository;

    @Override
    public Team createOrUpdate(Club club, Category category, char gender, int numTeam) {
        Optional<Team> optionalTeam = this.teamRepository.findByClubAndCategoryAndGenderAndNumTeam(club, category,gender,numTeam);
        return optionalTeam.orElseGet(() -> this.teamCreate(club, category,gender,numTeam));
    }

    private Team teamCreate(Club club, Category category,char gender,int numTeam) {
        Team newTeam = new Team();
        newTeam.setNumTeam(numTeam);
        newTeam.setCategory(category);
        newTeam.setClub(club);
        newTeam.setGender(gender);
        return this.teamRepository.save(newTeam);
    }
}
