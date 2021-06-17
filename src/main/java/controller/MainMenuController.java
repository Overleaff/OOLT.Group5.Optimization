package controller;

import javafx.event.ActionEvent;
import view.View;
import view.ViewSwitcher;

import java.io.IOException;

public class MainMenuController extends Controller {

    public void nextButton(ActionEvent actionEvent) throws IOException {
        ViewSwitcher.switchTo(View.INIT);
    }
}