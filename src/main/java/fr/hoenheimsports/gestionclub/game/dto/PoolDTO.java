package fr.hoenheimsports.gestionclub.game.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * A DTO for the {@link fr.hoenheimsports.gestionclub.game.model.Pool} entity
 */
public record PoolDTO(@NotNull String num, String name) implements Serializable {
}