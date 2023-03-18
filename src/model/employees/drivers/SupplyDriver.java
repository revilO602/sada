package model.employees.drivers;

import model.ProductTransport;
import model.storage.Product;
import model.vehicles.Truck;


/**
 * Supply driver resupplies the warehouse with products that run out.
 * The supply driver must traverse a distance of 4 to resupply a product, and his vehicle is a truck.
 *
 * @author leontiev
 */
public class SupplyDriver extends Driver {

    /**
     * Constructor for Supply Driver.
     *
     * @param name name of the new supply driver.
     */
    public SupplyDriver(String name){
        super(name);
        this.setVehicle(new Truck());
        this.setRequiredDistance(4);
    }

    /**
     * The supply driver picks the Supply as his actionHandler.
     *
     * @param productTransport aggregates all actionHandlers for drivers.
     */
    @Override
    public void pickActionHandler(ProductTransport productTransport) {
        this.setActionHandler(productTransport.getSupply());
    }

    /**
     * Advances the day for the supply driver and his truck.
     */
    @Override
    public void nextDay() {
        super.nextDay();
        if (!this.isOnRoute() && this.getVehicle().isAvailable())
            this.nextResupply();
    }

    /**
     * The supply driver takes on the next resupply request.
     * The supply driver can only transport his cars capacity at once.
     *
     * @return true if the entire resupply request has been handled.
     */
    public boolean nextResupply(){
        Product product = this.getRequestedProducts().getNextResupply(this.getVehicle().getCapacity());
        if (product != null){
            this.send(product);
            return false;
        }
        else return true;
    }

}
