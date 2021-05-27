package model.algorithm;

import model.individual.Element;
import model.individual.Individual;

public interface Algorithm {
	public abstract Individual solve(Element[] Elements, double maxWeight);
}
