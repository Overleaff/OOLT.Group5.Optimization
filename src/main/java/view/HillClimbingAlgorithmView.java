package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.Objects;

public class HillClimbingAlgorithmView extends View {

    public Parent initView() throws IOException {
        BorderPane root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/HillClimbingAlgorithm.fxml")));
        return new Group(root);
    }
}
