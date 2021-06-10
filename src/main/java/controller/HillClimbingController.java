package controller;

import algorithm.HeuristicAlgorithm;
import algorithm.HillClimbingAlgorithm;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import model.BackPack;
import model.PoolElements;
import model.Population;
import view.InitPopulationView;
import view.ViewSwitcher;

import java.io.IOException;

public class HillClimbingController extends Controller {
    @FXML
    private FlowPane generationsFlowPane = new FlowPane();
    @FXML
    private Label stepLabel = new Label();
    @FXML
    private Button solveButton = new Button();
    private BackPack bestInd;

    @FXML
    public void initialize() {
        solveButton.fire();
    }

    public void solveButtonClicked() {
        bestInd = HillClimbingAlgorithm.bestNextState(bestInd, PoolElements.getElements());
        if (HeuristicAlgorithm.generationLevel++ < HeuristicAlgorithm.MAX_GENERATION || Population.isSatisfy(bestInd)) {
            solveButton.setDisable(true);
            updateBestIndividual(generationsFlowPane, bestInd);
            stepLabel.setText("");
            if (HeuristicAlgorithm.generationLevel >= HeuristicAlgorithm.MAX_GENERATION)
                stepLabel.setText("Maximum generations reached.");
            if (!Population.isSatisfy(bestInd))
                stepLabel.setText(stepLabel.getText() + " " + "Best individual is not optimized.");
            else
                stepLabel.setText(stepLabel.getText() + " " + "Best individual is optimized. Problem solved!");
        }
        else {
            updateGenerations(generationsFlowPane, bestInd);
        }
    }

    public void backButtonClicked(ActionEvent actionEvent) throws IOException {
        HeuristicAlgorithm.generationLevel = 0;
        ViewSwitcher.switchTo(new InitPopulationView());
    }
}
