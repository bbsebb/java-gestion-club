package fr.hoenheimsports.gestionclub.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.PositiveOrZero;

/**
 * @author bbseb
 * @version 1.0
 * @created 12-oct.-2022 23:22:50
 */
@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Score {

	@PositiveOrZero(message = "Le score ne peut etre négatif")
	@Column(nullable = false)
	private int scoreHomeTeam;
	@PositiveOrZero(message = "Le score ne peut etre négatif")
	@Column(nullable = false)
	private int scoreVisitingTeam;


}