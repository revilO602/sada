package model.employees.drivers;

import model.employees.Employee;
import model.ProductTransport;
import model.storage.RequestedProducts;
import model.storage.Product;
import model.vehicles.IVehicle;

/**
 * An employed driver that transports products in his vehicle.
 *
 * @author leontiev
 */
public abstract class Driver extends Employee {
    /**
     * Distance of 1 takes 1 day to traverse at speed 1.
     */
    private int requiredDistance;
    private int travelledDistance = 0;
    private boolean onRoute = false;
    private Product product;
    private RequestedProducts requestedProducts;
    private IVehicle vehicle;


    /**
     * Constructor for Driver.
     *
     * @param name name of the new driver.
     */
    public Driver(String name){
        super(name);
    }

    /**
     * Driver picks the correct actionHandler for his type from productTransport.
     * This is abstract in Driver because Driver is also abstract,
     * therefore Driver doesn't have an actionHandler to pick.
     *
     * @param productTransport aggregates all actionHandlers for drivers.
     */
    public abstract void pickActionHandler(ProductTransport productTransport);

    /**
     * Returns the product the driver is currently transporting.
     *
     * @return product that's currently being transported.
     */
    public Product getProduct() {
        return this.product;
    }

    /**
     * Sets the product the driver is currently transporting.
     *
     * @param product product that's currently being transported.
     */
    public void setProduct(Product product) {
        this.product = product;
    }

    /**
     * Returns the vehicle of the driver.
     *
     * @return vehicle of the driver.
     */
    public IVehicle getVehicle() {
        return vehicle;
    }

    /**
     * Sets the vehicle of the driver.
     *
     * @param vehicle vehicle of the driver.
     */
    public void setVehicle(IVehicle vehicle) {
        this.vehicle = vehicle;
    }

    /**
     * Returns an object that contains all queued requested products for transport.
     *
     * @return object that contains requested products for transport in a queue.
     */
    public RequestedProducts getRequestedProducts() {
        return this.requestedProducts;
    }

    /**
     * Sets requestedProducts for the driver, from which he can get the next request in the queue.
     *
     * @param requestedProducts object that contains requested products for transport and can provide the next request.
     */
    public void setRequestedProducts(RequestedProducts requestedProducts) {
        this.requestedProducts = requestedProducts;
    }

    /**
     * Sets the distance the driver has to traverse to finish transporting a product.
     *
     * @param distance = days*speed that it takes to finish transport of a product.
     */
    public void setRequiredDistance(int distance){
        this.requiredDistance=distance;
    }

    /**
     * Sets the distance the driver has travelled while transporting the current product.
     *
     * @param distance the distance the driver has travelled while transporting the current product.
     */
    public void setTravelledDistance(int distance) {
        this.travelledDistance = distance;
    }

    /**
     * Returns true if driver is currently transporting a product.
     *
     * @return true if driver is currently transporting a product, otherwise false.
     */
    public boolean isOnRoute() {
        return onRoute;
    }

    /**
     * Advances the day for the driver and his vehicle.
     * If the driver reached his distance, then the transportation is finished.
     */
    public void nextDay(){
        this.vehicle.nextDay(this);
        if (this.onRoute) {
            this.travelledDistance+=this.vehicle.getSpeed();
            if (this.travelledDistance >= this.requiredDistance)
                this.finish();
        }

    }

    /**
     * Start the transportation of a product.
     * The driver and his vehicle become unavailable.
     *
     * @param product the product that the driver started transporting.
     */
    public void send(Product product){
        this.onRoute = true;
        this.vehicle.setAvailable(false);
        this.product=product;
        this.getActionHandler().actionStart(this);
    }

    /**
     * Finish the transportation of a product.
     * The driver and his vehicle become available.
     */
    protected void finish(){
        this.travelledDistance=0;
        this.onRoute = false;
        this.vehicle.setAvailable(true);
        this.getActionHandler().actionFinish(this);
    }

    /**
     * Outputs new information from the vehicle through the actionHandler.
     *
     * @param info new information about the state of the vehicle (accident, traffic jam, service).
     */
    public void receiveCarInfo(String info){
        this.getActionHandler().newMessage(info);
    }

}
