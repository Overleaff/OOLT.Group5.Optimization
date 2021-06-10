package model;

public interface Individual {
    public static final int MAX_WEIGHT = 27; // 27kg

    public abstract double getWeight();

    public abstract double fitness();

    public abstract String toString();
}
