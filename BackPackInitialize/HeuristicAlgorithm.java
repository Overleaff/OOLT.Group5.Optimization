import java.util.*;

public class HeuristicAlgorithm implements Algorithm {

	private List<Individual> population= new ArrayList<Individual>();

	public abstract void doOtherSteps();

	public Individual solve(Array<Element> items, double maxWeight) {
		return null;
	}

	public ArrayList<Individual> initPopulation() {

		return null;
	}

	public ArrayList<Individual> getPopulation() {
		return null;
	}

	public Individual getBestIndividual() {
		return null;
	}

	public abstract ArrayList<Individual> evolve();

	public boolean isSatisfy(Individual bp) {
		return false;
	}

	public void switchTo(View view) {

	}

}
