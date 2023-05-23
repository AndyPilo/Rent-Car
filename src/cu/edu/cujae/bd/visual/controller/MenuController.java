package cu.edu.cujae.bd.visual.controller;

import java.net.URL;
import java.util.ResourceBundle;

import cu.edu.cujae.bd.visual.models.Model;
import cu.edu.cujae.bd.visual.views.AdminMenuOption;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class MenuController implements Initializable{

    @FXML
    private Button dashboardButton;
    @FXML
    private Button reportsButton;
    @FXML
    private Button manageButton;
    @FXML
    private Button usersButton;
    @FXML
    private Button logoutButton;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addListener();
    }

   public void addListener(){
        dashboardButton.setOnAction(event -> onDashboard());
        reportsButton.setOnAction(event -> onReports());
        manageButton.setOnAction(event -> onManage());
        usersButton.setOnAction(event -> onUsers());
        logoutButton.setOnAction(event -> onLogout());
   }


   public void onDashboard(){   
        Model.getInstanse().getViewFactory().getSelectedMenu().set(AdminMenuOption.DASHBOARD);
   }
   public void onReports(){
        Model.getInstanse().getViewFactory().getSelectedMenu().set(AdminMenuOption.REPORTS);
   }
   public void onManage(){
        Model.getInstanse().getViewFactory().getSelectedMenu().set(AdminMenuOption.MANAGE);
   }
   public void onUsers(){
        Model.getInstanse().getViewFactory().getSelectedMenu().set(AdminMenuOption.USERS);
   }
   public void onLogout(){
        Stage stage = (Stage) logoutButton.getScene().getWindow();
        Model.getInstanse().getViewFactory().showLoginWindow();
        Model.getInstanse().getViewFactory().closeStage(stage);
   }
}