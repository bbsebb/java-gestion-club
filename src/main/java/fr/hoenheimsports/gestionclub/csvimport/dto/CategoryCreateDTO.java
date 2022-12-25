package fr.hoenheimsports.gestionclub.csvimport.dto;



import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * A DTO for the {@link fr.hoenheimsports.gestionclub.game.model.Category} entity
 */

public record CategoryCreateDTO(
                                @Size(message = "La colonne catégorie de devrait pas avoir un nom court à plus de 5 caractère", max = 5) @NotBlank(message = "La colonne catégorie ne devrait pas être vide") @NotEmpty(message = "La colonne catégorie ne devrait pas être vide") @NotNull(message = "La colonne catégorie ne devrait pas être vide") String shortName,
                                @Size(message = "La colonne catégorie de devrait pas avoir un nom long à plus de 15 caractères", max = 15) @NotBlank(message = "La colonne catégorie ne devrait pas être vide") @NotEmpty(message = "La colonne catégorie ne devrait pas être vide") @NotNull(message = "La colonne catégorie ne devrait pas être vide") String longName) implements Serializable {
}