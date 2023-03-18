package model.employeeActionHandlers;

import model.employees.Employee;
import javafx.scene.control.TextArea;

/**
 * Handles actions of employees (outputs messages about them).
 *
 * @author leontiev
 */
public interface IEmployeeActionHandler{

    /**
     * Handles the start of an action by an employee (produces a message about it).
     *
     * @param employee the employee that started the action.
     */
    void actionStart(Employee employee);

    /**
     * Handles the end of an action by an employee (produces a message about it).
     *
     * @param employee the employee that finished an action.
     */
    // explicit use of default method implementation
    default void actionFinish(Employee employee){
        actionStart(employee);
    }

    /**
     * Sets the text area to which this object outputs messages.
     *
     * @param out the text area to which this object outputs messages.
     */
    void setOut(TextArea out);

    /**
     * Creates a new message about the action of the employee.
     *
     * @param message the text of the message.
     */
    void newMessage(String message);
}
