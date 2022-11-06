package fr.hoenheimsports.gestionclub.service;

import fr.hoenheimsports.gestionclub.exception.csvimportexception.CsvException;


public interface CSVImport {
    void extract(String resourceString) throws CsvException;
}
