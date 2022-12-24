package fr.hoenheimsports.gestionclub.game.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link fr.hoenheimsports.gestionclub.game.model.Contributor} entity
 */
public record ContributorDTO(long id, String name, String tel) implements Serializable {
}