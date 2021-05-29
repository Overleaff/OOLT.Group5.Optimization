package view;

import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class ViewSwitcher {
    private static Scene scene;

    public static void setScene(Scene scene){
        ViewSwitcher.scene = scene;
    }

    public static void switchTo(View view) throws IOException {
        Parent root = view.initView();
        scene.setRoot(root);
    }
}