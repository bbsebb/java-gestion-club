package fr.hoenheimsports.gestionclub.csvimport.repository;

import fr.hoenheimsports.gestionclub.csvimport.model.CSVFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CSVFileRepository extends JpaRepository<CSVFile, Long> {
}