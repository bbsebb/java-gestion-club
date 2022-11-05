package fr.hoenheimsports.gestionclub.service;

import fr.hoenheimsports.gestionclub.entity.Competition;
import fr.hoenheimsports.gestionclub.repository.CompetitionRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@AllArgsConstructor
public class CompetitionServiceImpl implements CompetitionService {
    final private CompetitionRepository competitionRepository;

    public Competition createOrUpdate(String name) {
        Optional<Competition> optionalCompetition = this.competitionRepository.findByName(name);
        return optionalCompetition.orElseGet(() -> this.competitionCreate(name));
    }

    private Competition competitionCreate(String name) {
        Competition newCompetition = new Competition();
        newCompetition.setName(name);
        return competitionRepository.save(newCompetition);
    }
}
