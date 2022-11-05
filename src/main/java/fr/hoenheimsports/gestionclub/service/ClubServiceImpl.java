package fr.hoenheimsports.gestionclub.service;

import fr.hoenheimsports.gestionclub.entity.Club;
import fr.hoenheimsports.gestionclub.repository.ClubRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ClubServiceImpl implements ClubService {

    final private ClubRepository clubRepository;

    @Override
    public Club save(String num, String name) {
        Optional<Club> optionalClub = this.clubRepository.findByName(name);
        return optionalClub.orElseGet(() -> this.clubCreate(num, name));
    }

    private Club clubCreate(String num, String name) {
        Club newClub = new Club();
        newClub.setName(name);
        newClub.setNum(num);
        return this.clubRepository.save(newClub);
    }
}
