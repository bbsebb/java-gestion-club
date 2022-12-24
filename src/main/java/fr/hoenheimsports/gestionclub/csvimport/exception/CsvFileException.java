package fr.hoenheimsports.gestionclub.csvimport.exception;

public class CsvFileException extends CsvException {
    public CsvFileException() {
        super();
    }

    public CsvFileException(String message) {
        super(message);
    }

    public CsvFileException(String message, Throwable cause) {
        super(message, cause);
    }

    public CsvFileException(Throwable cause) {
        super(cause);
    }
}
