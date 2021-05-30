package model.algorithm;

import model.individual.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class GeneticAlgorithm extends HeuristicAlgorithm {

	private ArrayList<Genetic> population;

	public GeneticAlgorithm(){
		for(Individual i : getPopulation()){
			population.add((Genetic) i);
		}
	};

	public Genetic doOtherSteps() {
		// sort individual theo fitness
		Collections.sort(
				population, Comparator.comparingDouble(Genetic::fitness)
		);
		for(int i = 0; i < NUM_INDIVIDUAL/2; i++)
			crossover((Genetic) population.get(i), (Genetic) population.get(NUM_INDIVIDUAL-i-1));
		for(int i = 0; i< NUM_INDIVIDUAL/2; i++)
			mutate(population.get(i));
		return (Genetic)getBestIndividual();
	}

	public void crossover(Genetic g1, Genetic g2) {
		// swap half of elements in in1 with half of elements in in2, number of elements in in1 equals to in2
		Element[] items1 = g1.getElements();
		Element[] items2 = g2.getElements();
		int i;
		double tmpWei;
		String tmpString;
		for(i = 0; i < g1.getNumOfElement(); i++) {
			if (!g1.isContain(items2[i]) && !g2.isContain(items1[i])) {
				tmpWei = items1[i].getWeight();
				tmpString = items1[i].getImageFile();
				g1.updateElement(i, items2[i].getWeight(), items2[i].getImageFile());
				g2.updateElement(i, tmpWei, tmpString);
			}
		}
	}

	public void mutate(Genetic bp) {
		// update randomly 1/10 elements in the backpack with the new element not in backpack before
		int ran = (int)(Math.random() * 10);
		Element[] elements = bp.getElements();
		Element e = bp.getNewRandomElement();
		bp.updateElement(ran, e.getWeight(), e.getImageFile());
	}

	public Genetic getBestIndividual(){
		double max = 0;
		Genetic resIndividual = null;
		for(Genetic i : population){
			if(i.fitness() > max){
				max = i.fitness();
				resIndividual = i;
			}
		}
		return resIndividual;
	}

	public static void main(String[] args){
		GeneticAlgorithm gA = new GeneticAlgorithm();
		Element[] elements = new PoolElements().getElements();
		Individual bestInd = gA.solve(elements);
		System.out.println(bestInd + ", " + bestInd.getWeight());
	}
}
