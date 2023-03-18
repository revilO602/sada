package model.objectManagment;

import model.employees.*;
import model.employees.drivers.*;
import model.IEmployeeGroup;
import model.exceptions.EmployeeAlreadyExistsException;
import model.storage.IProductGroup;
import model.storage.Product;

import java.util.Scanner;

/**
 * Handles the creation of new objects during run-time.
 *
 * @author leontiev
 */
public class ObjectCreator {
    private final IEmployeeGroup driverGroup;
    private final IEmployeeGroup salesmanGroup;
    private final IProductGroup productGroup;
    private Scanner request;

    /**
     * Constructor for ObjectCreator.
     *
     * @param driverGroup   where to add created drivers.
     * @param salesmanGroup where to add created salesmen.
     * @param productGroup  where to add created products.
     */
    public ObjectCreator(IEmployeeGroup driverGroup,
                         IEmployeeGroup salesmanGroup, IProductGroup productGroup) {
        this.driverGroup=driverGroup;
        this.salesmanGroup=salesmanGroup;
        this.productGroup=productGroup;
    }

    /**
     * New request to create an object.
     * Can't use name of employee that's already taken. Using a name of an already existing product will
     * add the amount to that product. Required information for desired object type:
     * Delivery Driver, Supply Driver: name.
     * Salesman: name, cut (integer number).
     * Product: name, amount, price, batch (amount to be resupplied).
     *
     * @param request string with object type on the first line, followed by required information
     *                about the object on separate lines.
     */
    public void newRequest(String request){
        this.request = new Scanner(request);
        this.analyzeRequest();
    }

    private void analyzeRequest() throws IllegalStateException{
        String type = this.request.nextLine();
        switch (type) {
            case "Supply Driver":
                this.createSupplyDriver();
                break;
            case "Delivery Driver":
                this.createDeliveryDriver();
                break;
            case "Salesman":
                this.createSalesman();
                break;
            case "Product":
                this.createProduct();
                break;
            default: throw new IllegalStateException();
        }
    }

    private void createSupplyDriver() throws EmployeeAlreadyExistsException {
            String name = request.nextLine();
            if (this.driverGroup.findEmployee(name) != null)
                throw new EmployeeAlreadyExistsException("This name is already taken");
            SupplyDriver driver = new SupplyDriver(name);
            this.driverGroup.addEmployee(driver);
    }

    private void createDeliveryDriver() throws EmployeeAlreadyExistsException {
        String name = request.nextLine();
        if (this.driverGroup.findEmployee(name) != null)
            throw new EmployeeAlreadyExistsException("This name is already taken");
        DeliveryDriver driver = new DeliveryDriver(name);
        this.driverGroup.addEmployee(driver);
    }

    private void createSalesman() throws EmployeeAlreadyExistsException {
        String name = request.nextLine();
        if (this.salesmanGroup.findEmployee(name) != null)
            throw new EmployeeAlreadyExistsException("This name is already taken");
        Salesman salesman = new Salesman(name,Integer.parseInt(request.nextLine()));
        this.salesmanGroup.addEmployee(salesman);
    }

    private void createProduct(){
        Product product = new Product(request.nextLine(),Integer.parseInt(request.nextLine()),
                Integer.parseInt(request.nextLine()),Integer.parseInt(request.nextLine()));
        this.productGroup.addProduct(product);
    }

}
