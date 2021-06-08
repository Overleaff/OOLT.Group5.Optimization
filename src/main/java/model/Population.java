package model;

import java.util.ArrayList;

public class Population {
    public static final double SATISFY_WEIGHT_LESS = 0.2;
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

    public void initPopulation() {
        for (int i = 0; i < Population.NUM_INDIVIDUAL; i++) {
            population.add(new BackPack());
        }
    }

    public static boolean isSatisfy(Individual individual) {
        // kiểm tra xem (maxWeight - weight của bestIndividual) đã nhỏ hơn SATISFY_WEIGHT_LESS chưa,
        // nếu rồi thì return true; khong thì false
        double tmp = individual.MAX_WEIGHT - individual.getWeight();
        return tmp <= SATISFY_WEIGHT_LESS && tmp >= 0;
    }

    public static ArrayList<BackPack> toBackPackArrays(Population population){
        ArrayList<BackPack> ans = new ArrayList<>();
        for(Individual i : population.getPopulation()){
            ans.add((BackPack) i);
        }
        return ans;
    }
}
