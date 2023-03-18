package model.exceptions;

/**
 * Exception that's thrown when an employee with a name that's already taken is attempted to be added.
 *
 * @author leontiev
 */
public class EmployeeAlreadyExistsException extends IllegalArgumentException {
    /**
     * Constructor for EmployeeAlreadyExistsException.
     *
     * @param message message to be displayed when the exception is thrown.
     */
    public EmployeeAlreadyExistsException(String message){
        super(message);
    }
}
