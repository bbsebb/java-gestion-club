package fr.hoenheimsports.gestionclub.csvimport.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link fr.hoenheimsports.gestionclub.game.model.Contributor} entity
 */
public record ContributorCreateDTO( String name, String tel) implements Serializable {
}