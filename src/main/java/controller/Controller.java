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
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;
import model.BackPack;
import model.PoolElements;
import model.Population;
import view.BackpackView;
import view.ElementView;

import java.util.ArrayList;
import java.util.Objects;

public class Controller {
    protected static Population population = new Population();
    protected static BackPack bestInd = new BackPack();
    protected static ArrayList<BackPack> backPacks = new ArrayList<>();
    public static final int TOTAL_COLUMNS_BP = 4;

    public static void updateGenerations(VBox generationsVBox, ArrayList<BackPack> backPacks) {
        Label generationLabel = new Label("Generation " + HeuristicAlgorithm.generationLevel);
        generationLabel.setStyle("-fx-font-size: 18; -fx-font-weight: bold");
        generationsVBox.getChildren().add(0, addBackpacksToGrid(backPacks));
        generationsVBox.getChildren().add(0, generationLabel);
    }

    public static void updateGenerations(FlowPane generationsFlowPane, BackPack backPack) {
        Label generationLabel = new Label("Generation " + HeuristicAlgorithm.generationLevel);
        generationLabel.setStyle("-fx-font-size: 14; -fx-font-weight: bold");
        VBox backPackVBox = addBackpackToVBox(backPack);
        backPackVBox.getChildren().add(0, generationLabel);
        generationsFlowPane.getChildren().add(0, backPackVBox);
    }

    public static void updateBestIndividual(VBox generationsVBox, BackPack bestInd) {
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

    public static void updateBestIndividual(FlowPane generationsFlowPane, BackPack bestInd) {
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

    public static GridPane addBackpacksToGrid(ArrayList<BackPack> backPacks) {
        GridPane elementsGridPane = new GridPane();
        elementsGridPane.setHgap(20);
        elementsGridPane.setVgap(10);
        int row = 0, column = 0;
        Image backpackImage = new Image(Objects.requireNonNull(Controller.class.getResourceAsStream("/icon/backpack.png")), 120, 120, false, false);
        for (BackPack backPack : backPacks) {
            VBox elementBox = new ElementView(backpackImage, "Fitness", String.valueOf(backPack.fitness()));
            elementBox.setPrefSize(150, 150);
            elementsGridPane.add(elementBox, column, row);
            // Add Details button
            Button detailsButton = new Button("Details");
            detailsButton.setOnAction(e -> {
                BackPack bp = getSelectedItem(backPacks, GridPane.getRowIndex(detailsButton), GridPane.getColumnIndex(detailsButton));
                Stage backpackStage = new Stage();
                BackpackView bpView = new BackpackView(String.valueOf(bp.fitness()), bp.getElements(), bp.getNumOfElement());
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

    public static VBox addBackpackToVBox(BackPack backPack) {
        VBox elementVBox = new VBox();
        elementVBox.setSpacing(10);
        elementVBox.setAlignment(Pos.CENTER);
        Image backpackImage = new Image(Objects.requireNonNull(Controller.class.getResourceAsStream("/icon/backpack.png")), 120, 120, false, false);
        VBox elementBox = new ElementView(backpackImage, "Fitness", String.valueOf(backPack.fitness()));
        elementBox.setPrefSize(150, 150);
        elementVBox.getChildren().add(elementBox);
        // Add Details button
        Button detailsButton = new Button("Details");
        detailsButton.setOnAction(e -> {
            Stage backpackStage = new Stage();
            BackpackView bpView = new BackpackView(String.valueOf(backPack.fitness()), backPack.getElements(), backPack.getNumOfElement());
            Scene backpackScene = new Scene(bpView);
            backpackStage.getIcons().add(new Image(Objects.requireNonNull(Controller.class.getResourceAsStream("/icon/backpack.png"))));
            backpackStage.setTitle("Backpack View");
            backpackStage.setScene(backpackScene);
            backpackStage.showAndWait();
        });
        elementVBox.getChildren().add(detailsButton);
        return elementVBox;
    }

    public static BackPack getSelectedItem(ArrayList<BackPack> backPacks, int row, int column) {
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
