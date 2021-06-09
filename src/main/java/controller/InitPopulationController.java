package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import model.BackPack;
import model.Individual;
import view.*;

import java.io.IOException;
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

    public void geneticAlgorithmButtonClicked(ActionEvent actionEvent) throws IOException {
        ViewSwitcher.switchTo(new GeneticAlgorithmView());
    }

    public void psoButtonClicked(ActionEvent actionEvent) throws IOException {

    }

    public void hillClimbingButtonClicked(ActionEvent actionEvent) throws IOException {
        ViewSwitcher.switchTo(new HillClimbingView());
    }
}
