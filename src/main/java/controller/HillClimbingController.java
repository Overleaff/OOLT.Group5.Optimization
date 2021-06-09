package controller;

import algorithm.HillClimbingAlgorithm;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.BackPack;
import model.Element;
import model.PoolElements;
import model.Population;
import view.*;

import java.io.IOException;
import java.util.Objects;

public class HillClimbingController extends Controller{
    public static final int MAX_WIDTH = 900;
    public static final int MAX_HEIGHT = 600;
    // add bestNextState Button
    @FXML
    VBox oldState = new VBox();
    @FXML
    VBox newState = new VBox();
    @FXML
    private VBox pool = new VBox();
    @FXML
    private HBox flyItem = new HBox();
    @FXML
    private HBox flyItemBack = new HBox();
    @FXML
    private Button finishButton;
    @FXML
    private Button updateButton;
    @FXML
    private Button nextStateButton;

    private double xCurrentState = oldState.getLayoutX();
    private double yCurrentState = oldState.getLayoutY();
    private double xPool = pool.getLayoutX();
    private double yPool = pool.getLayoutY();

    public void initialize(){
        updateBestIndividual(oldState, bestInd);
        poolElementsWindow(pool, PoolElements.getElements());

        flyItem.getChildren().add(getRandomItem());
        flyItemBack.getChildren().add(getRandomItem());
        flyItemBack.setVisible(false);
        flyItem.setVisible(false);
        newState.setVisible(false);
        finishButton.setDisable(true);
        updateButton.setDisable(true);
        nextStateButton.setVisible(true);
    }


    public void updateClicked(ActionEvent actionEvent) throws IOException {
        ViewSwitcher.switchTo(new InitPopulationView());
    }

    public void finishClicked(ActionEvent actionEvent) throws IOException {
        ViewSwitcher.switchTo(new MainMenuView());
    }

    public void nextStateClicked(){
        flyItem.setVisible(true);
        flyItemBack.setVisible(true);
        // create animation that some items move from current state to pool and come back
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                flyItem.setTranslateX(xCurrentState++);
                yCurrentState+=2;
                flyItem.setTranslateY(yCurrentState);

                flyItemBack.setTranslateX(xPool--);
                flyItemBack.setTranslateY(yPool--);
                if(flyItemBack.getTranslateY() < -150){
                    flyItemBack.setVisible(false);
                }
                if(flyItem.getTranslateX() > 150){
                    flyItem.setVisible(false);
                }
                if(!flyItem.isVisible() && !flyItemBack.isVisible()){
                    BackPack newBest = HillClimbingAlgorithm.bestNextState(bestInd, PoolElements.getElements());
                    System.out.println(newBest.fitness());
                    updateBestIndividual(newState, newBest);
                    newState.setVisible(true);
                    stop();
                    if(Population.isSatisfy(newBest)){
                        finishButton.setDisable(false);
                        updateButton.setDisable(true);
                    }
                    else{
                        finishButton.setDisable(true);
                        updateButton.setDisable(false);
                        Controller.bestInd = newBest;
                    }
                }
            }
        };
        timer.start();
        nextStateButton.setDisable(true);
    }

    public ImageView getRandomItem(){
        int ran = (int) (Math.random()*20);
        ImageView imageItem = new ImageView(new Image(Objects.requireNonNull(
                Controller.class.getResourceAsStream("/img/"+PoolElements.getElements()[ran].getImageFile()))));
        imageItem.setFitHeight(75);
        imageItem.setFitWidth(75);
        return imageItem;
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
}
