package model.exceptions;

/**
 * Exception that's thrown when an incorrect name of a salesman is used.
 *
 * @author leontiev
 */
public class WrongSalesmanNameException extends IllegalArgumentException {
    /**
     * Constructor for WrongSalesmanNameException.
     *
     * @param message message to be displayed when the exception is thrown.
     */
    public WrongSalesmanNameException(String message){
        super(message);
    }
}
