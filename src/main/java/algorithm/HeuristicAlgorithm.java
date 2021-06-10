package algorithm;

import model.Individual;
import model.Population;

public abstract class HeuristicAlgorithm implements Algorithm{
    public static final int MAX_GENERATION = 20;
    public static int generationLevel = 0;
    private Population population = new Population();

    public HeuristicAlgorithm() {
        population.initPopulation();
    }

    public abstract Individual doOtherSteps();

    public final Individual solve() {
        Individual bestInd = population.getBestIndividual();
        // continue loop when we not find any satisfy Individual and generationLevel is not enough
        while (!Population.isSatisfy(bestInd) && generationLevel++ < MAX_GENERATION) {
            bestInd = doOtherSteps();
        }
        return bestInd;
    }

    public Population getPopVariable(){
        return this.population;
    }
}
