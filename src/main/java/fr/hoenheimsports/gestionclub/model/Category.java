package fr.hoenheimsports.gestionclub.model;


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
@NoArgsConstructor
@AllArgsConstructor
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Length(message = "La colonne catégorie de devrait pas avoir un nom court à plus de 5 caractère", max = 5)
	@NotBlank(message = "La colonne catégorie ne devrait pas être vide")
	@NotEmpty(message = "La colonne catégorie ne devrait pas être vide")
	@NotNull(message = "La colonne catégorie ne devrait pas être vide")
	@Column(unique=true)
	private String shortName;

	@Length(message = "La colonne catégorie de devrait pas avoir un nom long à plus de 15 caractères", max = 15)
	@NotBlank(message = "La colonne catégorie ne devrait pas être vide")
	@NotEmpty(message = "La colonne catégorie ne devrait pas être vide")
	@NotNull(message = "La colonne catégorie ne devrait pas être vide")
	private String longName;


}