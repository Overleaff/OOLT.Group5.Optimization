package model.individual;

public interface Individual {
    public abstract double getWeight();
    public abstract Element[] getElements();
    public abstract void updateElement(Element e, double weight);
}
