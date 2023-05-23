package cu.edu.cujae.bd.visual.views;

import cu.edu.cujae.bd.visual.controller.LoginController;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ViewFactory {

    private final ObjectProperty<AdminMenuOption> selectedMenu;

    //MENU
    private AnchorPane dashboardView;
    private AnchorPane manageView;

    //MANAGE MENU
    private AnchorPane contractView;

    //POSiTION OF THE APPLICATION
    private double xOffSet = 0;
    private double yOffSet = 0;

    public ViewFactory(){
        this.selectedMenu = new SimpleObjectProperty<>();
    }

    public ObjectProperty<AdminMenuOption>  getSelectedMenu(){
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

    public AnchorPane getContractView(){
        if(contractView == null){
            try{
                contractView = new FXMLLoader(getClass().getResource("../fxml/addContract.fxml")).load();  
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return contractView;
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
        scene.setOnMousePressed(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event){
                xOffSet = event.getSceneX();
                yOffSet = event.getSceneY();
            }
        });

        Stage stage = new Stage();

        scene.setOnMouseDragged(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event){
                stage.setX(event.getScreenX() - xOffSet);
                stage.setY(event.getScreenY() - yOffSet);
            }
        });

        stage.setScene(scene);
        stage.setTitle("Rent Car");
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }

    public void closeStage(Stage stage){
        stage.close();
    }

}