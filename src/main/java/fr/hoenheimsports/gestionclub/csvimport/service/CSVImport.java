package fr.hoenheimsports.gestionclub.csvimport.service;

import fr.hoenheimsports.gestionclub.csvimport.exception.CsvException;


public interface CSVImport {
    void extract(String resourceString) throws CsvException;
}
