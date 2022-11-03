package fr.hoenheimsports.gestionclub.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
	@Id
	private String code;
	@ManyToOne
	@JoinColumn(name = "pool_id")
	private Pool pool;
	@Column(name="num_day")
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
	private Date dateTime;

}