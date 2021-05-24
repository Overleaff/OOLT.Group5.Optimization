package sample.algorithm;

import sample.individual.*;
import View.*;
import java.util.*;

public abstract class HeuristicAlgorithm implements Algorithm {
	public static final double SATISFY_WEIGHT_LESS = 0.2;
	public static final int NUM_INDIVIDUAL = 10;
	private List<Individual> population;

	public abstract void doOtherSteps();

	public final Individual solve(Element[] items, double maxWeight) {
		initPopulation();
		Individual bestInd = getBestIndividual();
		while(!isSatisfy(bestInd)){
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
		// chọn ra Individual tốt nhất
	}

	public abstract ArrayList<Individual> evolve();

	public boolean isSatisfy(Individual individual) {
		// kiểm tra xem (maxWeight - weight của bestIndividual) đã nhỏ hơn SATISFY_WEIGHT_LESS chưa,
		// nếu rồi thì return true; khong thì false
	}

	public void switchTo(View view) {

	}

}
