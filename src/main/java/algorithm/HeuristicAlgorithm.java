package algorithm;

import controller.GeneticAlgorithmViewController;
import model.Individual;
import model.Population;
import view.Observer;
import view.View;

import java.util.ArrayList;

public abstract class HeuristicAlgorithm implements Algorithm, Publisher {
    public static final int MAX_GENERATION = 20;

    private int generationLevel = 0;
    private ArrayList<Observer> subscribes;
    private Population population = new Population();

    public HeuristicAlgorithm(GeneticAlgorithmViewController controller) {
        subscribes = new ArrayList<>();
        subscribes.add(controller);
        population.initPopulation();
    }

    public abstract Individual doOtherSteps();

    public final Individual solve() {
        Individual bestInd = population.getBestIndividual();
        // continue loop when we not find any satisfy Individual and generationLevel is not enough
        while (!Population.isSatisfy(bestInd) && generationLevel++ < MAX_GENERATION) {
            bestInd = doOtherSteps();
            notifySubscribers();
        }
        return bestInd;
    }

    public Population getPopVariable(){
        return this.population;
    }

    public int getGenerationLevel() {
        return this.generationLevel;
    }

    @Override
    public void subscribe(Observer o) {
        subscribes.add(o);
    }

    @Override
    public void unsubscribe(Observer o) {
        subscribes.remove(o);
    }

    @Override
    public void notifySubscribers(){
        for(Observer o : subscribes){
            // update each view
            o.update();
        }
    }
}
