package algorithm;

import controller.GeneticAlgorithmViewController;
import javafx.animation.FadeTransition;
import javafx.animation.FillTransition;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import model.BackPack;
import model.Element;
import model.Individual;

import java.util.Collections;
import java.util.Comparator;

public class GeneticAlgorithm extends HeuristicAlgorithm {

    private ObservableList<BackPack> population = FXCollections.observableArrayList();

    public GeneticAlgorithm() {
        for (Individual i : getPopulation()) {
            population.add((BackPack) i);
        }
    }

    public static void main(String[] args) {
        GeneticAlgorithm gA = new GeneticAlgorithm();
        Individual bestInd = gA.solve();
        System.out.println(bestInd + ", " + bestInd.getWeight());
    }

    public BackPack doOtherSteps() {
        // sort individual theo fitness
        Collections.sort(
                population, Comparator.comparingDouble(BackPack::fitness)
        );
        for (int i = 0; i < NUM_INDIVIDUAL / 2; i++) {
            int finalI = i;
            Platform.runLater(() -> {
                crossover(population.get(finalI), population.get(NUM_INDIVIDUAL - finalI - 1));
            });
            Circle circle = new Circle(5.0, Color.BLACK);
            FadeTransition fadeTransition = new FadeTransition(Duration.minutes(2), circle);
            fadeTransition.setFromValue(0);
            fadeTransition.setToValue(1.0);
            fadeTransition.play();
            VBox box = (VBox) GeneticAlgorithmViewController.getSelectedNode(i);
            assert box != null;
            box.setStyle("-fx-background-color: green");

            VBox box2 = (VBox) GeneticAlgorithmViewController.getSelectedNode(NUM_INDIVIDUAL - i - 1);
            assert box2 != null;
            box2.setStyle("-fx-background-color: green");
            box.setStyle(null);
            box2.setStyle(null);
        }
        for (int i = 0; i < NUM_INDIVIDUAL / 2; i++)
            mutate(population.get(i));
        return (BackPack) getBestIndividual();
    }

    public void crossover(BackPack g1, BackPack g2) {
        // swap half of elements in g1 with half of elements in g2, number of elements in g1 equals to g2
        Element[] items1 = g1.getElements();
        Element[] items2 = g2.getElements();
        int i;
        double tmpWei;
        String tmpString;
        for (i = 0; i < g1.getNumOfElement(); i++) {
            if (!g1.isContain(items2[i]) && !g2.isContain(items1[i])) {
                tmpWei = items1[i].getWeight();
                tmpString = items1[i].getImageFile();
                g1.updateElement(i, items2[i].getWeight(), items2[i].getImageFile());
                g2.updateElement(i, tmpWei, tmpString);
            }
        }
    }

    public void mutate(BackPack bp) {
        // update randomly 1/10 elements in the backpack with the new element not in backpack before
        int ran = (int) (Math.random() * 10);
        Element e = bp.getNewRandomElement();
        bp.updateElement(ran, e.getWeight(), e.getImageFile());
    }

    public ObservableList<BackPack> getPopulations() {
        return population;
    }
}
