package fr.hoenheimsports.gestionclub.csvimport.service;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import fr.hoenheimsports.gestionclub.csvimport.dto.CSVFileDTO;
import fr.hoenheimsports.gestionclub.csvimport.exception.CsvDataException;
import fr.hoenheimsports.gestionclub.csvimport.exception.CsvFileException;
import org.apache.commons.io.input.BOMInputStream;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.List;

@Service
public class CSVFileService {
    public CSVFileDTO extract(String path) throws CsvDataException, CsvFileException {
        Resource resource = new ClassPathResource(path);
        CSVFileDTO csvFileDTO = new CSVFileDTO(path, LocalDate.now());
        try (InputStreamReader reader = new InputStreamReader(new BOMInputStream(resource.getInputStream()))) {
            // Création du CSV READER
            try (CSVReader csvReader = new CSVReaderBuilder(reader).withCSVParser(new CSVParserBuilder().withSeparator(';').build()).build()) {
                List<String[]> csvLines;
                csvLines = csvReader.readAll();
                if (csvLines == null || csvLines.isEmpty()) {
                    throw new CsvDataException("Fichier vide" + path);
                } else {

                }
            }
                return csvFileDTO;
        } catch (FileNotFoundException fnfe) {
            throw new CsvFileException("Fichier introuvable : " + path, fnfe);
        } catch (IOException ioe) {
            throw new CsvFileException("Erreur de lecture du fichier" + path, ioe);
        } catch (com.opencsv.exceptions.CsvException e) {
            throw new CsvFileException("Erreur de parsage du framework CSV" + path, e);
        }


    }
}
