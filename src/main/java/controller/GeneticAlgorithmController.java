package controller;

import algorithm.GeneticAlgorithm;
import algorithm.HeuristicAlgorithm;
import javafx.animation.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.*;
import view.BackpackView;
import view.View;
import view.ViewSwitcher;

import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class GeneticAlgorithmController extends Controller {
    private static final int FLYING_ANIMATION_SPEED = 1;
    private static final float HIGHLIGHT_ANIMATION_SPEED = 2.5F;
    @FXML
    private static ScrollPane generationsScrollPane = new ScrollPane();
    @FXML
    private VBox generationsVBox = new VBox();
    @FXML
    private HBox flyItem = new HBox();
    @FXML
    private HBox flyBackItem = new HBox();
    @FXML
    private Label stepLabel = new Label();
    @FXML
    private Label timeLabel = new Label();
    @FXML
    private Button solveButton = new Button();
    @FXML
    private Button finishButton = new Button();
    @FXML
    private VBox poolVBox = new VBox();

    private ArrayList<BackPack> backPacks = new ArrayList<>();

    public GeneticAlgorithmController() {
        for (Individual i : population.getPopulation()) {
            backPacks.add((BackPack) i);
        }
    }

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
        flyItem.toFront();
        flyBackItem.toFront();
        generationsScrollPane.setContent(generationsVBox);
        addPoolElements(PoolElements.getElements());
        updateGenerations(generationsVBox, backPacks);
    }

    public void backButtonClicked(ActionEvent e) {
        h.setGenerationLevel(0);
        ViewSwitcher.switchTo(View.MAIN);
    }

    public void solveButtonClicked() {
        h.increaseGenerationLevel();
        if (h.getGenerationLevel() >= HeuristicAlgorithm.MAX_GENERATION || Population.isSatisfy(population.getBestIndividual())) {

            solveButton.setDisable(true);
            finishButton.setDisable(true);
            updateBestIndividual(generationsVBox, population.getBestIndividual());
        } else {
            System.out.println(backPacks);
            backPacks = GeneticAlgorithm.crossOverStep(backPacks);
            backPacks = GeneticAlgorithm.mutateStep(backPacks);
            animation();
        }
    }

    public void finishButtonClicked() {
        long start = System.nanoTime();
        solveButton.setDisable(true);
        finishButton.setDisable(true);
        while (!Population.isSatisfy(population.getBestIndividual()) && h.getGenerationLevel()+1 < HeuristicAlgorithm.MAX_GENERATION) {
            h.increaseGenerationLevel();
            backPacks = GeneticAlgorithm.crossOverStep(backPacks);
            backPacks = GeneticAlgorithm.mutateStep(backPacks);
            updateGenerations(generationsVBox, backPacks);
        }
        updateStepLabel();
        updateBestIndividual(generationsVBox, population.getBestIndividual());
        long elapsedTime = System.nanoTime() - start;
        timeLabel.setText(String.format("Elapsed time: %.3f seconds", (double) elapsedTime / 1_000_000_000));
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

    private void updateStepLabel() {
        stepLabel.setText("");
        if (h.getGenerationLevel() >= HeuristicAlgorithm.MAX_GENERATION)
            stepLabel.setText("Maximum generations reached.");
        if (!Population.isSatisfy(population.getBestIndividual()))
            stepLabel.setText(stepLabel.getText() + " " + "Best individual is not optimized.");
        else
            stepLabel.setText(stepLabel.getText() + " " + "Best individual is optimized. Problem solved!");
    }

    private void animation() {
        stepLabel.setText("Crossover: Swap half elements with each other.");
        flyItem.getChildren().clear();
        flyBackItem.getChildren().clear();

        AtomicInteger count = new AtomicInteger();
        while (count.incrementAndGet() <= 4) {
            flyItem.getChildren().add(getRandomItem());
            flyBackItem.getChildren().add(getRandomItem());
        }
        count.set(0);
        flyItem.setVisible(true);
        flyBackItem.setVisible(true);

        AtomicInteger yCurrent = new AtomicInteger(150), yBackCurrent = new AtomicInteger(300), repeat = new AtomicInteger(0);
        flyItem.setTranslateX(0);
        flyBackItem.setTranslateX(500);
        AnimationTimer fly = new AnimationTimer() {
            @Override
            public void handle(long l) {
                flyItem.setTranslateY(yCurrent.getAndAdd(FLYING_ANIMATION_SPEED));
                flyBackItem.setTranslateY(yBackCurrent.getAndAdd(-FLYING_ANIMATION_SPEED));
                if (flyItem.getTranslateY() == 300) {
                    yCurrent.set(150);
                    yBackCurrent.set(300);
                    flyItem.getChildren().clear();
                    flyBackItem.getChildren().clear();
                    while (count.incrementAndGet() <= 4) {
                        flyItem.getChildren().add(getRandomItem());
                        flyBackItem.getChildren().add(getRandomItem());
                    }
                    count.set(0);
                    repeat.incrementAndGet();
                }
                if (repeat.get() == 5) {
                    flyItem.setVisible(false);
                    flyBackItem.setVisible(false);
                    flyItem.getChildren().clear();
                    flyBackItem.getChildren().clear();
                    stop();
                    mutateAnimation();
                }
            }
        };

        Timeline boxHighlight = new Timeline(
                new KeyFrame(
                        Duration.seconds(0),
                        new KeyValue(Objects.requireNonNull(getSelectedNode(0)).styleProperty(), "-fx-background-color: green"),
                        new KeyValue(Objects.requireNonNull(getSelectedNode(Population.NUM_INDIVIDUAL - 1)).styleProperty(), "-fx-background-color: green")
                ),
                new KeyFrame(
                        Duration.seconds(HIGHLIGHT_ANIMATION_SPEED),
                        new KeyValue(Objects.requireNonNull(getSelectedNode(0)).styleProperty(), ""),
                        new KeyValue(Objects.requireNonNull(getSelectedNode(Population.NUM_INDIVIDUAL - 1)).styleProperty(), ""),
                        new KeyValue(Objects.requireNonNull(getSelectedNode(1)).styleProperty(), "-fx-background-color: green"),
                        new KeyValue(Objects.requireNonNull(getSelectedNode(Population.NUM_INDIVIDUAL - 2)).styleProperty(), "-fx-background-color: green")
                ),
                new KeyFrame(
                        Duration.seconds(HIGHLIGHT_ANIMATION_SPEED * 2),
                        new KeyValue(Objects.requireNonNull(getSelectedNode(1)).styleProperty(), ""),
                        new KeyValue(Objects.requireNonNull(getSelectedNode(Population.NUM_INDIVIDUAL - 2)).styleProperty(), ""),
                        new KeyValue(Objects.requireNonNull(getSelectedNode(2)).styleProperty(), "-fx-background-color: green"),
                        new KeyValue(Objects.requireNonNull(getSelectedNode(Population.NUM_INDIVIDUAL - 3)).styleProperty(), "-fx-background-color: green")
                ),
                new KeyFrame(
                        Duration.seconds(HIGHLIGHT_ANIMATION_SPEED * 3),
                        new KeyValue(Objects.requireNonNull(getSelectedNode(2)).styleProperty(), ""),
                        new KeyValue(Objects.requireNonNull(getSelectedNode(Population.NUM_INDIVIDUAL - 3)).styleProperty(), ""),
                        new KeyValue(Objects.requireNonNull(getSelectedNode(3)).styleProperty(), "-fx-background-color: green"),
                        new KeyValue(Objects.requireNonNull(getSelectedNode(Population.NUM_INDIVIDUAL - 4)).styleProperty(), "-fx-background-color: green")
                ),
                new KeyFrame(
                        Duration.seconds(HIGHLIGHT_ANIMATION_SPEED * 4),
                        new KeyValue(Objects.requireNonNull(getSelectedNode(3)).styleProperty(), ""),
                        new KeyValue(Objects.requireNonNull(getSelectedNode(Population.NUM_INDIVIDUAL - 4)).styleProperty(), ""),
                        new KeyValue(Objects.requireNonNull(getSelectedNode(4)).styleProperty(), "-fx-background-color: green"),
                        new KeyValue(Objects.requireNonNull(getSelectedNode(Population.NUM_INDIVIDUAL - 5)).styleProperty(), "-fx-background-color: green")
                ),
                new KeyFrame(
                        Duration.seconds(HIGHLIGHT_ANIMATION_SPEED * 5),
                        new KeyValue(Objects.requireNonNull(getSelectedNode(4)).styleProperty(), ""),
                        new KeyValue(Objects.requireNonNull(getSelectedNode(Population.NUM_INDIVIDUAL - 5)).styleProperty(), "")
                )
        );
        boxHighlight.play();
        fly.start();
    }

    private void mutateAnimation() {
        stepLabel.setText("Mutation: Randomly replace one element with new element not in that backpack before.");
        flyItem.setVisible(true);

        flyItem.getChildren().clear();
        flyItem.getChildren().add(getRandomItem());
        AtomicInteger xCurrent = new AtomicInteger(900), yCurrent = new AtomicInteger(350), xDestination = new AtomicInteger(50), repeat = new AtomicInteger(0);
        AnimationTimer fly = new AnimationTimer() {
            @Override
            public void handle(long l) {
                flyItem.setTranslateX(xCurrent.getAndAdd(-5));
                flyItem.setTranslateY(yCurrent.getAndAdd(-2));
                if(flyItem.getTranslateY() == 150) {
                    xCurrent.set(900);
                    yCurrent.set(350);
                    flyItem.getChildren().clear();
                    flyItem.getChildren().add(getRandomItem());
                    repeat.incrementAndGet();
                }
                if(repeat.get() == 5) {
                    flyItem.setVisible(false);
                    flyItem.getChildren().clear();
                    stop();
                }
            }
        };

        Timeline boxHighlight = new Timeline(
                new KeyFrame(
                        Duration.seconds(0),
                        new KeyValue(Objects.requireNonNull(getSelectedNode(0)).styleProperty(), "-fx-background-color: green")
                ),
                new KeyFrame(
                        Duration.seconds(HIGHLIGHT_ANIMATION_SPEED - 0.5),
                        new KeyValue(Objects.requireNonNull(getSelectedNode(0)).styleProperty(), ""),
                        new KeyValue(Objects.requireNonNull(getSelectedNode(1)).styleProperty(), "-fx-background-color: green")
                ),
                new KeyFrame(
                        Duration.seconds(HIGHLIGHT_ANIMATION_SPEED * 2 - 1),
                        new KeyValue(Objects.requireNonNull(getSelectedNode(1)).styleProperty(), ""),
                        new KeyValue(Objects.requireNonNull(getSelectedNode(2)).styleProperty(), "-fx-background-color: green")
                ),
                new KeyFrame(
                        Duration.seconds(HIGHLIGHT_ANIMATION_SPEED * 3 - 1.5),
                        new KeyValue(Objects.requireNonNull(getSelectedNode(2)).styleProperty(), ""),
                        new KeyValue(Objects.requireNonNull(getSelectedNode(3)).styleProperty(), "-fx-background-color: green")
                ),
                new KeyFrame(
                        Duration.seconds(HIGHLIGHT_ANIMATION_SPEED * 4 - 2),
                        new KeyValue(Objects.requireNonNull(getSelectedNode(3)).styleProperty(), ""),
                        new KeyValue(Objects.requireNonNull(getSelectedNode(4)).styleProperty(), "-fx-background-color: green")
                ),
                new KeyFrame(
                        Duration.seconds(HIGHLIGHT_ANIMATION_SPEED * 5 - 2.5),
                        new KeyValue(Objects.requireNonNull(getSelectedNode(4)).styleProperty(), "")
                )
        );
        boxHighlight.play();
        fly.start();
        boxHighlight.setOnFinished(e -> {
            stepLabel.setText("Better generation found!");
            updateGenerations(generationsVBox, backPacks);
        });
    }
}
