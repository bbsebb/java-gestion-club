package fr.hoenheimsports.gestionclub.csvimport.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * A DTO for the {@link fr.hoenheimsports.gestionclub.csvimport.model.CSVFile} entity
 */
@Data
public class CSVFileDTO implements Serializable {
    private final String filename;
    private final LocalDate datetimeUpload;

    private List<String> header;
    private List<Map<String,String>> data;
}