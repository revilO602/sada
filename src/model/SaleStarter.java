package model;

import model.employeeActionHandlers.Sale;
import model.employees.*;
import model.exceptions.WrongProductInputException;
import model.exceptions.WrongSalesmanNameException;
import model.storage.Product;

import java.io.Serializable;


/**
 * Starts the sale of a product.
 *
 * @author leontiev
 */
public class SaleStarter implements Serializable {

    private final Sale sale = new Sale();
    private final ProductTransport productTransport = new ProductTransport();

    /**
     * Returns the sale object.
     *
     * @return the sale.
     */
    public Sale getSale(){
        return this.sale;
    }

    /**
     * Returns the productTransport object.
     *
     * @return the productTransport object.
     */
    public ProductTransport getProductTransport(){
        return this.productTransport;
    }


    /**
     * Starts the sale of product by a salesman.
     *
     * @param salesmanName name of the salesman that sold the product.
     * @param productName  name of the sold product.
     * @param amount       amount sold.
     * @throws WrongSalesmanNameException thrown when no such salesman exists.
     * @throws WrongProductInputException thrown when product doesn't exist or there's not enough.
     */
    public void startSale(String salesmanName, String productName, int amount)
        throws WrongSalesmanNameException, WrongProductInputException {
        Salesman salesman = (Salesman) this.sale.findEmployee(salesmanName);

        if(salesman == null)
            throw new WrongSalesmanNameException("That salesman doesn't exist!");
        Product product = this.productTransport.takeProduct(productName,amount);
        if(product == null)
            throw new WrongProductInputException("That product doesn't exist or there's not enough in stock!");

        salesman.sell(product);
        this.productTransport.deliver(product);
    }

}
