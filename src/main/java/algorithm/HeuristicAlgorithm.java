package algorithm;

import model.Individual;
import model.Population;
import view.View;

public abstract class HeuristicAlgorithm implements Algorithm{
    public static final int MAX_GENERATION = 20;
    public static int generationLevel = 0;
    private Population population = new Population();

    public HeuristicAlgorithm() {
        initPopulation();
    }

    public abstract Individual doOtherSteps();
    public abstract Individual createIndividual();
    public abstract View getViewFile();

    public final Individual solve() {
        Individual bestInd = population.getBestIndividual();
        // continue loop when we not find any satisfy Individual and generationLevel is not enough
        while (!Population.isSatisfy(bestInd) && generationLevel++ < MAX_GENERATION) {
            bestInd = doOtherSteps();
        }
        return bestInd;
    }

    public Population initPopulation(){
        Population p = new Population();
        for(int i = 0; i < Population.NUM_INDIVIDUAL; i++){
            Individual ind = createIndividual();
            p.addIndividual(ind);
        }
        return p;
    }

    public Population getPopVariable(){
        return this.population;
    }
}
