package fr.hoenheimsports.gestionclub.game.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeamsColor {

    private TeamColor shirtColor1;
    private TeamColor shirtColor2;
    private TeamColor goalkeeperColor1;
    private TeamColor goalkeeperColor2;

}
