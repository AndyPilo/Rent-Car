package cu.edu.cujae.bd.visual.controller;

import cu.edu.cujae.bd.visual.models.Model;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ContractController {
    @FXML
    private Button closeButton;

     public void close() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        Model.getInstanse().getViewFactory().closeStage(stage);
    }
}
