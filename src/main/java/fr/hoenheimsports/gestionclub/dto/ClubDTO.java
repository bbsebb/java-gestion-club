package fr.hoenheimsports.gestionclub.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * A DTO for the {@link fr.hoenheimsports.gestionclub.model.Club} entity
 */
@Data
public class ClubDTO implements Serializable {
    private  long id;
    @Length(message = "Le numéro du code doit être de 11 caractères", min = 7, max = 7)
    @NotBlank(message = "Le colonne numero du club ne devrait pas être vide")
    @NotEmpty(message = "Le colonne numero du club ne devrait pas être vide")
    @NotNull(message = "Le colonne numero du club ne devrait pas être vide")
    private  String num;
    private  String name;
}