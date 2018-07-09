package exceptions;

public class NoUserException extends Exception{

    public NoUserException(String message){
        super("Problem adding this occurrence to the database: " + message);
    }
}
