package model.objectManagment;

import model.IEmployeeGroup;
import model.storage.IProductGroup;


import java.util.Scanner;

/**
 * Handles the deleting of various objects during run-time.
 *
 * @author leontiev
 */
public class ObjectDeleter {
    private final IEmployeeGroup driverGroup;
    private final IEmployeeGroup salesmanGroup;
    private final IProductGroup productGroup ;
    private Scanner request;

    /**
     * Constructor for ObjectDeleter.
     *
     * @param driverGroup   a group to delete drivers from.
     * @param salesmanGroup a group to delete salesmen from.
     * @param productGroup  a group to delete products from.
     */
    public ObjectDeleter(IEmployeeGroup driverGroup,
                         IEmployeeGroup salesmanGroup, IProductGroup  productGroup) {
        this.driverGroup=driverGroup;
        this.salesmanGroup=salesmanGroup;
        this.productGroup=productGroup;
    }

    /**
     * New request to delete an object.
     *
     * @param request string with object type on the first line, followed by name of object on separate line.
     */
    public void newRequest(String request){
        this.request = new Scanner(request);
        this.analyzeRequest();
    }

    private void analyzeRequest() throws IllegalStateException {
        String type = this.request.nextLine();
        if (("Supply Driver").equals(type) || ("Delivery Driver").equals(type)
            || ("Driver").equals(type)) {
            this.deleteDriver();
        }
        else if ("Salesman".equals(type)) {
            this.deleteSalesman();
        }
        else if ("Product".equals(type)) {
            this.deleteProduct();
        }
        else {
            throw new IllegalStateException();
        }
    }

    private void deleteDriver(){
        if(this.request.hasNextLine()) {
            this.driverGroup.removeEmployee(this.request.nextLine());
        }
    }

    private void deleteSalesman(){
        this.salesmanGroup.removeEmployee(this.request.nextLine());
    }

    private void deleteProduct(){
        this.productGroup.deleteProduct(this.request.nextLine());
    }
}
