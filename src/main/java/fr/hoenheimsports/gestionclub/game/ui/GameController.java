package fr.hoenheimsports.gestionclub.game.ui;

import fr.hoenheimsports.gestionclub.game.dto.GameDTO;
import fr.hoenheimsports.gestionclub.game.model.Game;
import fr.hoenheimsports.gestionclub.game.service.GameService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController("api/v1.0")
public class GameController {

    final private GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/games")
    public List<GameDTO> allGames() {
        return this.gameService.findAll().stream().map(this::mapper).toList();
    }

    private GameDTO mapper(Game game) {
        ModelMapper modelMapper= new ModelMapper();
        //modelMapper.getConfiguration().setPreferNestedProperties(false);
        TypeMap<Game,GameDTO> typeMap = modelMapper.createTypeMap(Game.class, GameDTO.class);



       return typeMap.map(game);
        //return modelMapper.map(game, GameDTO.class);
    }


}
