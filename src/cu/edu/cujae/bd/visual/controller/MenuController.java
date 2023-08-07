package cu.edu.cujae.bd.visual.controller;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import cu.edu.cujae.bd.visual.models.Model;
import cu.edu.cujae.bd.visual.views.AdminMenuOption;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

public class MenuController implements Initializable{
    @FXML
    private Button dashboardButton;
    @FXML
    private Button reportsButton;
    @FXML
    private Button usersButton;
    @FXML
    private Button logoutButton;
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
    }

   public void addListener(){
        dashboardButton.setOnAction(event -> onDashboard());
        reportsButton.setOnAction(event -> onReports());
        usersButton.setOnAction(event -> onUsers());
        logoutButton.setOnAction(event -> onLogout());
        contractButton.setOnAction(event -> onContract());
        carButton.setOnAction(event -> onCar());
        touristButton.setOnAction(event -> onTourist());
        driverButton.setOnAction(event -> onDriver());

   }

   public void onDashboard(){   
          Model.getInstanse().getViewFactory().getSelectedMenu().set(AdminMenuOption.DASHBOARD);
          carButton.setStyle("-fx-background-color: transparent;");
          dashboardButton.setStyle("-fx-background-color: linear-gradient(to bottom right, #33415C ,#485C82 );");
          reportsButton.setStyle("-fx-background-color: transparent;");
          usersButton.setStyle("-fx-background-color: transparent;");
          contractButton.setStyle("-fx-background-color: transparent;");
          touristButton.setStyle("-fx-background-color: transparent;");
          driverButton.setStyle("-fx-background-color: transparent;");
   }
   public void onReports(){
          Model.getInstanse().getViewFactory().getSelectedMenu().set(AdminMenuOption.REPORTS);
          carButton.setStyle("-fx-background-color: transparent;");
          dashboardButton.setStyle("-fx-background-color: transparent;");
          reportsButton.setStyle("-fx-background-color: linear-gradient(to bottom right, #33415C ,#485C82 );");
          usersButton.setStyle("-fx-background-color: transparent;");
          contractButton.setStyle("-fx-background-color: transparent;");
          touristButton.setStyle("-fx-background-color: transparent;");
          driverButton.setStyle("-fx-background-color: transparent;");
   }
   public void onUsers(){
          Model.getInstanse().getViewFactory().getSelectedMenu().set(AdminMenuOption.USERS);
          carButton.setStyle("-fx-background-color: transparent;");
          dashboardButton.setStyle("-fx-background-color: transparent;");
          reportsButton.setStyle("-fx-background-color: transparent;");
          usersButton.setStyle("-fx-background-color: linear-gradient(to bottom right, #33415C ,#485C82 );");
          contractButton.setStyle("-fx-background-color: transparent;");
          touristButton.setStyle("-fx-background-color: transparent;");
          driverButton.setStyle("-fx-background-color: transparent;");
   }
   public void onContract(){
          Model.getInstanse().getViewFactory().getSelectedMenu().set(AdminMenuOption.CONTRACTS);
          carButton.setStyle("-fx-background-color: transparent;");
          dashboardButton.setStyle("-fx-background-color: transparent;");
          reportsButton.setStyle("-fx-background-color: transparent;");
          usersButton.setStyle("-fx-background-color: transparent;");
          contractButton.setStyle("-fx-background-color: linear-gradient(to bottom right, #33415C ,#485C82 );");
          touristButton.setStyle("-fx-background-color: transparent;");
          driverButton.setStyle("-fx-background-color: transparent;");
   }
   public void onCar(){
          Model.getInstanse().getViewFactory().getSelectedMenu().set(AdminMenuOption.CARS);
          carButton.setStyle("-fx-background-color: linear-gradient(to bottom right, #33415C ,#485C82 );");
          dashboardButton.setStyle("-fx-background-color: transparent;");
          reportsButton.setStyle("-fx-background-color: transparent;");
          usersButton.setStyle("-fx-background-color: transparent;");
          contractButton.setStyle("-fx-background-color: transparent;");
          touristButton.setStyle("-fx-background-color: transparent;");
          driverButton.setStyle("-fx-background-color: transparent;");

   }
   public void onTourist(){
          Model.getInstanse().getViewFactory().getSelectedMenu().set(AdminMenuOption.TOURIST);
          carButton.setStyle("-fx-background-color: transparent;");
          dashboardButton.setStyle("-fx-background-color: transparent;");
          reportsButton.setStyle("-fx-background-color: transparent;");
          usersButton.setStyle("-fx-background-color: transparent;");
          contractButton.setStyle("-fx-background-color: transparent;");
          touristButton.setStyle("-fx-background-color: linear-gradient(to bottom right, #33415C ,#485C82 );");
          driverButton.setStyle("-fx-background-color: transparent;"); 
   }
   public void onDriver(){
          Model.getInstanse().getViewFactory().getSelectedMenu().set(AdminMenuOption.DRIVER);
          carButton.setStyle("-fx-background-color: transparent;");
          dashboardButton.setStyle("-fx-background-color: transparent;");
          reportsButton.setStyle("-fx-background-color: transparent;");
          usersButton.setStyle("-fx-background-color: transparent;");
          contractButton.setStyle("-fx-background-color: transparent;");
          touristButton.setStyle("-fx-background-color: transparent;");
          driverButton.setStyle("-fx-background-color: linear-gradient(to bottom right, #33415C ,#485C82 );");
   }  
 
   public void onManage(){
     /*manageButton.setOnMouseClicked(event ->{
       if(expande == false){
          managePane.setVisible(true);
          expande = true;     
       }else{
            managePane.setVisible(false);
           expande = false;
       }
     });
     */
}

  public void onLogout(){
     Alert alert = new Alert(AlertType.CONFIRMATION);
     alert.setHeaderText(null);
     alert.setTitle("Confirmation Message");
     alert.setContentText("Are you sure want to logout");
     Optional<ButtonType> option = alert.showAndWait();

     if(option.get().equals(ButtonType.OK)){
        Stage stage = (Stage) logoutButton.getScene().getWindow();
        Model.getInstanse().getViewFactory().closeStage(stage);
        Model.getInstanse().getViewFactory().showLoginWindow();       
     }     
   }
}