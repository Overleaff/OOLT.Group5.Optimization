package algorithm;

import view.Observer;

public interface Publisher {
    public abstract void subscribe(Observer v);
    public abstract void unsubscribe(Observer v);
    public abstract void notifySubscribers();
}
