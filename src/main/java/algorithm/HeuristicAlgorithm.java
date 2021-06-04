package algorithm;

import javafx.beans.property.SimpleIntegerProperty;
import model.BackPack;
import model.Individual;

import java.util.*;

public abstract class HeuristicAlgorithm implements Algorithm {
    public static SimpleIntegerProperty shouldUpdateUI = new SimpleIntegerProperty();
    public static final double SATISFY_WEIGHT_LESS = 0.2;
    public static final int NUM_INDIVIDUAL = 10;
    public static final int MAX_GENERATION = 20;

    private int generationLevel = 0;
    private List<Individual> population = new ArrayList<Individual>();

    public HeuristicAlgorithm() {
        shouldUpdateUI.set(0);
        initPopulation();
    }

    public abstract Individual doOtherSteps();

    public final Individual solve() {
        Individual bestInd = getBestIndividual();
        // continue loop when we not find any satisfy Individual and generationLevel is not enough
        while (!isSatisfy(bestInd) && generationLevel++ < MAX_GENERATION) {
            bestInd = doOtherSteps();
        }
        shouldUpdateUI.set(4);
        return bestInd;
    }

    public void initPopulation() {
        for (int i = 0; i < NUM_INDIVIDUAL; i++) {
            population.add(new BackPack());
        }
    }

    public int getGenerationLevel() {
        return this.generationLevel;
    }

    public List<Individual> getPopulation() {
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

    public boolean isSatisfy(Individual individual) {
        // kiểm tra xem (maxWeight - weight của bestIndividual) đã nhỏ hơn SATISFY_WEIGHT_LESS chưa,
        // nếu rồi thì return true; khong thì false
        double tmp = individual.MAX_WEIGHT - individual.getWeight();
        return tmp <= SATISFY_WEIGHT_LESS && tmp >= 0;
    }
}
