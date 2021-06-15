package controller;

import algorithm.HeuristicAlgorithm;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import model.BackPack;
import model.Individual;
import view.View;
import view.ViewSwitcher;

import java.util.ArrayList;

public class InitPopulationController extends Controller {
    @FXML
    private VBox generationsVBox = new VBox();
    private ArrayList<BackPack> initialPopulation = new ArrayList<>();

    @FXML
    public void initialize() {
        for (Individual i : population.getPopulation()) {
            backPacks.add((BackPack) i);
            initialPopulation.add((BackPack) i);
        }
        if(initialPopulation.isEmpty())
            updateGenerations(generationsVBox, backPacks);
        else
            updateGenerations(generationsVBox, initialPopulation);
        bestInd = (BackPack) population.getBestIndividual();
        HeuristicAlgorithm.generationLevel++;
    }

    public void geneticAlgorithmButtonClicked(ActionEvent actionEvent) {
        ViewSwitcher.switchTo(View.GENETIC);
    }

    public void psoButtonClicked(ActionEvent actionEvent) {

    }

    public void hillClimbingButtonClicked(ActionEvent actionEvent) {
        ViewSwitcher.switchTo(View.HILL_CLIMBING);
    }
}
