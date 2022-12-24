package fr.hoenheimsports.gestionclub.game.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
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
public class Address {

	private String street;
	@Column(nullable = false)
	private int cp;
	private String city;


}