package fr.hoenheimsports.gestionclub.service;

import fr.hoenheimsports.gestionclub.model.Role;
import fr.hoenheimsports.gestionclub.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Role save(Role role) {
        return this.roleRepository.save(role);
    }
}
