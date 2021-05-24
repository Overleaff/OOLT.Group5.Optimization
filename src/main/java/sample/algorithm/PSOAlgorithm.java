package sample.algorithm;

import View.View;
import sample.individual.Individual;
import sample.individual.Particle;

import java.util.ArrayList;

public class PSOAlgorithm extends HeuristicAlgorithm {

	private ArrayList<Individual> population;

	private Individual bestIndividual;

	public void doOtherSteps() {

	}

	public ArrayList<Individual> evolve() {
		return null;
	}

	public void initPopulation() {
		for(int i = 0; i < NUM_INDIVIDUAL; i++) {
			population.add(new Particle());
		}
	}

	public void operation0() {

	}

	public void updateVelocity() {

	}

	public void updatePosition() {

	}

	public void updateIndividual() {

	}

	public void switchTo(View view) {

	}

}
