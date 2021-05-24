package sample.algorithm;

import sample.individual.Element;
import sample.individual.Individual;

public interface Algorithm {
	public abstract Individual solve(Element[] Elements, double maxWeight);
}
