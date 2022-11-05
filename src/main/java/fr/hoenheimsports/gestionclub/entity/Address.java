package fr.hoenheimsports.gestionclub.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

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