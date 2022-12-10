package fr.hoenheimsports.gestionclub.web;

import fr.hoenheimsports.gestionclub.dto.GameDTO;
import fr.hoenheimsports.gestionclub.model.Game;
import fr.hoenheimsports.gestionclub.service.GameService;
import lombok.AllArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
public class GameController {

    private GameService gameService;



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
