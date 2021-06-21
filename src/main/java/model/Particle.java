package model;

public class Particle extends BackPack {
    // because the knapsack problem is quite simple, so velocity only have 1 dimension and int type
    // if velocity = 1, we add only best item for this backpack, velocity = 2, we add exactly 2 items for this backpack
    public static final int MAX_VELOCITY = 5;
    public static final int MIN_VELOCITY = 1;
    private int velocity;

    public Particle() {
        super();
        velocity = ranRange(MIN_VELOCITY, MAX_VELOCITY);
    }

    public int getVelocity() {
        return velocity;
    }

    public void setVelocity(int v) {
        this.velocity = v;
    }
}
