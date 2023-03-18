package model.employeeActionHandlers;

import model.employees.Employee;

import model.IEmployeeGroup;
import model.employees.Salesman;
import javafx.scene.control.TextArea;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles the sale of products by salesmen.
 *
 * @author leontiev
 */
public class Sale implements IEmployeeGroup, IEmployeeActionHandler, Serializable {
    private final List<Salesman> salesmen = new ArrayList<>();
    transient private TextArea out;

    /**
     * Sets the text area to which sale will display messages.
     *
     * @param out the text area to which the sale outputs messages.
     */
    @Override
    public void setOut(TextArea out) {
        this.out = out;
    }

    /**
     * Outputs all the current salesmen information.
     */
    public void tellSalesmen() {
        StringBuilder message = new StringBuilder("Current salesmen are:\n");
        for (Salesman s : this.salesmen)
            message.append(s.getName()).append(", ").append(s.getCut()).append("% cut\n");
        this.newMessage(message.toString());
    }

    /**
     * Displays a new message.
     *
     * @param message the text of the message.
     */
    @Override
    public void newMessage(String message) {
        this.out.appendText(message);
    }

    /**
     * Displays information about the start of a sale.
     *
     * @param employee the salesman that sold a product.
     */
    @Override
    public void actionStart(Employee employee) {
        Salesman salesman = (Salesman) employee;
        this.newMessage(salesman.getName() + " sold "
                + salesman.getProduct().getAmount()
                + " " + salesman.getProduct().getName() + " and made "
                + salesman.calcCut(salesman.getProduct().getAmount(), salesman.getProduct().getPrice())+
                " € for the company.\n");
    }

    /**
     * Adds a new salesman.
     *
     * @param employee new salesman to be added.
     */
    @Override
    public void addEmployee(Employee employee) {
        employee.setActionHandler(this);
        this.salesmen.add((Salesman)employee);
    }

    /**
     * Removes a salesman.
     *
     * @param employeeName name of the salesman to be removed.
     */
    @Override
    public void removeEmployee(String employeeName) {
        Salesman salesman = (Salesman) this.findEmployee(employeeName);
        if (salesman!=null){
            this.salesmen.remove(salesman);
        }
    }

    /**
     * Finds a salesman by name.
     *
     * @param employeeName name of the salesman to be found.
     * @return             the found salesman.
     */
    @Override
    public Employee findEmployee(String employeeName) {
        for (Salesman s : this.salesmen)
            if (s.getName().equals(employeeName)) {
                return s;
            }
        return null;
    }

}


