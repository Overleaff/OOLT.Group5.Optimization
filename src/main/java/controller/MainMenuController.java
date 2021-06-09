package controller;

import javafx.event.ActionEvent;
import model.BackPack;
import model.Population;
import view.InitPopulationView;
import view.View;
import view.ViewSwitcher;

import java.io.IOException;

public class MainMenuController extends Controller {

    public void nextButton(ActionEvent actionEvent) throws IOException {
        Controller.population = new Population();
        Controller.population.initPopulation();
        Controller.generationLevel = 0;
        ViewSwitcher.switchTo(new InitPopulationView());
    }
}