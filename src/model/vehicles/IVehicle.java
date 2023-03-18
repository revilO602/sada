package model.vehicles;

import model.employees.drivers.Driver;

/**
 * Vehicle that can be driven by a driver.
 *
 * @author leontiev
 */
public interface IVehicle {
    /**
     * An event that has a chance to happen, while the vehicle is being driven.
     *
     * @param driver that has to be informed about the event.
     */
    void roadEvent(Driver driver);

    /**
     * Advances the day for the vehicle.
     *
     * @param driver that has to be informed if something happens to the car during the day.
     */
    void nextDay(Driver driver);

    /**
     * Sets the capacity of the vehicle (the amount of product it can transport at once).
     *
     * @param capacity capacity of the vehicle.
     */
    void setCapacity(int capacity);

    /**
     * Returns the capacity of the vehicle (the amount of product it can transport at once).
     *
     * @return capacity of the vehicle.
     */
    int getCapacity();

    /**
     * Sets the speed of the vehicle (amount of distance traversed in one day).
     *
     * @param speed speed of the vehicle.
     */
    void setSpeed(int speed);

    /**
     * Returns the speed of the vehicle (amount of distance traversed in one day).
     *
     * @return speed of the vehicle.
     */
    int getSpeed();

    /**
     * Sets the vehicle to be available or unavailable.
     *
     * @param available true for available, false for unavailable.
     */
    void setAvailable(boolean available);

    /**
     * Returns true if the vehicle is available.
     *
     * @return true if the vehicle is available, otherwise false.
     */
    boolean isAvailable();
}
