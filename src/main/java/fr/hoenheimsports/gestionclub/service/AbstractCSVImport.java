package fr.hoenheimsports.gestionclub.service;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import fr.hoenheimsports.gestionclub.exception.csvimportexception.CsvDataException;
import fr.hoenheimsports.gestionclub.exception.csvimportexception.CsvException;
import fr.hoenheimsports.gestionclub.exception.csvimportexception.CsvFileException;
import fr.hoenheimsports.gestionclub.service.util.ExtractInfoTeam;
import lombok.AllArgsConstructor;
import org.apache.commons.io.input.BOMInputStream;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

 @AllArgsConstructor
abstract public class AbstractCSVImport implements CSVImport {
    final protected String[] header;
    final protected GameService GameService;
    final protected CompetitionService competitionService;
    final protected PoolService poolService;
    final protected RefereeService refereeService;
    final protected HalleService halleService;
    final protected TeamService teamService;
    final protected ClubService clubService;
    final protected CategoryService categoryService;
    final protected ExtractInfoTeam extractorHome;
    final protected ExtractInfoTeam extractorVisiting;



     protected Map<String, Integer> getHeaderMap(String[] header) {
        Map<String, Integer> headerMap = new Hashtable<>();
        for (int i = 0; i < this.header.length; i++) {
            headerMap.put(header[i], i);
        }
        return headerMap;
    }

    protected List<String[]> csvFileToListLines(String resourceString) throws CsvException {
        List<String[]> csvLines;
        Resource resource = new ClassPathResource(resourceString);
        try (InputStreamReader reader = new InputStreamReader(new BOMInputStream(resource.getInputStream()))) {
            // Création du CSV READER
            try(CSVReader csvReader = new CSVReaderBuilder(reader).withCSVParser(new CSVParserBuilder().withSeparator(';').build()).build()) {
                csvLines = csvReader.readAll();
            }
        } catch (FileNotFoundException fnfe) {
            throw new CsvFileException("Fichier introuvable : " + resourceString, fnfe);
        } catch (IOException ioe) {
            throw new CsvFileException("Erreur de lecture du fichier" + resourceString, ioe);
        } catch (com.opencsv.exceptions.CsvException e) {
            throw new CsvFileException("Erreur de parsage du framework CSV" + resourceString, e);
        }
        if (csvLines == null || csvLines.isEmpty()) {
            throw new CsvDataException("Fichier vide" + resourceString);
        }
        return csvLines;
    }


}
