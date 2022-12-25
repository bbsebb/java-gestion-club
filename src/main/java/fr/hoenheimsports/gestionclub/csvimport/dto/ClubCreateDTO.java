package fr.hoenheimsports.gestionclub.csvimport.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * A DTO for the {@link fr.hoenheimsports.gestionclub.game.model.Club} entity
 */
public record ClubCreateDTO(
                            @Size(message = "Le numéro du code doit être de 11 caractères", min = 7, max = 7) @NotBlank(message = "Le colonne numéro du club ne devrait pas être vide") @NotEmpty(message = "Le colonne numéro du club ne devrait pas être vide") @NotNull(message = "Le colonne numéro du club ne devrait pas être vide") String num,
                            String name) implements Serializable {
}