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
	private ArrayList<String> abc;
	private Element[] elementsList;
	private Individual bestIndividual;

	public PSOAlgorithm(Element[] elements, Individual in){
		super(elements);
		this.population = (ArrayList<Individual>) getPopulation();
		this.elementsList = elements;
		abc = PSOAlgorithm.run(in);
	}

	public Individual doOtherSteps() {
		int tmp = this.generation_level;
		if(tmp > abc.size()-1)
			tmp = abc.size()-1;
		Individual in2 = new BackPack(PoolElements.getElements());
		for (int i = 0; i < abc.get(tmp).length(); i++){
			if (abc.get(tmp).charAt(i) == '1'){
				in2.updateElement(i, elementsList[i].getWeight(), elementsList[i].getImageFile());
			}else{
				in2.updateElement(i, 0, "");
			}
		}
		return in2;
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
		System.out.println(testSum);
		return swarm.finalAns;

	}

	public static Individual getBestIndividual(String abc, Element[] elementsList) {
		Individual in2 = new BackPack(PoolElements.getElements());
		for (int i = 0; i < abc.length(); i++){
			if (abc.charAt(i) == '1'){
				in2.updateElement(i, elementsList[i].getWeight(), elementsList[i].getImageFile());
			}else{
				in2.updateElement(i, 0, "");
			}
		}
		return in2;
	}

	public static void main (String[] args){
		Element[] elements = PoolElements.getElements();
		Individual in1 = new BackPack(elements);

		ArrayList<String> abc = run(in1);
		for (int i = 0; i < abc.size(); i++){
			System.out.println(abc.get(i));
		}

		Individual bestInd = getBestIndividual(abc.get(abc.size()-1), in1.getElements());
	}

}