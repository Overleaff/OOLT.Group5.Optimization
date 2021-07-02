package controller;

import algorithm.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import view.View;
import view.ViewSwitcher;

import java.io.IOException;

public class MainMenuController extends Controller {
    @FXML
    Button AlgorithmButton;
    @FXML
    HBox AlgorithmHBox;

    public Button createButtonForAlgorithm(){
        Button b = new Button();
        b.setText(h.getName());
        b.setOnAction(e -> {
            ViewSwitcher.switchTo(View.INIT);
        });
        return b;
    }

    @FXML
    public void initialize(){
        AlgorithmHBox.getChildren().add(createButtonForAlgorithm());
        ViewSwitcher.switchTo(View.INIT);
    }
}