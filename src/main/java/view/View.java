package view;

import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public abstract class View {
    protected static Scene scene;
    public abstract Parent initView() throws IOException;
}
