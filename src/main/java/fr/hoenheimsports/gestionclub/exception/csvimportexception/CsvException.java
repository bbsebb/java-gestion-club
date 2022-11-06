package fr.hoenheimsports.gestionclub.exception.csvimportexception;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class CsvException extends Exception{

    public CsvException() {
        super();
    }

    public CsvException(String message) {
        super(message);
        log.error(message);
    }

    public CsvException(String message, Throwable cause) {
        super(message, cause);
        log.error(message);
    }

    public CsvException(Throwable cause) {
        super(cause);
    }
}
