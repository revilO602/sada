package model.vehicles;

import model.employees.drivers.Driver;

import java.io.Serializable;
import java.util.Random;

/**
 * Car is the vehicle driven by the delivery drivers.
 * By default, has a 10% chance of having an accident, capacity of 20, speed of 3
 * and needs to be checked every 5 days.
 *
 * @author leontiev
 */
public class Car implements IVehicle, Serializable {
    private int roadEventChance = 10;
    private int capacity = 20;
    private int speed = 3;
    private int daysToCheckUp = 5;
    private boolean inCheckUp=false;
    private boolean available=true;

    /**
     * Returns the chance of a road event happening while the car is being driven.
     *
     * @return chance of a road event happening.
     */
    public int getRoadEventChance() {
        return this.roadEventChance;
    }

    /**
     * Sets the chance of a road event happening while the car is being driven.
     *
     * @param roadEventChance chance of a road event happening.
     */
    public void setRoadEventChance(int roadEventChance) {
        this.roadEventChance = roadEventChance;
    }

    /**
     * Returns the capacity of the car (the amount of product it can transport at once).
     *
     * @return capacity of the car.
     */
    @Override
    public int getCapacity() {
        return this.capacity;
    }

    /**
     * Sets the capacity of the car (the amount of product it can transport at once).
     *
     * @param capacity capacity of the car.
     */
    @Override
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    /**
     * Returns the speed of the car (amount of distance traversed in one day).
     *
     * @return speed of the car.
     */
    @Override
    public int getSpeed() {
        return this.speed;
    }

    /**
     * Sets the speed of the car (amount of distance traversed in one day).
     *
     * @param speed speed of the car.
     */
    @Override
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /**
     * Sets the car to be available or unavailable.
     *
     * @param available true for available, false for unavailable.
     */
    @Override
    public void setAvailable(boolean available) {
        this.available=available;
    }

    /**
     * Returns true if the car is available.
     *
     * @return true if the car is available, otherwise false.
     */
    @Override
    public boolean isAvailable() {
        return this.available;
    }

    /**
     * There's a random chance of the car getting into an accident if the car is being driven, otherwise does nothing.
     * The accident makes the car unavailable and forces it to get checked.
     *
     * @param driver that has to be informed about the potential accident.
     */
    @Override
    public void roadEvent(Driver driver) {
        if(driver.isOnRoute()){
            Random random = new Random();
            if((random.nextInt(100)+1)<=this.roadEventChance) {
                driver.receiveCarInfo("Driver " + driver.getName() + " had an accident and returned to have" +
                                " his car repaired.\n");
                driver.setTravelledDistance(-this.speed);
                this.checkUp();
            }
        }
    }

    /**
     * Advances the day for the car.
     * A regular check up is performed every 5 days that lasts 1 day and makes the car unavailable.
     * If the car is being driven there's a chance of an accident happening.
     *
     * @param driver that has to be informed if something happens to the car during the day.
     */
    @Override
    public void nextDay(Driver driver){
        if(this.inCheckUp) {
            this.inCheckUp=false;
            this.available=true;
            this.daysToCheckUp= 5;
            driver.receiveCarInfo("Car of "+
                    driver.getName()+" returned into service.\n");
        }
        else{
            this.roadEvent(driver);
            this.daysToCheckUp--;
            if(this.daysToCheckUp<1 && this.available){
               this.checkUp();
                driver.receiveCarInfo("Car of "+
                        driver.getName()+" has a regular check-up today.\n");
            }
        }
    }

    /**
     * The car will be checked, making it unavailable for a day.
     */
    private void checkUp(){
        this.inCheckUp=true;
        this.available=false;
    }
}


