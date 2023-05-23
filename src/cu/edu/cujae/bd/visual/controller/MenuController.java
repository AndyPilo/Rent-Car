package cu.edu.cujae.bd.visual.controller;

import java.net.URL;
import java.util.ResourceBundle;

import cu.edu.cujae.bd.visual.models.Model;
import cu.edu.cujae.bd.visual.views.AdminMenuOption;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MenuController implements Initializable{
    boolean expande = false;
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
    @FXML
    private AnchorPane managePane;
    @FXML
    private Button contractButton;
    @FXML
    private Button carButton;
    @FXML
    private Button touristButton;
    @FXML
    private Button driverButton;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addListener();
        managePane.setVisible(false);
    }

   public void addListener(){
        dashboardButton.setOnAction(event -> onDashboard());
        reportsButton.setOnAction(event -> onReports());
        usersButton.setOnAction(event -> onUsers());
        logoutButton.setOnAction(event -> onLogout());
        manageButton.setOnAction(event -> onManage());
        contractButton.setOnAction(event -> onContract());
        carButton.setOnAction(event -> onCar());
   }


   public void onDashboard(){   
        Model.getInstanse().getViewFactory().getSelectedMenu().set(AdminMenuOption.DASHBOARD);
   }
   public void onReports(){
        Model.getInstanse().getViewFactory().getSelectedMenu().set(AdminMenuOption.REPORTS);
   }

   public void onUsers(){
        Model.getInstanse().getViewFactory().getSelectedMenu().set(AdminMenuOption.USERS);
   }

   public void onLogout(){
        Stage stage = (Stage) logoutButton.getScene().getWindow();
        Model.getInstanse().getViewFactory().showLoginWindow();
        Model.getInstanse().getViewFactory().closeStage(stage);
   }

   public void onContract(){
        Model.getInstanse().getViewFactory().getSelectedMenu().set(AdminMenuOption.CONTRACTS);
   }

   public void onCar(){
        Model.getInstanse().getViewFactory().getSelectedMenu().set(AdminMenuOption.CARS);
   }

   public void onTourist(){
     
   }

   public void onDriver(){
     
   }
  

   public void onManage(){
     //Model.getInstanse().getViewFactory().getSelectedMenu().set(AdminMenuOption.MANAGE);
     manageButton.setOnMouseClicked(event ->{
       if(expande == false){
          managePane.setVisible(true);
          manageButton.setStyle("-fx-background-color:  #9887FF");
          expande = true;     
       }else{
            managePane.setVisible(false);
            manageButton.setStyle("-fx-background-color: #001233");
           expande = false;
       }
     });
}
}