package model.vehicles;

import model.employees.drivers.Driver;

import java.util.Random;

/**
 * Truck is the car driven by supply drivers.
 * By default, has a 25% chance of getting stuck in a traffic jam for 1 day, has a capacity of 50 and
 * a speed of 2.
 *
 * @author leontiev
 */
public class Truck extends Car {

    /**
     * Constructor for Truck.
     */
    public Truck(){
        this.setRoadEventChance(20);
        this.setSpeed(2);
        this.setCapacity(50);
    }

    /**
     * There's a random chance of the truck getting stuck in a traffic jam, otherwise does nothing.
     * The traffic jam reduces the trucks speed to 1 for 1 day.
     *
     * @param driver that has to be informed about the potential traffic jam.
     */
    @Override
    public void roadEvent(Driver driver) {
        if(driver.isOnRoute()){
            Random random = new Random();
            if((random.nextInt(100)+1)<=this.getRoadEventChance()) {
                driver.receiveCarInfo("Driver " + driver.getName() +
                        " is stuck in a traffic jam today\n");
                this.setSpeed(1);
            }
        }
    }

    /**
     * Advances the day for the truck.
     *
     * @param driver that has to be informed if something happens to the truck during the day.
     */
    @Override
    public void nextDay(Driver driver) {
        if (this.getSpeed()==1)
            this.setSpeed(2);
        super.nextDay(driver);
    }

}
