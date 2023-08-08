package cu.edu.cujae.bd.visual.views;

import cu.edu.cujae.bd.visual.controller.LoginController;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ViewFactory {

    //ADMIN
    private final ObjectProperty<AdminMenuOption> selectedMenu;

    //USER
    private final ObjectProperty<UserMenuOption> selectedMenuUser;

    //MENU ADMIN
    private AnchorPane usersView;
    //MENU USER
    private AnchorPane dashboardView;
    private AnchorPane contractView;
    private AnchorPane carView;
    private AnchorPane touristView;
    private AnchorPane driverView;


    //POSITION OF THE APPLICATION
    private double xOffSet = 0;
    private double yOffSet = 0;

    public ViewFactory(){
        this.selectedMenu = new SimpleObjectProperty<>();
        this.selectedMenuUser = new SimpleObjectProperty<>();
    }

    public ObjectProperty<AdminMenuOption>  getSelectedAdminMenu(){
        return this.selectedMenu;
    }

     public ObjectProperty<UserMenuOption>  getSelectedUserMenu(){
        return this.selectedMenuUser;
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

    public AnchorPane getUsersView(){
        if(usersView == null){
            try{
            usersView = new FXMLLoader(getClass().getResource("../fxml/users.fxml")).load();
            }catch(Exception e){
                e.printStackTrace();
            }         
        }
        //System.out.println(selectedMenu);
        return usersView;
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

    public AnchorPane getCarView(){
        if(carView == null){
            try{
                carView = new FXMLLoader(getClass().getResource("../fxml/car.fxml")).load();  
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        //System.out.println(selectedMenu);
        return carView;
    }

    public AnchorPane getTouristView(){
        if(touristView == null){
            try{
                touristView = new FXMLLoader(getClass().getResource("../fxml/tourist.fxml")).load();  
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        //System.out.println(selectedMenu);
        return touristView;
    }

    public AnchorPane getDriverView(){
        if(driverView == null){
            try{
                driverView = new FXMLLoader(getClass().getResource("../fxml/driver.fxml")).load();  
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        //System.out.println(selectedMenu);
        return driverView;
    }

    public void showLoginWindow(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/login.fxml"));
        LoginController loginController = new LoginController();
        loader.setController(loginController);
        createStage(loader);
    }

    public void showApplicationWindow(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/application.fxml"));
        this.selectedMenu.set(AdminMenuOption.DASHBOARD);
        createStage(loader);
    }

    public void showAplicationUserWindow(){

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/applicationUser.fxml"));
        this.selectedMenuUser.set(UserMenuOption.DASHBOARD);
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