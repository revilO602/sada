import javafx.geometry.Orientation;
import model.exceptions.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.control.Alert.*;
import javafx.event.EventHandler;

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Main and only window of the application.
 *
 * @author leontiev
 */
public class View extends Application {
    private final ArrayList<TextArea> textOuts = new ArrayList<>();
    private final Button saleB = new Button("Sale");
    private final TextField salesmanIn = new TextField();
    private final TextField productIn = new TextField();
    private final TextField amountIn = new TextField();
    private final Label salesmanLbl = new Label("Salesman");
    private final Label productLbl = new Label("Product");
    private final Label amountLbl = new Label("Amount");
    private final Label saleOutLbl = new Label("Sale Output");
    private final Label deliveryOutLbl = new Label("Delivery Output");
    private final Label supplyOutLbl = new Label("Supply Output");
    private final TextArea supplyOut = new TextArea();
    private final TextArea deliveryOut = new TextArea();
    private final TextArea saleOut = new TextArea();
    private final Button tellStockB = new Button("What's in Stock");
    private final Button tellSalesmanB = new Button("Who can sell?");
    private final Button nextDayB = new Button("Next Day");
    private final TextArea creationInput = new TextArea();
    private final Label creationLbl = new Label("Add and Remove Items");
    private final Button addB = new Button("Add");
    private final Button removeB = new Button("Remove");
    private final Button saveB = new Button("Save State");
    private final Button loadB = new Button("Load State");

    /**
     * Handler that activates the sale.
     */
    class SaleEventHandler implements EventHandler<ActionEvent>{
        private final Controller controller;
        public SaleEventHandler(Controller controller){
            this.controller=controller;
        }

        @Override
        public void handle(ActionEvent actionEvent) {
            try {
                controller.sale(salesmanIn.getText(),
                        productIn.getText(),
                        Integer.parseInt(amountIn.getText()));
            }
            catch (NumberFormatException | WrongSalesmanNameException | WrongProductInputException x){
                createAlert(x.getMessage());
            }
        }
    }

    /**
     * Handler that advances time to the next day.
     */
    class NextDayEventHandler implements EventHandler<ActionEvent>{
        private final Controller controller;
        public NextDayEventHandler(Controller controller){
            this.controller=controller;
        }

        @Override
        public void handle(ActionEvent actionEvent) {
            for (TextArea t : textOuts)
                t.appendText("--------------| NEXT DAY |--------------\n");
            controller.nextDay();
        }
    }

    /**
     * Handler that adds an object to the program.
     */
    class AddEventHandler implements EventHandler<ActionEvent>{
        private final Controller controller;
        public AddEventHandler(Controller controller){
            this.controller=controller;
        }

        @Override
        public void handle(ActionEvent actionEvent) {
            try {
                controller.add(creationInput.getText());
            }
            catch(IllegalStateException | NoSuchElementException x){
                    createAlert("Wrong Input!");
            }
            catch (EmployeeAlreadyExistsException x){
                createAlert(x.getMessage());
            }
        }
    }

    /**
     * Handler that removes an object from the program.
     */
    class RemoveEventHandler implements EventHandler<ActionEvent>{
        private final Controller controller;
        public RemoveEventHandler(Controller controller){
            this.controller=controller;
        }

        @Override
        public void handle(ActionEvent actionEvent) {
            try {
                controller.remove(creationInput.getText());
            }
            catch(IllegalStateException | NoSuchElementException x){
                createAlert("Wrong Input!");
            }
        }
    }

    /**
     * Creates a new alert pop-up.
     *
     * @param alertMessage message that the alert displays.
     */
    private void createAlert(String alertMessage){
        Alert a = new Alert(AlertType.ERROR);
        a.setTitle("Something's wrong...");
        a.setContentText(alertMessage);
        a.showAndWait();
    }

    /**
     * Adds all the GUI elements that handle the sale input (first row of the main window) to the pane.
     *
     * @param pane pane that holds the elements.
     */
    private void setUpSaleElements(Pane pane){
        pane.getChildren().add(saleB);
        pane.getChildren().add(salesmanLbl);
        pane.getChildren().add(salesmanIn);
        pane.getChildren().add(productLbl);
        pane.getChildren().add(productIn);
        pane.getChildren().add(amountLbl);
        pane.getChildren().add(amountIn);
    }

    /**
     * Creates or updates the outputs in the main window.
     *
     * @param controller provides the objects that create the messages.
     */
    private void setUpMessageOuts(Controller controller){
        controller.getSupply().setOut(supplyOut);
        controller.getDelivery().setOut(deliveryOut);
        controller.getSale().setOut(saleOut);
    }

    /**
     * Adds all the outputs and supporting labels to the pane.
     *
     * @param pane       pane that holds the elements.
     * @param controller provides the objects that create the messages.
     */
    private void setUpOuts(Pane pane, Controller controller){
        setUpMessageOuts(controller);
        pane.getChildren().add(saleOut);
        pane.getChildren().add(saleOutLbl);
        pane.getChildren().add(tellSalesmanB);
        pane.getChildren().add(deliveryOut);
        pane.getChildren().add(deliveryOutLbl);
        pane.getChildren().add(supplyOut);
        pane.getChildren().add(supplyOutLbl);
        pane.getChildren().add(tellStockB);
        textOuts.add(saleOut);
        textOuts.add(supplyOut);
        textOuts.add(deliveryOut);
    }

    /**
     * Add all the creation GUI elements to the pane.
     *
     * @param pane pane that holds the elements.
     */
    private void setUpCreationElements(Pane pane){
        pane.getChildren().add(creationInput);
        creationInput.setPrefHeight(150);
        creationInput.setPrefWidth(150);
        pane.getChildren().add(creationLbl);
        pane.getChildren().add(addB);
        pane.getChildren().add(removeB);
        pane.getChildren().add(saveB);
        pane.getChildren().add(loadB);
    }

    /**
     * Add all GUI elements to the pane.
     *
     * @param pane       pane that holds the elements.
     * @param controller provides connections to the model objects.
     */
    private void setUpPane(FlowPane pane, Controller controller){
        pane.setHgap(10);
        pane.setVgap(10);
        setUpSaleElements(pane);
        setUpOuts(pane, controller);
        pane.getChildren().add(nextDayB);
        setUpCreationElements(pane);
    }

    /**
     * Create the main window and start the application.
     *
     * @param primaryStage the primary stage of the application.
     */
    public void start(Stage primaryStage){
        primaryStage.setTitle ("Sales And Delivery Assistant");
        FlowPane flow = new FlowPane();
        flow.setPrefSize(900,900);
        Controller controller = new Controller();
        setUpPane(flow, controller);
        ScrollPane scroll = new ScrollPane(flow);
        saleB.setOnAction(new SaleEventHandler(controller));
        nextDayB.setOnAction(new NextDayEventHandler(controller));
        tellStockB.setOnAction(e -> controller.tellStock());
        tellSalesmanB.setOnAction(e -> controller.tellSalesman());
        addB.setOnAction(new AddEventHandler(controller));
        removeB.setOnAction(new RemoveEventHandler(controller));
        saveB.setOnAction(e-> controller.saveState());
        loadB.setOnAction(e->{
            controller.loadState();
            this.setUpMessageOuts(controller);
        });
        primaryStage.setScene(new Scene(scroll,1000,1000));
        primaryStage.show();
    }

    /**
     * Entry point of the application.
     *
     * @param args array of command line arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
