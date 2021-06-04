package controller;

import javafx.event.ActionEvent;
import view.GeneticAlgorithmView;
import view.ViewSwitcher;

import java.io.IOException;

public class MainMenuController {
    public void geneticAlgorithmButtonClicked(ActionEvent actionEvent) throws IOException {
        ViewSwitcher.switchTo(new GeneticAlgorithmView());
    }

    public void psoButtonClicked(ActionEvent actionEvent) {
    }

    public void hillClimbingButtonClicked(ActionEvent actionEvent) {
    }
}