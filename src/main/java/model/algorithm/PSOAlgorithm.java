package model.algorithm;

import model.individual.Element;
import model.individual.State;
import view.View;
import model.individual.Individual;
import model.individual.Particle;

import java.util.ArrayList;

public class PSOAlgorithm extends HeuristicAlgorithm {
	private ArrayList<Particle> population;

	private Individual bestIndividual;

	public PSOAlgorithm(Element[] elements){
		super(elements);
		for(Individual i : getPopulation()){
			population.add((Particle) i);
		}
	}


	public Individual doOtherSteps() {
		// add code here
		return null;
	}

	@Override
	public Individual getBestIndividual() {
		return null;
	}

	public ArrayList<Individual> evolve() {
		return null;
	}

	public void initPopulation() {
		//for(int i = 0; i < NUM_INDIVIDUAL; i++) {
		//	population.add(new Particle());
		//}
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
