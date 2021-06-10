
package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import model.Population;

import java.io.IOException;
import java.util.Objects;

public class MainMenuView extends View {

    public Parent initView() throws IOException {
        return FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/MainMenu.fxml")));
    }
}