package fr.hoenheimsports.gestionclub.dto;

import fr.hoenheimsports.gestionclub.model.Contributor;
import fr.hoenheimsports.gestionclub.model.Game;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A DTO for the {@link fr.hoenheimsports.gestionclub.model.Team} entity
 */
@Data
public class TeamDTO implements Serializable {
    private  long id;
    @NotNull(message = "La catégorie d'une équipe ne doit pas être nul")
    private  CategoryDTO category;
    private  char gender;
    @Positive(message = "Le numéro d'une équipe doit être strictement supérieur à 0")
    private  int numTeam;
    private  ClubDTO club;
    private Contributor coach;


}