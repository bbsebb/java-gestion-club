package fr.hoenheimsports.gestionclub.csvimport.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * A DTO for the {@link fr.hoenheimsports.gestionclub.game.model.Game} entity
 */
@Data
public class GameCreateDTO implements Serializable {
    @NotBlank(message = "Le code de la rencontre ne doit pas être vide")
    @NotEmpty(message = "Le code de la rencontre ne doit pas être vide")
    @NotNull(message = "Le code de la rencontre ne doit pas être vide")
    @Length(message = "Le code d'une rencontre doit être de 7 caractères", min = 7, max = 7)
    private final String code;
    @NotNull(message = "Une rencontre doit avoir une poule")
    @Valid
    private final CompetitionCreateDTO competition;
    @Positive(message = "Le jour de la rencontre doit être supérieur à 0")
    private final int day;
    @Valid
    private final HalleCreateDTO halle;
    @Valid
    private final ContributorCreateDTO referee1;
    @Valid
    private final ContributorCreateDTO referee2;
    @Valid
    private final ContributorCreateDTO realReferee1;
    @Valid
    private final ContributorCreateDTO realReferee2;
    @NotNull
    @Valid
    private final TeamCreateDTO homeTeam;
    @NotNull
    @Valid
    private final TeamCreateDTO visitingTeam;
    private final URL fdmeUrl;
    private final Set<Long> barmanIds;
    @PositiveOrZero(message = "Le score ne peut être négatif")
    private final int scoreScoreHomeTeam;
    @PositiveOrZero(message = "Le score ne peut être négatif")
    private final int scoreScoreVisitingTeam;
    private final LocalDateTime dateTime;
    private final boolean isPlayed;
}