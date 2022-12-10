package fr.hoenheimsports.gestionclub.repository;

import fr.hoenheimsports.gestionclub.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}