package cu.edu.cujae.bd.visual.controller;

import java.net.URL;
import java.util.ResourceBundle;

import cu.edu.cujae.bd.visual.models.Model;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;


public class MenuController implements Initializable{

    @FXML
    private Button dashboardButton;
    @FXML
    private Button reportsButton;
    @FXML
    private Button manageButton;
    @FXML
    private Button usersButton;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addListener();
    }

   public void addListener(){
        dashboardButton.setOnAction(event -> onDashboard());
        reportsButton.setOnAction(event -> onReports());
        manageButton.setOnAction(event -> onManage());
        usersButton.setOnAction(event -> onUsers());
   }


   public void onDashboard(){   
        Model.getInstanse().getViewFactory().getSelectedMenu().set("dashboard");
   }
   public void onReports(){
        Model.getInstanse().getViewFactory().getSelectedMenu().set("reports");
        System.out.println("reportsButton");
   }
   public void onManage(){
        Model.getInstanse().getViewFactory().getSelectedMenu().set("manage");
        System.out.println(Model.getInstanse().getViewFactory().getSelectedMenu());
   }
   public void onUsers(){
        Model.getInstanse().getViewFactory().getSelectedMenu().set("users");
   }
}
