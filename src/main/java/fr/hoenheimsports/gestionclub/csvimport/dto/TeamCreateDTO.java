package fr.hoenheimsports.gestionclub.csvimport.dto;

import fr.hoenheimsports.gestionclub.game.model.TeamColor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.util.Set;

/**
 * A DTO for the {@link fr.hoenheimsports.gestionclub.game.model.Team} entity
 */
public record TeamCreateDTO(
                            @NotNull(message = "La catégorie d'une équipe ne doit pas être nul") CategoryCreateDTO category,
                            char gender,
                            @Positive(message = "Le numéro d'une équipe doit être strictement supérieur à 0") int numTeam,
                            @Valid ClubCreateDTO club, TeamColor teamsColorShirtColor1, TeamColor teamsColorShirtColor2,
                            TeamColor teamsColorGoalkeeperColor1, TeamColor teamsColorGoalkeeperColor2, @Valid ContributorCreateDTO coach,
                            Set<String> homeGameCodes, Set<String> visitingGameCodes) implements Serializable {
}