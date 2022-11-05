package fr.hoenheimsports.gestionclub.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.Date;
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
	@ManyToOne
	@JoinColumn(name = "pool_id")
	private Pool pool;
	@Positive(message = "Le jour de la rencontre doit être superieur à 0")
	@Column(name = "num_day", nullable = false)
	private int day;
	@ManyToOne
	@JoinColumn(name = "halle_id")
	private Halle halle;
	@ManyToOne
	@JoinColumn(name = "referee_1_id")
	private Referee referee1;
	@ManyToOne
	@JoinColumn(name = "referee_2_id")
	private Referee referee2;
	@ManyToOne
	@JoinColumn(name = "home_team_id")
	private Team homeTeam;
	@ManyToOne
	@JoinColumn(name = "visiting_team_id")
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