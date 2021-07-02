package algorithm;

import model.Individual;
import view.View;

public interface Algorithm {
    Individual solve(); // define Algorithm
    View getViewFile(); // define which file View it connects
    Individual createIndividual(); // define which individual it return
}
