package fr.hoenheimsports.gestionclub.repository;

import fr.hoenheimsports.gestionclub.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}