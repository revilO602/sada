package model;

import model.employees.Employee;

/**
 * Interface for handling a group of employees.
 *
 * @author leontiev
 */
public interface IEmployeeGroup {
    /**
     * Adds a new employee to the group.
     *
     * @param employee new employee to be added.
     */
    void addEmployee(Employee employee);

    /**
     * Remove (fire) employee by name.
     *
     * @param employeeName name of the employee to be removed.
     */
    void removeEmployee(String employeeName);

    /**
     * Find employee by name.
     *
     * @param employeeName name of the employee to be found.
     * @return             the found employee.
     */
    Employee findEmployee(String employeeName);
}
