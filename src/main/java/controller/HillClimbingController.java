package controller;

import algorithm.HeuristicAlgorithm;
import algorithm.HillClimbingAlgorithm;
import javafx.animation.PathTransition;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.BackPack;
import model.Element;
import model.PoolElements;
import model.Population;
import view.BackpackView;
import view.View;
import view.ViewSwitcher;

import java.util.Objects;

public class HillClimbingController extends Controller {
    @FXML
    private HBox flyItem = new HBox();
    @FXML
    private HBox flyBackItem = new HBox();
    @FXML
    private FlowPane generationsFlowPane = new FlowPane();
    @FXML
    private Label stepLabel = new Label();
    @FXML
    private Label timeLabel = new Label();
    @FXML
    private VBox poolVBox = new VBox();
    @FXML
    private Button solveButton = new Button();
    @FXML
    private Button finishButton = new Button();

    private BackPack oldInd = ((BackPack)bestInd).clone();

    @FXML
    public void initialize() {
        flyItem.setVisible(false);
        flyBackItem.setVisible(false);
        flyItem.toFront();
        flyBackItem.toFront();
        timeLabel.setText("");
        addPoolElements(PoolElements.getElements());
        updateGenerations(generationsFlowPane, bestInd);
    }

    public void solveButtonClicked() {
        if (h.getGenerationLevel()+1 >= HeuristicAlgorithm.MAX_GENERATION || Population.isSatisfy(bestInd)) {
            solveButton.setDisable(true);
            finishButton.setDisable(true);
            updateStepLabel();
            updateBestIndividual(generationsFlowPane, bestInd);
        } else {
            bestInd = HillClimbingAlgorithm.bestNextState((BackPack) bestInd, PoolElements.getElements());
            stepLabel.setText("Searching for next best state...");
            nextBestStateAnimation();
        }
    }

    public void finishButtonClicked() {
        long start = System.nanoTime();
        solveButton.setDisable(true);
        finishButton.setDisable(true);
        while (!Population.isSatisfy(bestInd) && h.getGenerationLevel()+1 < HeuristicAlgorithm.MAX_GENERATION) {
            bestInd = HillClimbingAlgorithm.bestNextState((BackPack) bestInd, PoolElements.getElements());
            updateGenerations(generationsFlowPane, bestInd);
        }
        updateStepLabel();
        updateBestIndividual(generationsFlowPane, bestInd);
        long elapsedTime = System.nanoTime() - start;
        timeLabel.setText(String.format("Elapsed time: %.3f seconds", (double) elapsedTime / 1_000_000_000));
    }

    private void updateStepLabel() {
        stepLabel.setText("");
        if (h.getGenerationLevel() >= HeuristicAlgorithm.MAX_GENERATION)
            stepLabel.setText("Maximum generations reached.");
        if (!Population.isSatisfy(bestInd))
            stepLabel.setText(stepLabel.getText() + " " + "Best individual is not optimized.");
        else
            stepLabel.setText(stepLabel.getText() + " " + "Best individual is optimized. Problem solved!");
    }

    private void nextBestStateAnimation() {
        flyItem.toFront();
        flyBackItem.toFront();
        // Backpack.diffItem will return item in oldInd but not in bestInd
        flyItem.getChildren().add(wrapItemInImage(Objects.requireNonNull(BackPack.diffItem(oldInd, (BackPack) bestInd))));
        flyBackItem.getChildren().add(wrapItemInImage(Objects.requireNonNull(BackPack.diffItem((BackPack) bestInd, oldInd))));
        flyItem.setVisible(true);
        flyBackItem.setVisible(true);
        PathTransition fly = new PathTransition(Duration.seconds(2), new Line(500, 400, 1200, 600), flyItem);
        PathTransition flyBack = new PathTransition(Duration.seconds(2), new Line(1200, 600, 500, 400), flyBackItem);
        fly.play();
        flyBack.play();
        fly.setOnFinished(e -> {
            flyItem.getChildren().clear();
            flyBackItem.getChildren().clear();
            flyItem.setVisible(false);
            flyBackItem.setVisible(false);
            stepLabel.setText("Better state found");
            updateGenerations(generationsFlowPane, bestInd);
        });
    }


    public void backButtonClicked() {
        h.setGenerationLevel(0);
        ViewSwitcher.switchTo(View.MAIN);
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


    public static ImageView wrapItemInImage(Element e){
        ImageView imageView = new ImageView(new Image(Objects.requireNonNull(Controller.class.getResourceAsStream("/img/" + e.getImageFile())), 100, 100, false, false));
        return imageView;
    }
}
