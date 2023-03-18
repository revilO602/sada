package model.storage;

import java.io.Serializable;

/**
 * Product that can be sold and transported.
 *
 * @author leontiev
 */
public class Product implements Serializable {
    private double price;
    private int amount;
    private String name;
    private int batch;

    /**
     * Constructor for Product.
     *
     * @param name   name of the product.
     * @param amount amount of the new product.
     * @param price  price of the product.
     * @param batch  the amount to be resupplied once the product runs out.
     */
    public Product(String name, int amount, double price,int batch){
        this.name=name;
        this.amount=amount;
        this.price=price;
        this.batch=batch;
    }

    /**
     * Copy constructor of Product.
     *
     * @param product Product to be copied.
     * @param amount  amount of the new product.
     */
    public Product(Product product, int amount){
        this.price = product.price;
        this.amount = amount;
        this.name = product.name;
        this.batch = product.batch;
    }

    /**
     * Returns the name of the product.
     *
     * @return the name of the product.
     */
    public String getName(){ return this.name; }

    /**
     * Returns the price of the product.
     *
     * @return the price of the product.
     */
    public double getPrice(){ return this.price; }

    /**
     * Returns how much of the product is to be resupplied once it runs out.
     *
     * @return the amount to be resupplied once the product runs out.
     */
    public int getBatch(){ return this.batch; }

    /**
     * Returns the amount of the product.
     *
     * @return the amount of product.
     */
    public int getAmount(){ return this.amount; }

    /**
     * Sets the amount of the product.
     *
     * @param amount the amount of the product.
     */
    public void setAmount(int amount){this.amount=amount; }

    /**
     * Increases the amount of the product.
     *
     * @param amount amount to be added to the product.
     */
    public void add(int amount){ this.amount += amount; }

    /**
     * Returns a copy of the product with the requested amount.
     *
     * @param amount amount of product to be taken.
     * @return       copy of the product with the requested amount, if there's not enough of the product returns null.
     */
    public Product take(int amount){
        Product product;
        if (this.amount - amount >=0) {
            this.amount -= amount;
            product = new Product(this, amount);
            product.setAmount(amount);
            return product;
        }
        return null;
    }

}
