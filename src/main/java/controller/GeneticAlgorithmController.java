package controller;

import algorithm.GeneticAlgorithm;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import model.Population;
import view.View;
import view.ViewSwitcher;

public class GeneticAlgorithmController extends Controller {
    // add next Button
    @FXML
    public void nextButton(ActionEvent ac){
        ViewSwitcher.switchTo(View.INIT);
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

    public void backButtonClicked(){
        generationLevel--;
        ViewSwitcher.switchTo(View.MAIN);
    }
}
