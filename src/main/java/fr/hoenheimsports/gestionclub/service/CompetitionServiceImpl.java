package fr.hoenheimsports.gestionclub.service;

import fr.hoenheimsports.gestionclub.model.Competition;
import fr.hoenheimsports.gestionclub.model.Pool;
import fr.hoenheimsports.gestionclub.repository.CompetitionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@AllArgsConstructor
public class CompetitionServiceImpl implements CompetitionService {
    final private CompetitionRepository competitionRepository;

    @Override
    public Competition createOrUpdate(String name, Pool pool) {
        Optional<Competition> optionalCompetition = this.competitionRepository.findByName(name);
        Competition comp = optionalCompetition.orElseGet(() -> this.competitionCreate(name));
        comp.setPool(pool);
        return competitionRepository.save(comp);
    }

    private Competition competitionCreate(String name) {
        Competition newCompetition = new Competition();
        newCompetition.setName(name);
        return  newCompetition;
    }
}
