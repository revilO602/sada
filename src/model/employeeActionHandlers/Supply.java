package model.employeeActionHandlers;


import model.employees.Employee;
import model.storage.*;
import model.employees.drivers.*;
import javafx.scene.control.TextArea;

import java.io.Serializable;

/**
 * Handles the supply of products to the warehouse.
 *
 * @author leontiev
 */
public class Supply implements IEmployeeActionHandler, Serializable {
    private Warehouse warehouse = new Warehouse();
    transient private TextArea out;

    /**
     * Sets the text area to which the supply will display messages.
     *
     * @param out the text area to which the supply outputs messages.
     */
    @Override
    public void setOut(TextArea out) {
        this.out = out;
    }

    /**
     * Returns the warehouse.
     *
     * @return the warehouse that stores products.
     */
    public Warehouse getWarehouse(){
        return this.warehouse;
    }

    /**
     * Sets the warehouse that is being supplied.
     *
     * @param warehouse the warehouse that stores products.
     */
    public void setWarehouse(Warehouse warehouse){
        this.warehouse=warehouse;
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
     * Takes a product from the warehouse.
     *
     * @param productName name of the product.
     * @param amount      the amount to be taken.
     * @return            requested product.
     */
    public Product takeProduct(String productName, int amount){
        return this.warehouse.removeProduct(productName,amount);
    }

    /**
     * Outputs information about all the products stored in the warehouse.
     */
    public void tellStoredProducts(){
        this.newMessage(this.warehouse.tellProducts());
    }

    /**
     * Starts a resupply request.
     *
     * @param employee the supply driver that started resupplying the warehouse.
     */
    @Override
    public void actionStart(Employee employee){
        Driver driver = (Driver) employee;
        this.newMessage("Supply driver " + driver.getName() +
                " set out to resupply " + driver.getProduct().getName() + ".\n");
    }


    /**
     * Finishes supplying the warehouse with product.
     *
     * @param employee the supply driver that finished supplying the warehouse.
     */
    @Override
    public void actionFinish(Employee employee) {
        Driver driver = (Driver) employee;
        Product product = driver.getProduct();
        this.newMessage(driver.getName() + " returned with a batch of "
                + product.getAmount() + " " + product.getName() + ".\n");
        this.warehouse.addProduct(product);
    }

}

