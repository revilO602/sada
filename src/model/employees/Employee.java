package model.employees;

import model.employeeActionHandlers.IEmployeeActionHandler;

import java.io.Serializable;

/**
 * Employee of the company.
 *
 * @author leontiev
 */
public abstract class Employee implements Serializable {
    private String name;
    private IEmployeeActionHandler actionHandler;

    /**
     * Constructor of Employee.
     *
     * @param name the name of the new employee.
     */
    public Employee(String name){
        this.name=name;
    }

    /**
     * Returns the name of the employee.
     *
     * @return the name of the employee.
     */
    public String getName(){
        return this.name;
    }

    /**
     * Sets the actionHandler for the employees actions.
     *
     * @param actionHandler the actionHandler for the employee.
     */
    public void setActionHandler(IEmployeeActionHandler actionHandler){
        this.actionHandler=actionHandler;
    }

    /**
     * Returns the actionHandler of the employee.
     *
     * @return the actionHandler of the employee.
     */
    public IEmployeeActionHandler getActionHandler(){
        return this.actionHandler;
    }
}
