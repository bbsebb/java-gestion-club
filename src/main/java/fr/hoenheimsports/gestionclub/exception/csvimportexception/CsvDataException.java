package fr.hoenheimsports.gestionclub.exception.csvimportexception;

public class CsvDataException extends CsvException {
    public CsvDataException() {
        super();
    }

    public CsvDataException(String message) {
        super(message);
    }

    public CsvDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public CsvDataException(Throwable cause) {
        super(cause);
    }
}
