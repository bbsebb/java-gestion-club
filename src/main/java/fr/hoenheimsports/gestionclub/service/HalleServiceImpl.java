package fr.hoenheimsports.gestionclub.service;

import fr.hoenheimsports.gestionclub.model.Address;
import fr.hoenheimsports.gestionclub.model.Halle;
import fr.hoenheimsports.gestionclub.repository.HalleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class HalleServiceImpl implements HalleService {

    final private HalleRepository halleRepository;

    @Override
    public Halle createOrUpdate(String name, Address address, String glue) {

        Optional<Halle> optionalHalle = this.halleRepository.findByNameAndAddress(name, address);
        return optionalHalle.orElseGet(() -> this.halleCreate(name, address, glue));
    }

    private Halle halleCreate(String name, Address address,String glue) {
        Halle newHalle = new Halle();
        newHalle.setName(name);
        newHalle.setAddress(address);
        newHalle.setGlue(glue);
        return this.halleRepository.save(newHalle);
    }

    public Address addressCreate(String street, String cpStr, String city) {
        Address address = new Address();
        address.setStreet(street);
        int cp = 0;
        try {
            cp = Integer.parseInt(cpStr);
        } catch (NumberFormatException nfe) {
        }
        address.setCp(cp);
        address.setCity(city);
        return address;
    }
}
