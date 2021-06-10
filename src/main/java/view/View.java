
package view;

import javafx.scene.Parent;
import javafx.scene.Scene;
import model.BackPack;
import model.Individual;
import model.Population;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public enum View {
    MAIN("/fxml/MainMenu.fxml"),
    INIT("/fxml/InitPopulation.fxml"),
    GENETIC("/fxml/GeneticAlgorithm.fxml"),
    PSO("/fxml/PSOAlgorithm.fxml"),
    HILLCLIMBING("/fxml/HillClimbing.fxml");

    private String fileName;

    View(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}