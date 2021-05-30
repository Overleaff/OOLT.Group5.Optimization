package model.algorithm;

import model.individual.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class HillClimbingAlgorithm extends HeuristicAlgorithm {
	// do steepest-ascent algorithm : examine all neighboring nodes of the current state and
	// selects one neighbor node that closest to the goal state.
	// we find the best neighboring node by :
	// 1. consider adding one of all items that not in backpack yet, memory this best item that closest to goal
	// 2. sort all items that not in backpack, then loop through all items inside backpack, for each loop we use binary
	// search to find the replaced item that let this new state closest to the goal.
	private BackPack backpack;
	private ArrayList<BackPack> population = new ArrayList<>();

	public HillClimbingAlgorithm(Element[] elements){
		super(elements);
		for(Individual i : getPopulation()){
			population.add((BackPack) i);
		}
		this.backpack = (BackPack) getBestIndividual();
	}

	private Element binarySearch(double target, Element[] a){
		int l = 0;
		int r = a.length-1;
		while(l+1 < r){
			int mid = l + (r-l)/2;
			if(a[mid].getWeight() <= target){
				l = mid;
			}else{
				r = mid-1;
			}
		}
		return target - a[l].getWeight() > 0 ? a[l] : new Element(0);
	}

	public Element[] ElementOutBack(Element[] PoolElements){
		Element[] ElementsOutBack = new Element[Element.MAX_ELEMENTS - backpack.getNumOfElement()+1];
		int j =0;
		for (Element poolElement : PoolElements) {
			if (!backpack.isContain(poolElement)) {
				ElementsOutBack[j++] = poolElement;
			}
		}
		ElementsOutBack[j] = new Element(0);
		return ElementsOutBack;
	}
	public void modifyState(Element[] PoolElements){
		Element[] elementsOutBack = ElementOutBack(PoolElements);
		Arrays.sort(elementsOutBack, Comparator.comparingDouble(Element::getWeight));
		double min = 100; int j = 0;
		Element res = new Element(0);
		Element tmp = new Element(0);
		for(int i=0; i <= backpack.getNumOfElement(); i++){
			double target = Individual.MAX_WEIGHT - backpack.getWeight() + backpack.getElements()[i].getWeight();
			if(target >= 0)
				tmp = binarySearch(target, elementsOutBack);
			if(Math.abs(tmp.getWeight() - target) < min){
				min = Math.abs(tmp.getWeight() - target);
				res = tmp;
				j = i;
			}
		}
		System.out.println(1);
		backpack.updateElement(j, res.getWeight(), res.getImageFile());
	}

	public void bestNextState(){
		modifyState(backpack.getPoolElements());
	}

	public BackPack doOtherSteps() {
		// add code here
		bestNextState();
		return this.backpack;
	}

	public static void main(String[] args){
		Element[] elements = new PoolElements().getElements();
		HillClimbingAlgorithm hc = new HillClimbingAlgorithm(elements);
		Individual bestInd = hc.solve();
		System.out.println(bestInd + ", " + bestInd.getWeight());
	}
}
