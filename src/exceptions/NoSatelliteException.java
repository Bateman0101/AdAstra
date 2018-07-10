package exceptions;

public class NoSatelliteException extends Exception {

    public NoSatelliteException(String message) {
        super("Problem adding this occurrence to the database: " + message);
    }
}
