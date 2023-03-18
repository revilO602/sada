package model.observerPattern;

/**
 * The observable interface is part of the observer design pattern.
 *
 * @author leontiev
 */
public interface IObservable {
    /**
     * Adds a new observer that observes this observable.
     *
     * @param observer new observer.
     */
    void addObserver(IObserver observer);

    /**
     * Notify all observers that the state of the observable has changed.
     */
    void notifyObservers();
}
