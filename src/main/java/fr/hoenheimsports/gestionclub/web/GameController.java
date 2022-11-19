package fr.hoenheimsports.gestionclub.web;

import fr.hoenheimsports.gestionclub.entity.Game;
import fr.hoenheimsports.gestionclub.service.GameService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
public class GameController {

    private GameService gameService;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/games")
    public List<Game> allGames() {
        return this.gameService.findAll();
    }

}
