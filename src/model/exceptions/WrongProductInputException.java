package model.exceptions;

/**
 * Exception that's thrown when an incorrect amount or name of a product is used.
 *
 * @author leontiev
 */
public class WrongProductInputException extends IllegalArgumentException {
    /**
     * Constructor for WrongProductInputException.
     *
     * @param message message to be displayed when the exception is thrown.
     */
    public WrongProductInputException(String message){
        super(message);
    }
}
