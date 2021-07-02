package controller;

import algorithm.Algorithm;
import algorithm.HeuristicAlgorithm;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import view.View;
import view.ViewSwitcher;

import java.util.Objects;

public class Visualization extends Application implements AlgorithmController {

    @Override
    public void visualize(Algorithm a) {
        Controller.a = a;
        launch();
    }

    @Override
    public void start(Stage stage){
        var scene = new Scene(new Pane());
        ViewSwitcher.setScene(scene);
        ViewSwitcher.switchTo(View.MAIN);

        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/icon/mainicon.png"))));
        stage.setTitle("Evolutionary Algorithms Simulator");
        stage.setScene(scene);
        stage.show();
    }
}
