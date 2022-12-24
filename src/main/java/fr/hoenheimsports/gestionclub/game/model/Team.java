package fr.hoenheimsports.gestionclub.game.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.HashSet;
import java.util.Set;

/**
 * @author bbseb
 * @version 1.0
 * @created 12-oct.-2022 23:22:50
 */
@Entity
@Table(name = "Team", uniqueConstraints = {
		@UniqueConstraint(name = "uc_team_club_id_numteam", columnNames = {"club_id", "numTeam", "gender", "category_id"})
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Team {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@NotNull(message = "La catégorie d'une équipe ne doit pas être nul")
	@ManyToOne(optional = false)
	@JoinColumn(name = "category_id")
	private Category category;
	@Column(nullable = false)
	private char gender;
	@Positive(message = "Le numéro d'une équipe doit être strictement supérieur à 0")
	@Column(nullable = false)
	private int numTeam;
	@ManyToOne(optional = false)
	@JoinColumn(name = "club_id")
	private Club club;
	@Embedded
	private TeamsColor teamsColor;
	@ManyToOne
	private Contributor coach;


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