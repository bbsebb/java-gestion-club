package fr.hoenheimsports.gestionclub.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

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

	private int scoreHomeTeam;
	private int scoreVisitingTeam;


}