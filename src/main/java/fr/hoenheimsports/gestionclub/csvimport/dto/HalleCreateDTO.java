package fr.hoenheimsports.gestionclub.csvimport.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link fr.hoenheimsports.gestionclub.game.model.Halle} entity
 */

public record HalleCreateDTO( String name, String addressStreet, int addressCp, String addressCity,
                             String glue) implements Serializable {
}