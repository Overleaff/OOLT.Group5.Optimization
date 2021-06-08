package controller;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import view.*;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        var scene = new Scene(new Pane());

        ViewSwitcher.setScene(scene);
        ViewSwitcher.switchTo(new MainMenuView());

        primaryStage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/icon/mainicon.png"))));
        primaryStage.setTitle("Evolutionary Algorithms Simulator");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
