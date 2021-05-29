package view;

import javafx.scene.Parent;
import javafx.scene.Scene;

public abstract class View {
    protected static Scene scene;
    public abstract Parent initView();
}
