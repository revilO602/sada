package model.storage;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Holds and handles all requests for the transportation of products.
 *
 * @author leontiev
 */
public class RequestedProducts implements Serializable {
    private final Queue<Product> supplyQueue = new LinkedList<>();
    private final Queue<Product> deliveryQueue = new LinkedList<>();

    /**
     * Produces the next delivery request from the queue.
     *
     * @return next product to be delivered.
     */
    public Product getNextDelivery(){
        return this.deliveryQueue.poll();
    }

    /**
     * Produces the next resupply request from the queue.
     * Only gives the amount that the driver can transport at once.
     *
     * @param carCapacity amount that the driver can transport at once.
     * @return            next product to be resupplied, null if there's no request.
     */
    public Product getNextResupply(int carCapacity){
        Product nextProduct = this.supplyQueue.peek();
        if (nextProduct!=null){
            if (nextProduct.getAmount() < carCapacity) {
                carCapacity=nextProduct.getAmount();
            }
            Product readyProduct= nextProduct.take(carCapacity);
            if(nextProduct.getAmount()==0){
                this.supplyQueue.poll();
            }
            return readyProduct;
        }
        return null;
    }

    /**
     * Add a new request for delivery of a product to the delivery queue.
     *
     * @param product product to be added to the delivery queue.
     */
    public void addDeliveryRequest(Product product){
        deliveryQueue.add(product);
    }

    /**
     * Add a new request to resupply a product to the resupply queue.
     *
     * @param product product to be added to the supply queue.
     */
    public void addResupplyRequest(Product product){
        supplyQueue.add(product);
    }
}
