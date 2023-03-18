package model;

import model.employeeActionHandlers.Delivery;
import model.employeeActionHandlers.Supply;
import model.employees.drivers.*;
import model.employees.Employee;
import model.observerPattern.*;
import model.storage.Product;
import model.storage.RequestedProducts;
import model.storage.Warehouse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * Manages the transport of products.
 * Aggregates all the drivers.
 *
 * @author leontiev
 */
public class ProductTransport implements IObserver, IEmployeeGroup,  Serializable {
    private final List<Driver> drivers = new ArrayList<>();
    private final Delivery delivery = new Delivery();
    private final Supply supply = new Supply();
    private final RequestedProducts requestedProducts = new RequestedProducts();

    /**
     * Constructor for ProductTransport.
     */
    public ProductTransport(){
        this.supply.getWarehouse().addObserver(this);
    }

    /**
     * Returns the warehouse.
     *
     * @return the warehouse.
     */
    public Warehouse getWarehouse(){
        return this.supply.getWarehouse();
    }

    /**
     * Returns the delivery.
     *
     * @return the delivery.
     */
    public Delivery getDelivery(){
        return this.delivery;
    }

    /**
     * Returns the supply.
     *
     * @return the supply.
     */
    public Supply getSupply(){
        return this.supply;
    }

    /**
     * Takes a product from the supply.
     *
     * @param productName name of the product to be taken.
     * @param amount      amount of the product to be taken.
     * @return            the taken product.
     */
    public Product takeProduct(String productName, int amount){
        return this.supply.takeProduct(productName,amount);
    }

    /**
     * Tells supply to display information about the stored products.
     */
    public void tellStoredProducts() {
        this.supply.tellStoredProducts();
    }


    /**
     * Gets called when a product ran out in the warehouse, starts the resupply process.
     *
     * @param warehouse where the product ran out.
     */
    @Override
    public void update(IObservable warehouse){
        this.supply.setWarehouse((Warehouse) warehouse);
        Product product = this.supply.getWarehouse().findProduct(0);
        this.resupply(product);
    }

    /**
     * Advances the day in all drivers.
     */
    public void nextDay(){
        for (Driver d : drivers)
            d.nextDay();
    }

    /**
     * Starts delivery of product.
     * If there's no available delivery driver, adds the product into queue.
     *
     * @param product product to be delivered.
     */
    public void deliver(Product product){
        this.requestedProducts.addDeliveryRequest(product);
        DeliveryDriver driver=(DeliveryDriver)findDriver(DeliveryDriver.class);
        if (driver==null){
            this.delivery.newMessage("Request for delivery of "+
                    product.getAmount()+" "+product.getName()
                    +" added to queue.\n");
        }
        else driver.nextDelivery();
    }

    /**
     * Starts the resupplying of a product.
     * Keeps sending supply drivers until the request is taken care of or there's no available supply driver left.
     * If there's no supply driver available adds the request to queue.
     *
     * @param product product to be resupplied.
     */
    public void resupply(Product product){
        product.setAmount(product.getBatch());
        this.requestedProducts.addResupplyRequest(product);
        boolean requestFinished = false;
        while(!requestFinished && product.getAmount() > 0) {
            SupplyDriver driver = (SupplyDriver) findDriver(SupplyDriver.class);
            if (driver == null) {
                this.supply.newMessage("Request to resupply the warehouse with " +
                        product.getAmount() + " " + product.getName()
                        + " added to queue.\n");
                requestFinished = true;
            } else requestFinished = driver.nextResupply();
        }
    }

    /**
     * Finds an available driver of the given class.
     *
     * @param driverClass class of the driver to be found.
     * @return            the found driver or null if no driver of given class is available.
     */
    // explicit use of RTTI
    public Driver findDriver(Class<?> driverClass){
        for (Driver d: this.drivers) {
            if (d.getClass()==driverClass && !d.isOnRoute() && d.getVehicle().isAvailable())
                return d;
        }
        return null;
    }

    /**
     * Adds a new driver.
     *
     * @param employee new driver to be added.
     */
    @Override
    public void addEmployee(Employee employee) {
        Driver driver = (Driver) employee;
        driver.pickActionHandler(this);
        driver.setRequestedProducts(this.requestedProducts);
        this.drivers.add(driver);
    }

    /**
     * Removes a driver.
     *
     * @param employeeName name of the driver to be removed.
     */
    @Override
    public void removeEmployee(String employeeName) {
        Driver driver =(Driver) this.findEmployee(employeeName);
        if (driver!=null){
            this.drivers.remove(driver);
        }
    }

    /**
     * Finds driver by name.
     *
     * @param employeeName name of the driver to be found.
     * @return             the found driver or null if he doesn't exist.
     */
    @Override
    public Employee findEmployee(String employeeName) {
        for (Driver d: this.drivers) {
            if (d.getName().equals(employeeName))
                return d;
        }
        return null;
    }

}