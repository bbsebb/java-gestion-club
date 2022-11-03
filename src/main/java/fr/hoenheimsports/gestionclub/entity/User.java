package fr.hoenheimsports.gestionclub.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.net.URL;
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
	private String email;
	private long password;
	private String tel;
	private URL picture;
	@ManyToOne
	@JoinColumn(name = "role_id")
	public Role role;
	@ManyToMany(mappedBy = "barmen")
	private Set<Game> barmanGames;




}