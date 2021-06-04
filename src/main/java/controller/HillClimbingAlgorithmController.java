package controller;

import algorithm.HeuristicAlgorithm;
import algorithm.HillClimbingAlgorithm;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.BackPack;
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

public class HillClimbingAlgorithmController implements Initializable {
    public static final int TOTAL_COLUMNS_BP = 4;
    private static final int INITIAL_WAIT_DELAY = 100;
    public ObservableList<BackPack> backPack;
    public HillClimbingAlgorithm hillClimbingAlgorithm;
    @FXML
    public VBox generationsVBox = new VBox();
    @FXML
    public static ScrollPane generationsScrollPane = new ScrollPane();
    @FXML
    private Button backButton = new Button();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        backButton.setCancelButton(true);
        hillClimbingAlgorithm = new HillClimbingAlgorithm();
        backPack = FXCollections.observableArrayList();
        backPack.add(hillClimbingAlgorithm.getPopulations());
        backPack.addListener((ListChangeListener<BackPack>) change -> {
            //if(HeuristicAlgorithm.shouldUpdateUI)
                updateGenerations();
        });
        updateGenerations();

        generationsVBox.setAlignment(Pos.CENTER);
        generationsScrollPane.setContent(generationsVBox);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    BackPack bestInd = (BackPack) hillClimbingAlgorithm.solve();
                    updateBestIndividual(bestInd);
                });
            }
        }, INITIAL_WAIT_DELAY);
    }

    private void updateGenerations() {
        Label generationLabel = new Label("Generation " + hillClimbingAlgorithm.getGenerationLevel());
        generationLabel.setStyle("-fx-font-size: 18; -fx-font-weight: bold");
        generationsVBox.getChildren().add(0, addBackpackToGrid());
        generationsVBox.getChildren().add(0, generationLabel);
    }

    private void updateBestIndividual(BackPack bestInd) {
        Label resultLabel = new Label("Best individual:");
        resultLabel.setStyle("-fx-font-size: 18; -fx-font-weight: bold");
        Label noteLabel = new Label();
        if (hillClimbingAlgorithm.getGenerationLevel() >= HeuristicAlgorithm.MAX_GENERATION)
            noteLabel.setText("Maximum generations reached.");
        if (!hillClimbingAlgorithm.isSatisfy(bestInd))
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

    public void backButtonClicked(ActionEvent actionEvent) throws IOException {
        ViewSwitcher.switchTo(new MainMenuView());
    }

    public VBox addBackpackToGrid() {
        Image backpackImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/icon/backpack.png")), 120, 120, false, false);
        VBox elementBox = new ElementView(backpackImage, "Fitness", String.valueOf(backPack.get(0).fitness()));
        elementBox.setPrefSize(150, 150);
        // Add Details button
        Button detailsButton = new Button("Details");
        detailsButton.setOnAction(e -> {
            BackPack bp = backPack.get(0);
            Stage backpackStage = new Stage();
            BackpackView bpView = new BackpackView(String.valueOf(bp.fitness()), bp.getElements(), bp.getNumOfElement());
            Scene backpackScene = new Scene(bpView);
            backpackStage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/icon/backpack.png"))));
            backpackStage.setTitle("Backpack View");
            backpackStage.setScene(backpackScene);
            backpackStage.showAndWait();
        });
        elementBox.getChildren().add(detailsButton);
        return elementBox;
    }
}
