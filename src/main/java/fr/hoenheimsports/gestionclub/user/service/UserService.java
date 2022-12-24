package fr.hoenheimsports.gestionclub.user.service;

import fr.hoenheimsports.gestionclub.user.model.User;

import java.util.List;

public interface UserService {
    User save(User user);

    List<User> findAll();
}
