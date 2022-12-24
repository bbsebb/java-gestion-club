package fr.hoenheimsports.gestionclub.game.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author bbseb
 * @version 1.0
 * @created 12-oct.-2022 23:22:50
 */
@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Competition {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(unique = true)
	private String name;
	@NotNull
	@ManyToOne(optional = false)
	@JoinColumn(name = "pool_id")
	private Pool pool;


}