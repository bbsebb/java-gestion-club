package fr.hoenheimsports.gestionclub.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * A DTO for the {@link fr.hoenheimsports.gestionclub.model.Category} entity
 */
@Data
public class CategoryDTO implements Serializable {
    private  long id;
    @Length(message = "La colonne catégorie de devrait pas avoir un nom court à plus de 5 caractère", max = 5)
    @NotBlank(message = "La colonne catégorie ne devrait pas être vide")
    @NotEmpty(message = "La colonne catégorie ne devrait pas être vide")
    @NotNull(message = "La colonne catégorie ne devrait pas être vide")
    private  String shortName;
    @Length(message = "La colonne catégorie de devrait pas avoir un nom long à plus de 15 caractères", max = 15)
    @NotBlank(message = "La colonne catégorie ne devrait pas être vide")
    @NotEmpty(message = "La colonne catégorie ne devrait pas être vide")
    @NotNull(message = "La colonne catégorie ne devrait pas être vide")
    private  String longName;
}