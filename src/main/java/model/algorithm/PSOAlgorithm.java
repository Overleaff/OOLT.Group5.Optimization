package model.algorithm;

import view.View;
import model.individual.Individual;
import model.individual.Particle;

import java.util.ArrayList;

public class PSOAlgorithm extends HeuristicAlgorithm {

	private ArrayList<Individual> population;

	private Individual bestIndividual;

	public Individual doOtherSteps() {
		// add code here
		return null;
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
