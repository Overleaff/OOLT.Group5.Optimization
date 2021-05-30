package model.individual;

public interface Individual {
    public static final int MAX_WEIGHT = 27; // 26kg

    public abstract double getWeight();

    public abstract Element[] getElements();

    public abstract void updateElement(int i, double weight, String imgFile);

    public abstract int getNumOfElement();

    // for sap xep cac individual
    public abstract String toString();

    public Element getNewRandomElement();
}
