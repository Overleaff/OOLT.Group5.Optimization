package controller;

import algorithm.HeuristicAlgorithm;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.*;
import view.BackpackView;
import view.ElementView;

import java.util.ArrayList;
import java.util.Objects;

public class Controller {
    protected static HeuristicAlgorithm h;
    protected static Population population = new Population();
    protected static Individual bestInd;
    public static final int TOTAL_COLUMNS_BP = 4;

    public static void updateGenerations(VBox generationsVBox, ArrayList<? extends Individual> individuals) {
        Label generationLabel = new Label("Generation " + h.getGenerationLevel());
        generationLabel.setStyle("-fx-font-size: 18; -fx-font-weight: bold");
        generationsVBox.getChildren().add(0, addBackpacksToGrid(individuals));
        generationsVBox.getChildren().add(0, generationLabel);
    }

    public static void updateGenerations(FlowPane generationsFlowPane, Individual individual) {
        Label generationLabel = new Label("Generation " + h.getGenerationLevel());
        generationLabel.setStyle("-fx-font-size: 14; -fx-font-weight: bold");
        VBox backPackVBox = addBackpackToVBox(individual);
        backPackVBox.getChildren().add(0, generationLabel);
        generationsFlowPane.getChildren().add(0, backPackVBox);
    }

    public static void updateBestIndividual(VBox generationsVBox, Individual bestInd) {
        Label resultLabel = new Label("Best individual:");
        resultLabel.setStyle("-fx-font-size: 18; -fx-font-weight: bold");
        Image backpackImage = new Image(Objects.requireNonNull(Controller.class.getResourceAsStream("/icon/backpack.png")), 120, 120, false, false);
        VBox elementBox = new ElementView(backpackImage, "Fitness", String.valueOf(bestInd.fitness()));
        // Add Details button
        Button detailsButton = new Button("Details");
        detailsButton.setOnAction(e -> {
            Stage backpackStage = new Stage();
            BackpackView bpView = new BackpackView(String.valueOf(bestInd.fitness()), bestInd.getElements(), bestInd.getNumOfElement());
            Scene backpackScene = new Scene(bpView);
            backpackStage.getIcons().add(new Image(Objects.requireNonNull(Controller.class.getResourceAsStream("/icon/backpack.png"))));
            backpackStage.setTitle("Backpack View");
            backpackStage.setScene(backpackScene);
            backpackStage.showAndWait();
        });
        generationsVBox.getChildren().add(0, detailsButton);
        generationsVBox.getChildren().add(0, elementBox);
        generationsVBox.getChildren().add(0, resultLabel);
    }

    public static void updateBestIndividual(FlowPane generationsFlowPane, Individual bestInd) {
        Label resultLabel = new Label("Best individual:");
        resultLabel.setStyle("-fx-font-size: 14; -fx-font-weight: bold");

        Image backpackImage = new Image(Objects.requireNonNull(Controller.class.getResourceAsStream("/icon/backpack.png")), 120, 120, false, false);
        VBox elementBox = new ElementView(backpackImage, "Fitness", String.valueOf(bestInd.fitness()));
        // Add Details button
        Button detailsButton = new Button("Details");
        detailsButton.setOnAction(e -> {
            Stage backpackStage = new Stage();
            BackpackView bpView = new BackpackView(String.valueOf(bestInd.fitness()), bestInd.getElements(), bestInd.getNumOfElement());
            Scene backpackScene = new Scene(bpView);
            backpackStage.getIcons().add(new Image(Objects.requireNonNull(Controller.class.getResourceAsStream("/icon/backpack.png"))));
            backpackStage.setTitle("Backpack View");
            backpackStage.setScene(backpackScene);
            backpackStage.showAndWait();
        });
        elementBox.getChildren().add(0, resultLabel);
        elementBox.getChildren().add(detailsButton);
        elementBox.setSpacing(10);
        generationsFlowPane.getChildren().add(0, elementBox);
    }

    public static GridPane addBackpacksToGrid(ArrayList<? extends Individual> individuals) {
        GridPane elementsGridPane = new GridPane();
        elementsGridPane.setHgap(20);
        elementsGridPane.setVgap(10);
        int row = 0, column = 0;
        Image backpackImage = new Image(Objects.requireNonNull(Controller.class.getResourceAsStream("/icon/backpack.png")), 120, 120, false, false);
        for (Individual i : individuals) {
            VBox elementBox = new ElementView(backpackImage, "Fitness", String.valueOf(i.fitness()));
            elementBox.setPrefSize(150, 150);
            elementsGridPane.add(elementBox, column, row);
            // Add Details button
            Button detailsButton = new Button("Details");
            detailsButton.setOnAction(e -> {
                Individual ind = getSelectedItem(individuals, GridPane.getRowIndex(detailsButton), GridPane.getColumnIndex(detailsButton));
                Stage backpackStage = new Stage();
                BackpackView bpView = new BackpackView(String.valueOf(ind.fitness()), ind.getElements(), ind.getNumOfElement());
                Scene backpackScene = new Scene(bpView);
                backpackStage.getIcons().add(new Image(Objects.requireNonNull(Controller.class.getResourceAsStream("/icon/backpack.png"))));
                backpackStage.setTitle("Backpack View");
                backpackStage.setScene(backpackScene);
                backpackStage.showAndWait();
            });
            elementsGridPane.add(detailsButton, column, row);
            if (column == TOTAL_COLUMNS_BP) {
                ++row;
                column = 0;
            } else if (column < TOTAL_COLUMNS_BP) ++column;
        }
        return elementsGridPane;
    }

    public static VBox addBackpackToVBox(Individual individual) {
        VBox elementVBox = new VBox();
        elementVBox.setSpacing(10);
        elementVBox.setAlignment(Pos.CENTER);
        Image backpackImage = new Image(Objects.requireNonNull(Controller.class.getResourceAsStream("/icon/backpack.png")), 120, 120, false, false);
        VBox elementBox = new ElementView(backpackImage, "Fitness", String.valueOf(individual.fitness()));
        elementBox.setPrefSize(150, 150);
        elementVBox.getChildren().add(elementBox);
        // Add Details button
        Button detailsButton = new Button("Details");
        detailsButton.setOnAction(e -> {
            Stage backpackStage = new Stage();
            BackpackView bpView = new BackpackView(String.valueOf(individual.fitness()), individual.getElements(), individual.getNumOfElement());
            Scene backpackScene = new Scene(bpView);
            backpackStage.getIcons().add(new Image(Objects.requireNonNull(Controller.class.getResourceAsStream("/icon/backpack.png"))));
            backpackStage.setTitle("Backpack View");
            backpackStage.setScene(backpackScene);
            backpackStage.showAndWait();
        });
        elementVBox.getChildren().add(detailsButton);
        return elementVBox;
    }

    public static Individual getSelectedItem(ArrayList<? extends Individual> backPacks, int row, int column) {
        return backPacks.get((TOTAL_COLUMNS_BP + 1) * row + column);
    }

    public static VBox getRandomItem() {
        int ran = (int) (Math.random() * 20);
        VBox item = new VBox();
        item.getChildren().add(new ImageView(new Image(Objects.requireNonNull(Controller.class.getResourceAsStream("/img/" + PoolElements.getElements()[ran].getImageFile())), 100, 100, false, false)));
        Label weight = new Label();
        weight.setText("Weight: " + PoolElements.getElements()[ran].getWeight());
        weight.setStyle("-fx-font-size: 14; -fx-font-weight: bold; -fx-background-color: white");
        item.getChildren().add(weight);
        return item;
    }
}
