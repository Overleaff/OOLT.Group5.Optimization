package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Element;

import java.util.Objects;

public class BackpackView extends ScrollPane {
    public static final int TOTAL_COLUMNS = 3;

    public BackpackView(String fitness, Element[] elements, int elementsLength) {
        setPannable(true);
        fitToWidthProperty().set(true);
        setPrefSize(600, 400);

        VBox vBox = new VBox();
        HBox titleBox = new HBox();
        titleBox.setAlignment(Pos.CENTER_LEFT);
        titleBox.setPadding(new Insets(10, 10, 10, 10));
        titleBox.setSpacing(10);
        Label titleLabel = new Label("Fitness score:");
        titleLabel.setStyle("-fx-font-size: 14");
        Label fitnessLabel = new Label();
        fitnessLabel.setText(fitness);
        fitnessLabel.setStyle("-fx-text-fill: #c0392b; -fx-font-size: 14; -fx-font-weight: bold");
        titleBox.getChildren().addAll(titleLabel, fitnessLabel);

        GridPane elementsGridPane = new GridPane();
        elementsGridPane.setPadding(new Insets(10, 10, 10, 10));
        elementsGridPane.setHgap(10);
        elementsGridPane.setVgap(10);
        int column = 0, row = 0;
        for (int i = 0; i < elementsLength; i++) {
            Element element = elements[i];
            Image elementImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/" + element.getImageFile())), 120, 120, false, false);
            VBox elementBox = new ElementView(elementImage, element.getImageFile().split("\\.")[0], String.valueOf(element.getWeight()));
            elementBox.setPrefSize(200, 100);
            elementsGridPane.add(elementBox, column, row);
            if (column == TOTAL_COLUMNS) {
                ++row;
                column = 0;
            } else if (column < TOTAL_COLUMNS) ++column;
        }

        vBox.getChildren().addAll(titleBox, elementsGridPane);
        setContent(vBox);
    }
}