package model.algorithm;

import model.individual.Element;
import model.individual.Individual;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class GeneticAlgorithm extends HeuristicAlgorithm {

	private ArrayList<Individual> population;

	public GeneticAlgorithm(){
		this.population = (ArrayList<Individual>) getPopulation();
	}

	public Individual doOtherSteps() {
		// sort individual theo fitness
		Collections.sort(
				population, Comparator.comparingDouble(Individual::fitness)
		);
		for(int i = 0; i < NUM_INDIVIDUAL/2; i++)
			crossover(population.get(i), population.get(NUM_INDIVIDUAL-i));
		for(int i = 0; i< NUM_INDIVIDUAL/2; i++)
			mutate(population.get(i));
		return getBestIndividual();
	}

	public void crossover(Individual in1, Individual in2) {
		// swap half of elements in in1 with half of elements in in2, number of elements in in1 equals to in2
		Element[] items1 = in1.getElements();
		Element[] items2 = in2.getElements();
		for(int i = 0; i < in1.getNumOfElement()/2; i++){
			in1.updateElement(items1[i], items2[i].getWeight(), items2[i].getImageFile());
			in2.updateElement(items2[i], items1[i].getWeight(), items1[i].getImageFile());
		}
	}

	public void mutate(Individual bp) {
		// update randomly 1/10 elements in the backpack with the new element not in backpack before
		int ran = (int)(Math.random() * 10);
		Element[] elements = bp.getElements();
		Element e = bp.getNewRandomElement();
		bp.updateElement(elements[ran], e.getWeight(), e.getImageFile());

	}
}
