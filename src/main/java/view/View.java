
package view;

import javafx.scene.Parent;
import javafx.scene.Scene;
import model.BackPack;
import model.Individual;
import model.Population;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public abstract class View {
    protected static Scene scene;
    public abstract Parent initView() throws IOException;
}