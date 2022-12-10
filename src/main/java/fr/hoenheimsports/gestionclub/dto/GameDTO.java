package fr.hoenheimsports.gestionclub.dto;

import fr.hoenheimsports.gestionclub.model.*;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * A DTO for the {@link fr.hoenheimsports.gestionclub.model.Game} entity
 */
@Data
public class GameDTO implements Serializable {
    @NotBlank(message = "Le code de la rencontre ne doit pas être vide")
    @NotEmpty(message = "Le code de la rencontre ne doit pas être vide")
    @NotNull(message = "Le code de la rencontre ne doit pas être vide")
    @Length(message = "Le code d'une rencontre doit etre de 7 caractères", min = 7, max = 7)
    private String code;
    @NotNull(message = "Une rencontre doit avoir une poule")
    private Competition competition;
    @Positive(message = "Le jour de la rencontre doit être superieur à 0")
    private int day;
    private Halle halle;
    private Contributor referee1;
    private Contributor referee2;
    @NotNull
    private  TeamDTO homeTeam;
    @NotNull
    private  TeamDTO visitingTeam;
    private FDME fdme;
    private Score score;
    private LocalDateTime dateTime;
    private boolean isPlayed;
}