package cu.edu.cujae.bd.visual.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import cu.edu.cujae.bd.visual.models.Model;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ContractController implements Initializable{
    @FXML
    private Button closeButton;

     public void close() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        Model.getInstanse().getViewFactory().closeStage(stage);
    }
    public void showRentCarWindow() throws IOException{
        FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../fxml/rentCar.fxml"));
            loader.load();
            //AddModelController controller = loader.getController();
            //controller.initAtributes();

            Parent parent = loader.getRoot();
            Stage stage = new Stage(StageStyle.UTILITY);
            stage.setScene(new Scene(parent));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
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
