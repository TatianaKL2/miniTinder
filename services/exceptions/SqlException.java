package kg.megacom.miniTinder.services.exceptions;

public class SqlException extends RuntimeException{
    public SqlException (String message) {
        super(message);
    }
}
