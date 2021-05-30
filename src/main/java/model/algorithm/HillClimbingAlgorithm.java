package model.algorithm;

import model.individual.BackPack;
import model.individual.Element;
import model.individual.Individual;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class HillClimbingAlgorithm extends HeuristicAlgorithm {
	// do steepest-ascent algorithm : examine all neighboring nodes of the current state and
	// selects one neighbor node that closest to the goal state.
	// we find the best neighboring node by :
	// 1. consider adding one of all items that not in backpack yet, store back the best node that closest to goal
	// 2. sort all items that not in backpack, then loop through all items inside backpack, for each loop we use binary
	// search to find the replaced item that let this new state closest to the goal.

	private Individual stateIndividual;

	private Element binarySearch(double target, Element[] a){
		double[] weightArray =
	}

	public HillClimbingAlgorithm(){
		this.stateIndividual = getBestIndividual();
	}

	public Individual doOtherSteps() {
		// add code here
		return null;
	}

	public Individual drawSolution() {
		return null;
	}

}
