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
                    break;
                case CARS:
                    applicationId.setCenter(Model.getInstanse().getViewFactory().getCarView());
                    break;
                case TOURIST:
                    applicationId.setCenter(Model.getInstanse().getViewFactory().getTouristView());
                    break;
                case DRIVER:
                    applicationId.setCenter(Model.getInstanse().getViewFactory().getDriverView());
                    break;
                case USERS:
                    applicationId.setCenter(Model.getInstanse().getViewFactory().getUsersView());
                    break;
                default:
                    applicationId.setCenter(Model.getInstanse().getViewFactory().getDashboard());
                    break;
            }
        
        });
    }
}
