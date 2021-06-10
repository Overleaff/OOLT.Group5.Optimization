package controller;

import javafx.event.ActionEvent;
import model.Population;
import view.View;
import view.ViewSwitcher;

public class MainMenuController extends Controller {

    public void nextButton(ActionEvent actionEvent){
        Controller.population = new Population();
        Controller.population.initPopulation();
        Controller.generationLevel = 0;
        ViewSwitcher.switchTo(View.INIT);
    }
}