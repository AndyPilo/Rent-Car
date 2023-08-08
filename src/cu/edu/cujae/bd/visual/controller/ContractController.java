package cu.edu.cujae.bd.visual.controller;

import java.net.URL;
import java.util.ResourceBundle;

import cu.edu.cujae.bd.visual.models.Model;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ContractController implements Initializable{
    @FXML
    private Button closeButton;

     public void close() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        Model.getInstanse().getViewFactory().closeStage(stage);
    }

    public void updateContract(){

    }

    public void insertContract(){

    }

    public void deleteContract(){

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
}
