package controller;

import algorithm.GeneticAlgorithm;
import algorithm.HeuristicAlgorithm;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import model.BackPack;
import model.Population;
import view.View;
import view.ViewSwitcher;

public class GeneticAlgorithmController extends Controller {
    @FXML
    private static ScrollPane generationsScrollPane = new ScrollPane();
    @FXML
    private VBox generationsVBox = new VBox();
    @FXML
    private HBox flyItem = new HBox();
    @FXML
    private HBox flyBackItem = new HBox();
    @FXML
    private Button solveButton = new Button();

    private static Node getSelectedNode(int index) {
        int row = index / (TOTAL_COLUMNS_BP + 1);
        int column = index % (TOTAL_COLUMNS_BP + 1);
        ObservableList<Node> children = ((GridPane) ((VBox) generationsScrollPane.getContent()).getChildren().get(1)).getChildren();
        for (Node node : children) {
            if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == column)
                return node;
        }
        return null;
    }

    @FXML
    public void initialize() {
        flyItem.setVisible(false);
        flyBackItem.setVisible(false);
        generationsScrollPane.setContent(generationsVBox);
        updateGenerations(generationsVBox, backPacks);
    }

    public void backButtonClicked(ActionEvent e) {
        HeuristicAlgorithm.generationLevel = 0;
        ViewSwitcher.switchTo(View.INIT);
    }

    // population take from InitPopulation
    // use GeneticAlgorithm.crossOverStep() to generate new individuals then visualize it
    // nhớ sau khi mutate, và crossover step phải update population
    public void solveButtonClicked() {
        if (HeuristicAlgorithm.generationLevel++ >= HeuristicAlgorithm.MAX_GENERATION || Population.isSatisfy(population.getBestIndividual())) {
            solveButton.setDisable(true);
            updateBestIndividual(generationsVBox, (BackPack) population.getBestIndividual());
        } else {
            backPacks = GeneticAlgorithm.crossOverStep(backPacks);
            population.setPopulation(backPacks);
            crossOverAnimation();
            backPacks = GeneticAlgorithm.mutateStep(backPacks);
            population.setPopulation(backPacks);
        }
    }

    private void crossOverAnimation() {
        flyItem.toFront();
        flyBackItem.toFront();
        flyItem.getChildren().clear();
        flyBackItem.getChildren().clear();
        int count = 0;
        while (count++ < 4) {
            flyItem.getChildren().add(getRandomItem());
            flyBackItem.getChildren().add(getRandomItem());
        }
        flyItem.setVisible(true);
        flyBackItem.setVisible(true);

        Timeline timeline = new Timeline(
                new KeyFrame(
                        Duration.seconds(0),
                        new KeyValue(flyItem.translateYProperty(), 150),
                        new KeyValue(flyBackItem.translateYProperty(), 300)
                ),
                new KeyFrame(
                        Duration.seconds(1.5),
                        new KeyValue(flyItem.translateYProperty(), 300),
                        new KeyValue(flyBackItem.translateYProperty(), 150)
                )
        );
        timeline.play();
    }
}
