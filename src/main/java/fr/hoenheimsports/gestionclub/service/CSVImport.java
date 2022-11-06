package fr.hoenheimsports.gestionclub.service;

import fr.hoenheimsports.gestionclub.exception.csvimportexception.CsvException;
import org.springframework.stereotype.Service;

@Service
public interface CSVImport {
    void extract(String resourceString) throws CsvException;
}
