package fr.hoenheimsports.gestionclub.service;

import fr.hoenheimsports.gestionclub.model.Contributor;
import fr.hoenheimsports.gestionclub.repository.ContributorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@AllArgsConstructor
public class ContributorServiceImpl implements ContributorService {
    final private ContributorRepository contributorRepository;
    @Override
    public Contributor createOrUpdate(String name) {

        Optional<Contributor> optionalContributor = this.contributorRepository.findByName(name);
        return optionalContributor.orElseGet(() -> this.refereeCreate(name));
    }
    @Override
    public Contributor createOrUpdate(String name,String tel) {

        Optional<Contributor> optionalContributor = this.contributorRepository.findByName(name);
        return optionalContributor.orElseGet(() -> this.refereeCreate(name,tel));
    }

    private Contributor refereeCreate(String name) {
        Contributor newContributor = new Contributor();
        newContributor.setName(name);
        return this.contributorRepository.save(newContributor);
    }
    private Contributor refereeCreate(String name,String tel) {
        Contributor newContributor = new Contributor();
        newContributor.setName(name);
        newContributor.setTel(tel);
        return this.contributorRepository.save(newContributor);
    }
}
