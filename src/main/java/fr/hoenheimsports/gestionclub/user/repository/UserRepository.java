package fr.hoenheimsports.gestionclub.user.repository;

import fr.hoenheimsports.gestionclub.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}