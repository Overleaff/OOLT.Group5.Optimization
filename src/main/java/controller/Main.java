package controller;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import algorithm.*;
import model.BackPack;
import view.*;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        var scene = new Scene(new Pane());

        ViewSwitcher.setScene(scene);
        ViewSwitcher.switchTo(new MainMenuView());

        primaryStage.setTitle("test a scene");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        /*launch(args);*/
        BackPack bp1 = new BackPack();

        Algorithm a = new GeneticAlgorithm();
        System.out.println(a.solve());

        a = new HillClimbingAlgorithm();
        a.solve();
        System.out.println(a.solve());

        a = new PSOAlgorithm(bp1);
        System.out.println(a.solve());
    }
}
