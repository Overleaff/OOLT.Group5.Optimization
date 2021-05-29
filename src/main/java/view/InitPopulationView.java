package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class InitPopulationView extends View{

    public Parent initView() {
        Text test = new Text("Hello World");
        Button next = new Button("next");
        next.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ViewSwitcher.switchTo(new MainMenuView());
            }
        });

        VBox vbox = new VBox(test, next);
        vbox.setPrefWidth(720);
        vbox.setPrefHeight(480);
        return new Group(vbox);
    }
}
