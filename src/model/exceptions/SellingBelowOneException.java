package model.exceptions;

/**
 * Exception that's thrown when less than one product is attempted to be sold.
 *
 * @author leontiev
 */
public class SellingBelowOneException extends NumberFormatException {
    /**
     * Constructor for SellingBelowOneException.
     *
     * @param message message to be displayed when the exception is thrown.
     */
    public SellingBelowOneException(String message){
        super(message);
    }
}
