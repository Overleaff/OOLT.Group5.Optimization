package controller;

import algorithm.GeneticAlgorithm;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import model.Population;
import view.InitPopulationView;
import view.View;
import view.ViewSwitcher;

import java.io.IOException;

public class GeneticAlgorithmController extends Controller {
    // add next Button
    @FXML
    public void nextButton(ActionEvent ac) throws IOException{
        ViewSwitcher.switchTo(new InitPopulationView());
    }
    // add crossover button
    // add mutate button


    // population take from InitPopulation
    // use GeneticAlgorithm.crossOverStep() to generate new individuals then visualize it
    //nhớ sau khi mutate, và crossover step phải update population
    public void visualize(){
        /*GeneticAlgorithm.crossOverStep(Population.toBackPackArrays(population));*/
        GeneticAlgorithm.crossOverStep(Population.toBackPackArrays(population));
    }

    public void backButtonClicked() throws IOException{
        generationLevel--;
        ViewSwitcher.switchTo(new InitPopulationView());
    }
}
