package fr.hoenheimsports.gestionclub.csvimport.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * A DTO for the {@link fr.hoenheimsports.gestionclub.game.model.Pool} entity
 */
public record PoolCreateDTO(@NotNull String num, String name) implements Serializable {
}