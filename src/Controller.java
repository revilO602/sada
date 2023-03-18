import model.employeeActionHandlers.*;
import model.exceptions.*;
import model.objectManagment.*;
import model.SaleStarter;

/**
 * Connects the model to the users input in View.
 *
 * @author leontiev
 */
public class Controller {
    private SaleStarter saleStarter;
    private ObjectCreator objectCreator;
    private ObjectDeleter objectDeleter;
    private final Serializer serializer;

    /**
     * Constructor for Controller.
     */
    public Controller(){
        this.saleStarter=new SaleStarter();
        this.objectCreator =new ObjectCreator(saleStarter.getProductTransport(),saleStarter.getSale(),
                saleStarter.getProductTransport().getWarehouse());
        this.objectDeleter = new ObjectDeleter(saleStarter.getProductTransport(),saleStarter.getSale(),
                saleStarter.getProductTransport().getWarehouse());
        this.serializer=new Serializer(this.saleStarter);
    }

    /**
     * Starts the sale using input from the user.
     *
     * @param salesmanName name of the salesman that sold a product.
     * @param productName  name of the sold product.
     * @param amount       amount sold.
     * @throws WrongSalesmanNameException thrown if the salesman doesn't exist.
     * @throws WrongProductInputException thrown if the product doesn't exist or there's not enough of it.
     * @throws SellingBelowOneException   thrown when less than one piece of product is attempted to be sold.
     */
    public void sale (String salesmanName, String productName, int amount)
            throws WrongSalesmanNameException, WrongProductInputException, SellingBelowOneException {
        if (amount < 1) {
            throw new SellingBelowOneException("Can't sell less than one piece of product!");
        }
        this.saleStarter.startSale(salesmanName, productName, amount);
    }

    /**
     * Tells product transport to display information about all stored products.
     */
    public void tellStock(){
        this.saleStarter.getProductTransport().tellStoredProducts();
    }

    /**
     * Tells sale to display information about all salesmen.
     */
    public void tellSalesman(){
       this.saleStarter.getSale().tellSalesmen();
    }

    /**
     * Advances the day in product transport.
     */
    public void nextDay(){
        this.saleStarter.getProductTransport().nextDay();
    }

    /**
     * Gets the supply information from product transport.
     *
     * @return the supply object.
     */
    public Supply getSupply(){
       return this.saleStarter.getProductTransport().getSupply();
    }

    /**
     * Gets the delivery information from product transport.
     *
     * @return the delivery object.
     */
    public Delivery getDelivery(){
        return this.saleStarter.getProductTransport().getDelivery();
    }

    /**
     * Gets the sale information from the sale starter.
     *
     * @return the sale object.
     */
    public Sale getSale(){return this.saleStarter.getSale();}

    /**
     * Sends a request to create a new object to the object creator.
     *
     * @param request user input for the object parameters.
     */
    public void add(String request){
        this.objectCreator.newRequest(request);
    }

    /**
     * Sends a request to remove an object from the program to the object deleter.
     *
     * @param request user input to identify the object.
     */
    public void remove(String request){
        this.objectDeleter.newRequest(request);
    }

    /**
     * Tells the serializer to serialize the program data.
     */
    public void saveState(){
        this.serializer.serialize();
    }

    /**
     * Tells the serializer to deserialize the program data.
     */
    public void loadState(){
        this.saleStarter = (SaleStarter)this.serializer.deserialize();
        this.objectCreator =new ObjectCreator(saleStarter.getProductTransport(),saleStarter.getSale(),
                saleStarter.getProductTransport().getWarehouse());
        this.objectDeleter = new ObjectDeleter(saleStarter.getProductTransport(),saleStarter.getSale(),
                saleStarter.getProductTransport().getWarehouse());
    }
}
