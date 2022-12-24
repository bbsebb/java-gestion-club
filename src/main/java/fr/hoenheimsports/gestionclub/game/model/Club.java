package fr.hoenheimsports.gestionclub.game.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author bbseb
 * @version 1.0
 * @created 12-oct.-2022 23:22:50
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Club {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Length(message = "Le numéro du code doit être de 11 caractères", min = 7, max = 7)
    @NotBlank(message = "Le colonne numero du club ne devrait pas être vide")
    @NotEmpty(message = "Le colonne numero du club ne devrait pas être vide")
    @NotNull(message = "Le colonne numero du club ne devrait pas être vide")
    private String num;
    @Column(unique = true)
    private String name;

}