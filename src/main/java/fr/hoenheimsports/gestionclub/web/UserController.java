package fr.hoenheimsports.gestionclub.web;

import fr.hoenheimsports.gestionclub.model.User;
import fr.hoenheimsports.gestionclub.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @GetMapping("/users")
    public List<User> alUsers() {
        return this.userService.findAll();
    }
}
