package fr.hoenheimsports.gestionclub.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author bbseb
 * @version 1.0
 * @created 12-oct.-2022 23:22:50
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pool {
	@Id
	private String num;
	private String name;
	@ManyToOne
	@JoinColumn(name = "competition_id")
	private Competition competition;


}