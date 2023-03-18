package model.storage;

import model.observerPattern.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The warehouse holds all the products.
 *
 * @author leontiev
 */
public class Warehouse implements IObservable, IProductGroup, Serializable {
    private final List<Product> products = new ArrayList<>();
    private final List<IObserver> observers = new ArrayList<>();


    /**
     * Returns the name and amount of all the products.
     *
     * @return string containing the information about all the products.
     */
    public String tellProducts(){
        StringBuilder message = new StringBuilder("Products in stock:\n");
        for ( Product p : this.products)
            message.append(p.getAmount()).append(" ").append(p.getName()).append("\n");

        return message.toString();
    }

    /**
     * Adds a new observer of the warehouse.
     *
     * @param observer new observer.
     */
    @Override
    public void addObserver(IObserver observer){
        this.observers.add(observer);
    }

    /**
     * Notifies all observers that something relevant has changed in the warehouse.
     */
    @Override
    public void notifyObservers(){
        for (IObserver o: observers){
            o.update(this);
        }
    }

    /**
     * Adds a product to the warehouse.
     * If the product already exists, only increase its amount.
     *
     * @param product the product to be added.
     */
    @Override
    public void addProduct(Product product){
        for (Product p: this.products)
            if (p.getName().equals(product.getName())) {
                p.add(product.getAmount());
                return;
            }
        this.products.add(product);
    }

    /**
     * Deletes all the product with the given name.
     *
     * @param productName the name of the product to be deleted.
     */
    @Override
    public void deleteProduct(String productName){
        Product product = this.findProduct(productName);
        this.products.remove(product);
    }

    /**
     * Removes product from the warehouse.
     * If a product ran out, notifies observers of this fact.
     *
     * @param productName name of the product to be removed.
     * @param amount      amount to be removed.
     * @return            the removed product or null if the product doesn't exist or there's not enough of it.
     */
    @Override
    public Product removeProduct(String productName, int amount){
        Product product = this.findProduct(productName);
        try{
            Product removedProduct = product.take(amount);
            if (product.getAmount()==0 && product.getBatch()>0){
                this.notifyObservers();
                this.products.remove(product);
            }
            return removedProduct;
        }
        catch(NullPointerException e){
            return null;
        }
    }

    /**
     * Finds product by the given name.
     *
     * @param productName name of the product to be found.
     * @return            the found product.
     */
    @Override
    public Product findProduct(String productName){
        for (Product p : this.products)
            if (p.getName().equals(productName))
                return p;
        return null;
    }

    /**
     * Finds the first product with the given amount.
     * Used mostly to look for a sold-out product (amount == 0).
     *
     * @param amount amount of the product to be found.
     * @return       the found product.
     */
    public Product findProduct(int amount){
        for (Product p : this.products)
            if (p.getAmount()==amount)
                return p;
        return null;
    }

}
