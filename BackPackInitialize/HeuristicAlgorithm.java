import java.util.*;

public abstract class HeuristicAlgorithm{

	private List<Individual> population= new ArrayList<Individual>();

	public abstract void doOtherSteps();

	public abstract Individual solve(Array<Element> items, double maxWeight) ;

	public ArrayList<Individual> initPopulation() {
		population.add(new BackPack(14, BackPack.getElement()));
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

	/*
	public void switchTo(View view) {
	}
		*/
}
