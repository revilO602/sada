package model.employeeActionHandlers;

import model.employees.drivers.Driver;
import model.employees.Employee;
import model.storage.Product;
import javafx.scene.control.TextArea;

import java.io.Serializable;

/**
 * Produces information about the delivery of products.
 *
 * @author leontiev
 */
public class Delivery implements IEmployeeActionHandler, Serializable {
    transient private TextArea out;


    /**
     * Sets the text area to which delivery will display messages.
     *
     * @param out the text area to which this object outputs messages.
     */
    @Override
    public void setOut(TextArea out) {
        this.out = out;
    }


    /**
     * Displays a new message.
     *
     * @param message the text of the message.
     */
    @Override
    public void newMessage(String message){
        this.out.appendText(message);
    }


    /**
     * Displays a message about the start of a delivery.
     *
     * @param employee the delivery driver that started a delivery.
     */
    @Override
    public void actionStart(Employee employee){
        Driver driver = (Driver) employee;
        this.newMessage("Delivery driver "+driver.getName() + " began delivery of "
                + driver.getProduct().getAmount()+" "+driver.getProduct().getName() + ".\n") ;
    }

    /**
     * Displays a message about the end of a delivery.
     *
     * @param employee the delivery driver that finished a delivery.
     */
    @Override
    public void actionFinish(Employee employee) {
        Driver driver = (Driver) employee;
        Product product = driver.getProduct();
        this.newMessage(driver.getName() + " finished a delivery of "
                +product.getAmount()+" " + product.getName()+".\n");
    }

}
