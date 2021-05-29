package model.individual;

public interface Individual {
    public abstract double getWeight();
    public abstract Element[] getElements();
    public abstract void updateElement(Element e, double weight, String imgFile);
    public abstract int getNumOfElement();
    // for sap xep cac individual
    public abstract double fitness();
    public abstract Element getNewRandomElement();

}
