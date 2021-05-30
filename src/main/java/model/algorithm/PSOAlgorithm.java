package model.algorithm;

//import view.View;
import model.individual.BackPack;
import model.individual.Element;
import model.individual.Individual;
import model.individual.PoolElements;
import model.algorithm.HeuristicAlgorithm;

import model.algorithm.PSO.PSO_.Particle;
import model.algorithm.PSO.PSO_.Swarm;
import model.algorithm.PSO.PSO_.Vector;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class PSOAlgorithm extends HeuristicAlgorithm {

	private ArrayList<Individual> population;

	private Individual bestIndividual;

	public PSOAlgorithm(){
		this.population = (ArrayList<Individual>) getPopulation();
	}

	public Individual doOtherSteps() {
		// add code here
		return null;
	}

	public ArrayList<Individual> evolve() {
		return null;
	}

	/*public void initPopulation() {
		for(int i = 0; i < NUM_INDIVIDUAL; i++) {
			population.add(new Particle());
		}
	}*/

	public static ArrayList<String> run(Individual in1) {

		Element[] elementsList = in1.getElements();

		ArrayList<Float> weight = new ArrayList<Float>();

		double testSum= 0;
		for(int i = 0; i < Element.MAX_ELEMENTS/2; i++){
			weight.add(new Float(elementsList[i].getWeight()));
			System.out.println(elementsList[i].getWeight());
			testSum +=elementsList[i].getWeight();
		}
		Swarm swarm = new Swarm(10, 1000);
		System.out.println (in1.getWeight());
		swarm.runEach(BackPack.MAX_WEIGHT, weight);

		for (int i = 0; i < swarm.finalAns.size(); i++){
			System.out.println(swarm.finalAns.get(i)+ " ");
		}
		System.out.print(testSum);
		return swarm.finalAns;

	}

	public static Individual getBestIndividual(String abc, Element[] elementsList) {
		Element[] elementsList1 = new Element[Element.MAX_ELEMENTS/2];
		int j = 0;
		for (int i = 0; i < abc.length(); i++){
			if (abc.charAt(i) == '1'){
				elementsList1[j] = elementsList[i];
				j++;
			}
		}
		Individual in2 = new BackPack(elementsList1);
		return in2;
	}
/*
	public void switchTo(View view) {

	}
	*/


	public static void testing(){
		Swarm swarm;
		int particles, epochs;
		particles = 5;
		epochs = 100;

		swarm = new Swarm( particles, epochs);
		ArrayList<Float> weight = new ArrayList<Float>();
		weight.add(new Float(3));
		weight.add(new Float(2));
		weight.add(new Float(4));
		swarm.runEach(11, weight);
		for (int i = 0; i < swarm.finalAns.size(); i++){
			System.out.println(swarm.finalAns.get(i)+ " ");
		}
	}

	public static void main (String[] args){
		Element[] elements = new PoolElements().getElements();
		Individual in1 = new BackPack(elements);

		ArrayList<String> abc = run(in1);
		for (int i = 0; i < abc.size(); i++){
			System.out.println(abc.get(i));
		}

		Individual bestInd = getBestIndividual(abc.get(abc.size()-1), in1.getElements());
	}

}
