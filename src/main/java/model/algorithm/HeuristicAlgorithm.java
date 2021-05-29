package model.algorithm;

import model.individual.*;

import java.util.*;

public abstract class HeuristicAlgorithm implements Algorithm {
	public static final double SATISFY_WEIGHT_LESS = 0.2;
	public static final int NUM_INDIVIDUAL = 10;
	public static final int MAX_GENERATION = 10;

	public static int GENERATION_LEVEL = 0;
	private List<Individual> population = new ArrayList<Individual>();

	public abstract Individual doOtherSteps();

	public final Individual solve(Element[] elements) {
		initPopulation(elements);
		Individual bestInd = getBestIndividual();
		// continue loop when we not find any satisfy Individual and generationLevel is not enough
		while(!isSatisfy(bestInd) && GENERATION_LEVEL < MAX_GENERATION){
			++GENERATION_LEVEL;
			bestInd = doOtherSteps();
		}
		return bestInd;
	}

	public void initPopulation(Element[] elements) {
		for(int i = 0; i < NUM_INDIVIDUAL; i++){
			population.add(new BackPack(elements));
		}
	}

	public List<Individual> getPopulation() {
		return population;
	}

	public Individual getBestIndividual() {
		double min = 100;
		double tmp;
		Individual resIndividual = null;
		for(Individual i : population){
			tmp = BackPack.MAX_WEIGHT - i.getWeight();
			if(tmp >= 0 && tmp < min){
				min = tmp;
				resIndividual = i;
			}
		}
		return resIndividual;
	}

	public boolean isSatisfy(Individual individual) {
		// kiểm tra xem (maxWeight - weight của bestIndividual) đã nhỏ hơn SATISFY_WEIGHT_LESS chưa,
		// nếu rồi thì return true; khong thì false
		return BackPack.MAX_WEIGHT - individual.getWeight() <= SATISFY_WEIGHT_LESS;
	}

}
