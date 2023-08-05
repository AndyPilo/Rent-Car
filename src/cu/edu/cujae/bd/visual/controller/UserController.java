package cu.edu.cujae.bd.visual.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import cu.edu.cujae.bd.dto.RolDto;
import cu.edu.cujae.bd.dto.UserDto;
import cu.edu.cujae.bd.service.ServicesLocator;
import cu.edu.cujae.bd.visual.models.Model;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class UserController implements Initializable{

    public ObservableList<UserDto> users = FXCollections.observableArrayList();

    @FXML
    private TableView<UserDto> usersTable;
    @FXML
    private TableColumn<UserDto, String> rolCol;
    @FXML
    private TableColumn<UserDto, String> usernameCol;

/****************************    TABLAS Y CHOICEBOX     ****************************/

    public void configurarTablaUser() {
        usernameCol.setCellValueFactory(new PropertyValueFactory<>("username"));
        rolCol.setCellValueFactory(cellData -> {
            RolDto rol = cellData.getValue().getRol();
            String tipoRol = rol != null ? rol.getRol() : "";
            return new SimpleStringProperty(tipoRol);
        });
    
        usersTable.setItems(users);

    }

    public void rellenarTablaUser() throws SQLException {
        users.clear();
        ObservableList<UserDto> userList = ServicesLocator.getUserServices().getAllUsers();
        users.setAll(userList);
    }
    
/****************************    BOTONES     ****************************/

    //se ejecuta al pulsar el boton new car
    public void openNew() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../fxml/addUser.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent parent = loader.getRoot();
        Stage stage = new Stage(StageStyle.UTILITY);
        stage.setScene(new Scene(parent));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

     public void onDeleteButton() {
        UserDto selectedUserDto = usersTable.getSelectionModel().getSelectedItem();
            if (selectedUserDto != null) {
                try {
                    ServicesLocator.getUserServices().deleteUser(selectedUserDto.getCodUser());;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                users.remove(selectedUserDto);
                usersTable.refresh();
            }else{
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("You must select a user");
                alert.showAndWait();
            }
    }

    //Se ejecuta al dar clic en el boton update
	public void openForEdit() {
		UserDto selectedUser = usersTable.getSelectionModel().getSelectedItem();

        if(selectedUser != null){
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../fxml/addUser.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            AddUserController addUserController = loader.getController();
            addUserController.setUpdate(true);
            addUserController.setTextField(selectedUser.getUsername());

            Parent parent = loader.getRoot();
            Stage stage = new Stage(StageStyle.UTILITY);
            stage.setScene(new Scene(parent));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        }else{
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("You must select a car");
            alert.showAndWait();
        }        
	}

    public void close() {
        Stage stage = (Stage) usersTable.getScene().getWindow();
        Model.getInstanse().getViewFactory().closeStage(stage);
    }
    public void minimice(){
        Stage stage = (Stage) usersTable.getScene().getWindow();
        stage.setIconified(true);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configurarTablaUser();
        try {
            rellenarTablaUser();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
