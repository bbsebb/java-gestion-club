package fr.hoenheimsports.gestionclub.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
public class Team {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;
	private char gender;
	private int numTeam;
	@ManyToOne
	@JoinColumn(name = "club_id")
	private Club club;

	@OneToMany(mappedBy = "homeTeam")
	private Set<Game> homeGames = new HashSet<>();

	@OneToMany(mappedBy = "visitingTeam")
	private Set<Game> visitingGames = new HashSet<>();


	public boolean addHomeGames(Game game) {
		return this.homeGames.add(game);
	}
	public boolean addVisitingGames(Game game) {
		return this.visitingGames.add(game);
	}


}