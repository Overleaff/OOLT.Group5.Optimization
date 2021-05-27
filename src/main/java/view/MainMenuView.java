package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;
import java.util.Objects;

public class MainMenuView extends View {
    public static final int WIDTH_SCREEN = 720;
    public static final int HEIGHT_SCREEN = 480;

    public Parent initView() throws IOException {
        return FXMLLoader.load(Objects.requireNonNull(getClass().getResource("fxml/MainMenu.fxml")));
    }
}
