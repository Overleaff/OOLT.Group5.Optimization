package controller;

import algorithm.GeneticAlgorithm;
import algorithm.HeuristicAlgorithm;
import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.BackPack;
import model.Element;
import view.BackpackView;
import view.ElementView;
import view.MainMenuView;
import view.ViewSwitcher;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class GeneticAlgorithmController implements Initializable {
    private static final int TOTAL_COLUMNS_BP = 4;
    private static final int INITIAL_WAIT_DELAY = 100;
    private Element[] elements;
    private ObservableList<BackPack> backPacks;
    private SimpleIntegerProperty shouldUpdateUI;
    private GeneticAlgorithm geneticAlgorithm;
    @FXML
    private VBox generationsVBox = new VBox();
    @FXML
    private static ScrollPane generationsScrollPane = new ScrollPane();
    @FXML
    private Button backButton = new Button();

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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        backButton.setCancelButton(true);
        geneticAlgorithm = new GeneticAlgorithm();
        shouldUpdateUI = HeuristicAlgorithm.shouldUpdateUI;
        backPacks = geneticAlgorithm.getPopulations();

        shouldUpdateUI.addListener((observableValue, oldVal, newVal) -> updateUI(newVal));
        updateGenerations();

        generationsVBox.setAlignment(Pos.CENTER);
        generationsScrollPane.setContent(generationsVBox);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    BackPack bestInd = (BackPack) geneticAlgorithm.solve();
                    updateBestIndividual(bestInd);
                });
            }
        }, INITIAL_WAIT_DELAY);
    }

    private void updateUI(Number newVal) {
        if(newVal.equals(1))
            updateGenerations();
        else if(newVal.equals(2))
            updateCrossOver();
        if(newVal.equals(3))
            updateMutate();
    }

    private void updateMutate() {
        System.out.println("test");
    }

    private void updateCrossOver() {
        for (int i = 0; i < HeuristicAlgorithm.NUM_INDIVIDUAL / 2; i++) {
            Node node = ((GridPane) ((VBox) generationsScrollPane.getContent()).getChildren().get(1)).getChildren().get(i);
            Node node2 = ((GridPane) ((VBox) generationsScrollPane.getContent()).getChildren().get(1)).getChildren().get(HeuristicAlgorithm.NUM_INDIVIDUAL - i - 1);
            node.setStyle("-fx-background-color: green");
            node2.setStyle("-fx-background-color: green");
        }
    }

    private void updateGenerations() {
        Label generationLabel = new Label("Generation " + geneticAlgorithm.getGenerationLevel());
        generationLabel.setStyle("-fx-font-size: 18; -fx-font-weight: bold");
        generationsVBox.getChildren().add(0, addBackpacksToGrid());
        generationsVBox.getChildren().add(0, generationLabel);
    }

    private void updateBestIndividual(BackPack bestInd) {
        Label resultLabel = new Label("Best individual:");
        resultLabel.setStyle("-fx-font-size: 18; -fx-font-weight: bold");
        Label noteLabel = new Label();
        if(geneticAlgorithm.getGenerationLevel() >= HeuristicAlgorithm.MAX_GENERATION)
            noteLabel.setText("Maximum generations reached.");
        if(!geneticAlgorithm.isSatisfy(bestInd))
            noteLabel.setText(noteLabel.getText() + " " + "Best individual is not optimized.");
        else
            noteLabel.setText(noteLabel.getText() + " " + "Best individual is optimized. Problem solved!");
        Image backpackImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/icon/backpack.png")), 120, 120, false, false);
        VBox elementBox = new ElementView(backpackImage, "Fitness", String.valueOf(bestInd.fitness()));
        // Add Details button
        Button detailsButton = new Button("Details");
        detailsButton.setOnAction(e -> {
            Stage backpackStage = new Stage();
            BackpackView bpView = new BackpackView(String.valueOf(bestInd.fitness()), bestInd.getElements(), bestInd.getNumOfElement());
            Scene backpackScene = new Scene(bpView);
            backpackStage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/icon/backpack.png"))));
            backpackStage.setTitle("Backpack View");
            backpackStage.setScene(backpackScene);
            backpackStage.showAndWait();
        });
        generationsVBox.getChildren().add(0, noteLabel);
        generationsVBox.getChildren().add(0, detailsButton);
        generationsVBox.getChildren().add(0, elementBox);
        generationsVBox.getChildren().add(0, resultLabel);
    }

    @FXML
    private void backButtonClicked(ActionEvent actionEvent) throws IOException {
        ViewSwitcher.switchTo(new MainMenuView());
    }

    private GridPane addElementsToGrid() {
        GridPane elementsGridPane = new GridPane();
        int row = 0, column = 0;
        for (Element element : elements) {
            Image elementImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/" + element.getImageFile())), 100, 100, false, false);
            VBox elementBox = new ElementView(elementImage, element.getImageFile().split("\\.")[0], String.valueOf(element.getWeight()));
            elementBox.setPrefSize(80, 80);
            elementsGridPane.add(elementBox, column, row);
            if (column == 5) {
                ++row;
                column = 0;
            } else if (column < 5) ++column;
        }
        return elementsGridPane;
    }

    private GridPane addBackpacksToGrid() {
        GridPane elementsGridPane = new GridPane();
        elementsGridPane.setHgap(20);
        elementsGridPane.setVgap(10);
        int row = 0, column = 0;
        Image backpackImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/icon/backpack.png")), 120, 120, false, false);
        for (BackPack backPack : backPacks) {
            VBox elementBox = new ElementView(backpackImage, "Fitness", String.valueOf(backPack.fitness()));
            elementBox.setPrefSize(150, 150);
            elementsGridPane.add(elementBox, column, row);
            // Add Details button
            Button detailsButton = new Button("Details");
            detailsButton.setOnAction(e -> {
                BackPack bp = getSelectedItem(GridPane.getRowIndex(detailsButton), GridPane.getColumnIndex(detailsButton));
                Stage backpackStage = new Stage();
                BackpackView bpView = new BackpackView(String.valueOf(bp.fitness()), bp.getElements(), bp.getNumOfElement());
                Scene backpackScene = new Scene(bpView);
                backpackStage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/icon/backpack.png"))));
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

    private BackPack getSelectedItem(int row, int column) {
        return backPacks.get((TOTAL_COLUMNS_BP + 1) * row + column);
    }
}
