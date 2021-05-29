package view;

import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class MainMenuView extends View{
    public static final int WIDTH_SCREEN = 720;
    public static final int HEIGHT_SCREEN = 480;

    public Parent initView() {
        Text test = new Text("this is main menu");
        Button next = new Button("next");
        next.setOnAction(evt -> System.out.println("go to second view"));

        VBox vbox = new VBox(test, next);
        vbox.setPrefHeight(HEIGHT_SCREEN);
        vbox.setPrefWidth(WIDTH_SCREEN);
        return new Group(vbox);
    }
}
