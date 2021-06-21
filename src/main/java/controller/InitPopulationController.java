package controller;

import algorithm.GeneticAlgorithm;
import algorithm.HeuristicAlgorithm;
import algorithm.HillClimbingAlgorithm;
import algorithm.PSOAlgorithm;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.BackPack;
import model.Element;
import model.Individual;
import model.PoolElements;
import view.BackpackView;
import view.View;
import view.ViewSwitcher;

import java.util.ArrayList;
import java.util.Objects;

public class InitPopulationController extends Controller {
    @FXML
    private VBox generationsVBox = new VBox();
    @FXML
    private VBox poolVBox = new VBox();
    private ArrayList<Individual> initialPopulation = new ArrayList<>();

    @FXML
    public void initialize() {
        initializePopulation();
        addPoolElements(PoolElements.getElements());
    }

    private void initializePopulation() {
        generationsVBox.getChildren().clear();
        initialPopulation = population.getPopulation();
        updateGenerations(generationsVBox, initialPopulation);
        bestInd = population.getBestIndividual();
        HeuristicAlgorithm.generationLevel++;
    }

    public void resetButtonClicked() {
        population.getPopulation().clear();
        initialPopulation.clear();
        population = h.initPopulation();
        HeuristicAlgorithm.generationLevel = 0;
        initializePopulation();
    }

    public void startSimulator(){
        ViewSwitcher.switchTo(h.getViewFile());
    }

    private void addPoolElements(Element[] elements) {
        Image poolElement = new Image(Objects.requireNonNull(Controller.class.getResourceAsStream("/icon/pool.png")), 120, 120, false, false);
        VBox elementBox = new VBox();
        elementBox.getChildren().add(new ImageView(poolElement));
        Button detailsButton = new Button("Details");
        detailsButton.setOnAction(e -> {
            Stage backpackStage = new Stage();
            BackpackView bpView = new BackpackView("0", elements, elements.length);
            Scene backpackScene = new Scene(bpView);
            backpackStage.getIcons().add(new Image(Objects.requireNonNull(Controller.class.getResourceAsStream("/icon/pool.png"))));
            backpackStage.setTitle("PoolElement View");
            backpackStage.setScene(backpackScene);
            backpackStage.showAndWait();
        });
        Label titleLabel = new Label("Elements Pool");
        titleLabel.setStyle("-fx-font-size: 16; -fx-font-weight: bold");
        poolVBox.getChildren().add(titleLabel);
        poolVBox.getChildren().add(elementBox);
        poolVBox.getChildren().add(detailsButton);
        poolVBox.setSpacing(10);
    }
}
