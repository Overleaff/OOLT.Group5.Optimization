package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ElementView extends VBox {
    public ElementView(Image img, String name, String cost) {
        setAlignment(Pos.CENTER);

        HBox itemInfo = new HBox();
        itemInfo.setAlignment(Pos.CENTER_RIGHT);
        itemInfo.setPadding(new Insets(10,5,5,5));
        itemInfo.setSpacing(10);
        Label itemName = new Label(name);
        Label itemCost = new Label(cost);
        itemName.setStyle("-fx-font-size: 14");
        itemCost.setStyle("-fx-font-weight: bold; -fx-font-size: 14; -fx-text-fill: #c0392b");
        itemInfo.getChildren().addAll(itemName, itemCost);

        ImageView itemImage = new ImageView(img);
        getChildren().addAll(itemImage, itemInfo);
    }
}
