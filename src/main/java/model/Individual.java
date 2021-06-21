package model;

public interface Individual {
    public static final int MAX_WEIGHT = 27; // 27kg

    double getWeight();

    double fitness();

    String toString();

    int getNumOfElement();

    Element[] getElements();
}
