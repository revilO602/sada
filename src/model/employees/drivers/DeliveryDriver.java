package model.employees.drivers;

import model.ProductTransport;
import model.storage.Product;
import model.vehicles.Car;


/**
 * Driver that delivers products to the customers after they're sold.
 * All delivery drivers have cars, and they have to traverse a distance of 3 to deliver a product.
 *
 * @author leontiev
 */
public class DeliveryDriver extends Driver {
    private Product currentDelivery;
    private int currentDeliveryProgress=0;

    /**
     * Constructor for DeliveryDriver.
     *
     * @param name name of the new delivery driver.
     */
    public DeliveryDriver(String name){
        super(name);
        this.setVehicle(new Car());
        this.setRequiredDistance(3);
    }

    /**
     * Sets the actionHandler for the delivery driver to be Delivery.
     *
     * @param productTransport aggregates all actionHandlers for drivers.
     */
    @Override
    public void pickActionHandler(ProductTransport productTransport) {
        this.setActionHandler(productTransport.getDelivery());
    }

    /**
     * The delivery driver starts the delivery of a product.
     * The delivery driver can only deliver as much product at once as is his cars capacity. Therefore, it will take him
     * longer to deliver a large amount of product.
     *
     * @param product the product that the driver started delivering.
     */
    @Override
    public void send(Product product) {
        super.send(product);
        this.currentDeliveryProgress=0;
        this.currentDelivery=new Product(product, product.getAmount());
        product.setAmount(Math.min(this.getVehicle().getCapacity(),
                this.currentDelivery.getAmount()));

    }

    /**
     * Finishes one delivery of a product by the delivery driver.
     * The whole delivery request is finished only if the entire requested amount has been delivered.
     */
    @Override
    protected void finish() {
        this.setTravelledDistance(0);
        this.currentDeliveryProgress+=this.getProduct().getAmount();
        if (this.currentDelivery.getAmount()==this.currentDeliveryProgress){
            this.setProduct(this.currentDelivery);
            super.finish();
        }
        else{
            this.getProduct().setAmount(Math.min(this.getVehicle().getCapacity(),
                    this.currentDelivery.getAmount()-this.currentDeliveryProgress));
        }
    }

    /**
     * Advances the day for the delivery driver and his car.
     */
    @Override
    public void nextDay() {
        super.nextDay();
        if (!this.isOnRoute() && this.getVehicle().isAvailable())
            this.nextDelivery();
    }

    /**
     * The takes on a new delivery request from the queue.
     */
    public void nextDelivery(){
        Product product = this.getRequestedProducts().getNextDelivery();
        if (product != null){
            this.send(product);
        }
    }

}