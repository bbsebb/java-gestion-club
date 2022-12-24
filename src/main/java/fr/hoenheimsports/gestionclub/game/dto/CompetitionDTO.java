package fr.hoenheimsports.gestionclub.game.dto;

import javax.validation.Valid;
import java.io.Serializable;

/**
 * A DTO for the {@link fr.hoenheimsports.gestionclub.game.model.Competition} entity
 */
public record CompetitionDTO(long id, String name, @Valid PoolDTO pool) implements Serializable {
}