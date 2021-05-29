package view;

import javafx.scene.Parent;
import javafx.scene.Scene;

public class ViewSwitcher {
    private static Scene scene;

    public static void setScene(Scene scene){
        ViewSwitcher.scene = scene;
    }

    public static void switchTo(View view){
        Parent root = view.initView();
        scene.setRoot(root);
    }
}
