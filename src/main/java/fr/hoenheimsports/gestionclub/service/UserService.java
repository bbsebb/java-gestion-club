package fr.hoenheimsports.gestionclub.service;

import fr.hoenheimsports.gestionclub.model.User;

import java.util.List;

public interface UserService {
    User save(User user);

    List<User> findAll();
}
