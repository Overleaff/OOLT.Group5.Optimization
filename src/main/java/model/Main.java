package model;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.algorithm.Algorithm;
import model.algorithm.GeneticAlgorithm;
import model.algorithm.HeuristicAlgorithm;
import model.algorithm.HillClimbingAlgorithm;
import model.individual.BackPack;
import model.individual.Element;
import model.individual.Individual;
import model.individual.PoolElements;
import view.*;

import java.net.URL;

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
        Element[] poolElements = PoolElements.getElements();
        Algorithm a = new GeneticAlgorithm(poolElements);
        System.out.println(a.solve());

        Algorithm b = new HillClimbingAlgorithm(poolElements);
        b.solve();
        System.out.println(b.solve());
    }
}
