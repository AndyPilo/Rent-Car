package cu.edu.cujae.bd.visual.controller;

import java.net.URL;
import java.util.ResourceBundle;

import cu.edu.cujae.bd.visual.models.Model;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

public class ApplicationController implements Initializable{

    @FXML
    private BorderPane applicationId;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
     
        Model.getInstanse().getViewFactory().getSelectedMenu().addListener((observableValue, oldVal, newVal) -> {       
            switch(newVal){
                case CONTRACTS:
                    applicationId.setCenter(Model.getInstanse().getViewFactory().getContractView());
                    System.out.println(Model.getInstanse().getViewFactory().getSelectedMenu());
                    break;
                case CARS:
                    applicationId.setCenter(Model.getInstanse().getViewFactory().getCarView());
                    System.out.println(Model.getInstanse().getViewFactory().getSelectedMenu() + "En el case");
                    break;
                default:
                    applicationId.setCenter(Model.getInstanse().getViewFactory().getDashboard());
                    break;
            }
        
        });
    }
}
