package model.observerPattern;

/**
 * The observer interface is part of the observer design pattern.
 *
 * @author leontiev
 */
public interface IObserver {
    /**
     * The observer becomes updated with the state of the observable.
     *
     * @param observable the observable, whose state has changed.
     */
    void update(IObservable observable);

}
