package controller;

import algorithm.GeneticAlgorithm;
import algorithm.HillClimbingAlgorithm;
import algorithm.PSOAlgorithm;
import javafx.event.ActionEvent;
import view.View;
import view.ViewSwitcher;

import java.io.IOException;

public class MainMenuController extends Controller {

    public void next() {
        population = h.initPopulation();
        ViewSwitcher.switchTo(View.INIT);
    }

    public void geneticAlgorithmButtonClicked() {
        h = new GeneticAlgorithm();
        next();
    }

    public void psoButtonClicked() {
        //h = new PSOAlgorithm();
        next();
    }

    public void hillClimbingButtonClicked() {
        h = new HillClimbingAlgorithm();
        next();
    }
}