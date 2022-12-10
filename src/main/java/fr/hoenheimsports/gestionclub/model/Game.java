package fr.hoenheimsports.gestionclub.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * @author bbseb
 * @version 1.0
 * @created 12-oct.-2022 23:22:50
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Game {
	@NotBlank(message = "Le code de la rencontre ne doit pas être vide")
	@NotEmpty(message = "Le code de la rencontre ne doit pas être vide")
	@NotNull(message = "Le code de la rencontre ne doit pas être vide")
	@Length(message = "Le code d'une rencontre doit etre de 7 caractères", min = 7, max = 7)
	@Id
	@Column(nullable = false)
	private String code;
	@NotNull(message = "Une rencontre doit avoir une poule")
	@ManyToOne(optional = false)
	@JoinColumn(name = "competition_id")
	private Competition competition;
	@Positive(message = "Le jour de la rencontre doit être superieur à 0")
	@Column(name = "num_day", nullable = false)
	private int day;
	@ManyToOne
	private Halle halle;
	@ManyToOne
	private Contributor referee1;
	@ManyToOne
	private Contributor referee2;
	@ManyToOne
	private Contributor realReferee1;
	@ManyToOne
	private Contributor realReferee2;
	@NotNull
	@JsonIgnoreProperties({"homeGames","visitingGames"})
	@ManyToOne(optional = false)
	private Team homeTeam;
	@NotNull
	@ManyToOne(optional = false)
	private Team visitingTeam;
	@Embedded
	private FDME fdme;


	@ManyToMany()
	@JoinTable(name = "game_barmen",
			joinColumns = @JoinColumn(name = "game_code", referencedColumnName = "code"),
			inverseJoinColumns = @JoinColumn(name = "barman_id", referencedColumnName = "id"))
	private Set<User> barmen = new HashSet<>();
	@Embedded
	private Score score;

	private LocalDateTime dateTime;

	private boolean isPlayed;


}