package controller;

import algorithm.HillClimbingAlgorithm;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.BackPack;
import model.Element;
import model.Individual;
import model.PoolElements;
import view.BackpackView;
import view.ElementView;
import view.InitPopulationView;
import view.ViewSwitcher;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class HillClimbingController extends Controller{

    private BackPack bestInd = (BackPack) population.getBestIndividual();
    // add bestNextState Button
    @FXML
    VBox oldState = new VBox();
    @FXML
    VBox newState = new VBox();
    private int translateX = 0;
    @FXML
    private VBox pool = new VBox();
    @FXML
    public void initialize(){
        updateBestIndividual(oldState, bestInd);
        Controller.generationLevel++;
        poolElementsWindow(pool, PoolElements.getElements());
        BackPack newBest = HillClimbingAlgorithm.bestNextState(bestInd, PoolElements.getElements());
        updateBestIndividual(newState, newBest);
    }

    public void nextStateClicked(){

    }

    public static void poolElementsWindow(VBox poolBox, Element[] elements){
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
        poolBox.getChildren().add(0, detailsButton);
        poolBox.getChildren().add(0, elementBox);
    }

    public void backButtonClicked(ActionEvent actionEvent) throws IOException {
        generationLevel--;
        ViewSwitcher.switchTo(new InitPopulationView());
    }


}
