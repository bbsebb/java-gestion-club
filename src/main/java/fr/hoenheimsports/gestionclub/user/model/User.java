package fr.hoenheimsports.gestionclub.user.model;


import fr.hoenheimsports.gestionclub.game.model.Game;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.net.URL;
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
@Table(name="users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String name;
	private String forename;
	@Email(message = "l'email n'a pas une forme correcte")
	private String email;

	private String password;
	private String tel;
	private URL picture;

	@ManyToMany
	public Set<Role> roles = new HashSet();
	@ManyToMany(mappedBy = "barmen")
	private Set<Game> barmanGames = new HashSet();


	public void addRole(Role r) {
		this.roles.add(r);
	}

}