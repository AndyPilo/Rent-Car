package cu.edu.cujae.bd.visual.views;

import cu.edu.cujae.bd.visual.controller.LoginController;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ViewFactory {

    private final StringProperty selectedMenu;
    private AnchorPane dashboardView;
    private AnchorPane manageView;

    public ViewFactory(){
        this.selectedMenu = new SimpleStringProperty("");
    }

    public StringProperty getSelectedMenu(){
        return this.selectedMenu;
    }

    public AnchorPane getDashboard(){
        if(dashboardView == null){
            try{
                dashboardView = new FXMLLoader(getClass().getResource("../fxml/dashboard.fxml")).load();   
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return dashboardView;
    }

    public AnchorPane getManageView(){
        if(manageView == null){
            try{
            manageView = new FXMLLoader(getClass().getResource("../fxml/manage.fxml")).load();
            }catch(Exception e){
                e.printStackTrace();
            }         
        }
        return manageView;
    }

    public void showLoginWindow(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/login.fxml"));
        LoginController loginController = new LoginController();
        loader.setController(loginController);
        createStage(loader);
    }

    public void showApplicationWindow(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/application.fxml"));
        createStage(loader);
    }

    public void createStage(FXMLLoader loader){
        Scene scene = null;
        try{
            scene = new Scene(loader.load());
        }catch(Exception e){
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Rent Car");
        stage.show();
    }

    public void closeStage(Stage stage){
        stage.close();
    }

}