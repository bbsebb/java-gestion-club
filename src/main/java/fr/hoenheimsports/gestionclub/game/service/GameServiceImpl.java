package fr.hoenheimsports.gestionclub.game.service;

import fr.hoenheimsports.gestionclub.game.model.*;
import fr.hoenheimsports.gestionclub.game.repository.GameRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GameServiceImpl implements GameService {

    final private GameRepository gameRepository;

    @Override
    public Game createOrUpdate(String code,
                               Halle halle,
                               FDME fdme,
                               Score score,
                               String day,
                               String date,
                               String time,
                               Competition competition,
                               Contributor ref1,
                               Contributor ref2,
                               Team homeTeam,
                               Team visitingTeam,
                               boolean isPlayed
    ) {
        Optional<Game> optionalGame = this.gameRepository.findById(code);
        Game game = optionalGame.orElseGet(() -> this.gameCreate(code));
        game.setHalle(halle);
        game.setFdme(fdme);
        game.setScore(score);
        game.setDay(Integer.parseInt(day));
        game.setDateTime(this.dateConversion(date,time));
        game.setCompetition(competition);
        game.setReferee1(ref1);
        game.setReferee2(ref2);
        game.setVisitingTeam(visitingTeam);
        game.setHomeTeam(homeTeam);
        game.setPlayed(isPlayed);
        return this.gameRepository.save(game);
    }

    @Override
    public Game createOrUpdate(String code,
                               FDME fdme,
                               Score score,
                               String day,
                               String date,
                               String time,
                               Competition competition,
                               Contributor ref1,
                               Contributor ref2,
                               Team homeTeam,
                               Team visitingTeam,
                               boolean isPlayed
    ) {
        Optional<Game> optionalGame = this.gameRepository.findById(code);
        Game game = optionalGame.orElseGet(() -> this.gameCreate(code));
        game.setFdme(fdme);
        game.setScore(score);
        game.setDay(Integer.parseInt(day));
        game.setDateTime(this.dateConversion(date,time));
        game.setCompetition(competition);
        game.setReferee1(ref1);
        game.setReferee2(ref2);
        game.setVisitingTeam(visitingTeam);
        game.setHomeTeam(homeTeam);
        game.setPlayed(isPlayed);
        return this.gameRepository.save(game);
    }

    @Override
    public List<Game> findAll() {
        return this.gameRepository.findAll();
    }

    private Game gameCreate(String code) {
        Game newHGame = new Game();
        newHGame.setCode(code);
        return newHGame;
    }

    private LocalDateTime dateConversion(String dateStr,String timeStr) {
        LocalDateTime dateTime = null;
        if(!dateStr.isBlank() && !timeStr.isBlank()) {
            {
                String dateTimeStr = dateStr + " " + timeStr;
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                dateTime = LocalDateTime.parse(dateTimeStr,formatter);
            }
        }
        return dateTime;
    }
}
