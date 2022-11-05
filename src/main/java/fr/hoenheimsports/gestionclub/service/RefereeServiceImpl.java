package fr.hoenheimsports.gestionclub.service;

import fr.hoenheimsports.gestionclub.entity.Referee;
import fr.hoenheimsports.gestionclub.repository.RefereeRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@AllArgsConstructor
public class RefereeServiceImpl implements RefereeService {
    final private RefereeRepository refereeRepository;
    @Override
    public Referee createOrUpdate(String name) {

        Optional<Referee> optionalReferee = this.refereeRepository.findByName(name);
        return optionalReferee.orElseGet(() -> this.refereeCreate(name));
    }

    private Referee refereeCreate(String name) {
        Referee newReferee = new Referee();
        newReferee.setName(name);
        return this.refereeRepository.save(newReferee);
    }
}
