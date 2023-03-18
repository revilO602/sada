package model.employees;
import model.storage.Product;

/**
 * An employed salesman that sells products.
 *
 * @author leontiev
 */
public class Salesman extends Employee {
    private int cut;
    private Product product;

    /**
     * Constructor for Salesman.
     *
     * @param name name of the new salesman.
     * @param cut  the cut the salesman takes from every sale (percentage).
     */
    public Salesman(String name, int cut){
        super(name);
        this.cut=cut;
    }

    /**
     * Returns the cut of the salesman.
     *
     * @return the cut of the salesman (percentage).
     */
    public Integer getCut(){
        return this.cut;
    }

    /**
     * Returns the last product sold by the salesman.
     *
     * @return last product sold by the salesman.
     */
    public Product getProduct(){
      return this.product;
    }

    /**
     * Calculates how much money the salesman earned from a sale.
     *
     * @param amount amount of product sold.
     * @param price  price of the sold product.
     * @return       amount of money from the sale the salesman takes.
     */
    public double calcCut(int amount, double price){
        double cut = 100-this.cut;
        cut = cut/100;
        return amount * price * cut;
    }

    /**
     * The salesman sells a product.
     *
     * @param product the product that the salesman sold.
     */
    public void sell(Product product){
        this.product=product;
        this.getActionHandler().actionStart(this);
    }
}
