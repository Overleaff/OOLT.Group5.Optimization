package sample.algorithm;

import sample.individual.*;
import view.*;
import java.util.*;

public abstract class HeuristicAlgorithm implements Algorithm {
	public static final double SATISFY_WEIGHT_LESS = 0.2;
	public static final int NUM_INDIVIDUAL = 10;
	public static final int MAX_GENERATION = 10;

	public static int GENERATION_LEVEL = 0;
	private double maxWeight;
	private List<Individual> population = new ArrayList<Individual>();

	public abstract void doOtherSteps();

	public final Individual solve(Element[] items, double maxWeight) {
		initPopulation();
		this.maxWeight = maxWeight;
		Individual bestInd = getBestIndividual();
		// continue loop when we not find any satisfy Individual and generationLevel is not enough
		while(!isSatisfy(bestInd) && GENERATION_LEVEL < MAX_GENERATION){
			++GENERATION_LEVEL;
			doOtherSteps();
			evolve();
		}
		return bestInd;
	}

	public void initPopulation() {
		for(int i = 0; i < NUM_INDIVIDUAL; i++){
			population.add(new BackPack());
		}
	}

	public List<Individual> getPopulation() {
		return population;
	}

	public Individual getBestIndividual() {
		double min = 100;
		double tmp;
		Individual resIndividual = new BackPack();
		for(Individual i : population){
			tmp = maxWeight - i.getWeight();
			if(tmp >= 0 && tmp < min){
				min = tmp;
				resIndividual = i;
			}
		}
		return resIndividual;
	}

	public abstract ArrayList<Individual> evolve();

	public boolean isSatisfy(Individual individual) {
		// kiểm tra xem (maxWeight - weight của bestIndividual) đã nhỏ hơn SATISFY_WEIGHT_LESS chưa,
		// nếu rồi thì return true; khong thì false
		return maxWeight - individual.getWeight() <= SATISFY_WEIGHT_LESS;
	}

	public void switchTo(View view) {

	}

}
