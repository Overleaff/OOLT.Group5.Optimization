package model;

import java.util.ArrayList;

public class Population {
    public static final double SATISFY_WEIGHT_LESS = 0.1;
    public static final int NUM_INDIVIDUAL = 10;

    private ArrayList<Individual> population = new ArrayList<>();

    public ArrayList<Individual> getPopulation() {
        return this.population;
    }

    public Individual getBestIndividual() {
        double max = -1;
        Individual resIndividual = null;
        for (Individual i : population) {
            if (i.fitness() > max) {
                max = i.fitness();
                resIndividual = i;
            }
        }
        return resIndividual;
    }

    public void addIndividual(Individual i){
        population.add(i);
    }

    public static boolean isSatisfy(Individual individual) {
        // kiểm tra xem (maxWeight - weight của bestIndividual) đã nhỏ hơn SATISFY_WEIGHT_LESS chưa,
        // nếu rồi thì return true; khong thì false
        double tmp = individual.MAX_WEIGHT - individual.getWeight();
        return tmp <= SATISFY_WEIGHT_LESS && tmp >= 0;
    }

    public void setPopulation(ArrayList<BackPack> backPacks) {
        for(BackPack bp: backPacks) {
            this.population.set(backPacks.indexOf(bp), bp);
        }
    }
}
