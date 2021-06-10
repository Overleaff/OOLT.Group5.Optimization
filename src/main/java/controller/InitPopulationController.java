package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import model.BackPack;
import model.Individual;
import view.*;

import java.util.ArrayList;

public class InitPopulationController extends Controller {
    @FXML
    public VBox generationsVBox = new VBox();
    private ArrayList<BackPack> backPacks = new ArrayList<>();
    @FXML
    public void initialize() {
        for(Individual i : population.getPopulation()){
            backPacks.add((BackPack) i);
        }
        Controller.updateGenerations(generationsVBox, backPacks);
        if(generationLevel == 0)
            Controller.bestInd = (BackPack) population.getBestIndividual();
        Controller.updateBestIndividual(generationsVBox, Controller.bestInd);
        Controller.generationLevel++;
    }

    public void geneticAlgorithmButtonClicked(ActionEvent actionEvent) {
        ViewSwitcher.switchTo(View.GENETIC);
    }

    public void psoButtonClicked(ActionEvent actionEvent) {
        ViewSwitcher.switchTo(View.PSO);
    }

    public void hillClimbingButtonClicked(ActionEvent actionEvent) {
        ViewSwitcher.switchTo(View.HILLCLIMBING);
    }
}
