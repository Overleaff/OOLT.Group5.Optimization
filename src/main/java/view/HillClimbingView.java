package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;
import java.util.Objects;

public class HillClimbingView extends View{
    @Override
    public Parent initView() throws IOException {
        return FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/HillClimbing.fxml")));
    }
}
