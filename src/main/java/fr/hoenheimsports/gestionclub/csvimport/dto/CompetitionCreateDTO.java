package fr.hoenheimsports.gestionclub.csvimport.dto;

import javax.validation.Valid;
import java.io.Serializable;

/**
 * A DTO for the {@link fr.hoenheimsports.gestionclub.game.model.Competition} entity
 */
public record CompetitionCreateDTO(String name, @Valid PoolCreateDTO pool) implements Serializable {
}