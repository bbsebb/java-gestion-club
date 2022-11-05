package fr.hoenheimsports.gestionclub.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author bbseb
 * @version 1.0
 * @created 12-oct.-2022 23:22:50
 */
@Entity
@Table(name = "Halle", uniqueConstraints = {
		@UniqueConstraint(name = "uc_halle_name_street_cp_city", columnNames = {"name", "street", "cp", "city"})
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Halle {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String name;
	@Embedded
	private Address address;


}