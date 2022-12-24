package fr.hoenheimsports.gestionclub.game.service;

import fr.hoenheimsports.gestionclub.user.model.Role;
import fr.hoenheimsports.gestionclub.game.repository.RoleRepository;
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
