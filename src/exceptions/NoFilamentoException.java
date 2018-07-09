package exceptions;

public class NoFilamentoException extends Exception{

    public NoFilamentoException(String message){
        super("Problem adding this occurrence to the database: " + message);
    }
}
