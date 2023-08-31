package cu.edu.cujae.bd.visual.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class UserController implements Initializable{

    public ObservableList<UserDto> users = FXCollections.observableArrayList();
    private UserDto selectedUser;
    private UserDto userAuth;

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
    
/****************************    BOTONES    ****************************/

    //se ejecuta al pulsar el boton new user
    public void openNew() throws IOException, SQLException {
        this.selectedUser = usersTable.getSelectionModel().getSelectedItem();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../fxml/addUser.fxml"));
        loader.load();
        AddUserController controller = loader.getController();
            
        controller.initAtributes();

        Parent parent = loader.getRoot();
        Stage stage = new Stage(StageStyle.UNDECORATED);
        stage.setScene(new Scene(parent));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
                
        
        UserDto user = controller.getSelectedUser();
        if(user!=null){
            ServicesLocator.getUserServices().insertUser(user); 
            rellenarTablaUser();
        }        
    }

     public void onDeleteButton() {

        UserDto selectedUserDto = usersTable.getSelectionModel().getSelectedItem();
            if (selectedUserDto != null) {

                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setHeaderText(null);
                alert.setTitle("Confirmation Message");
                alert.setContentText("Are you sure you want to delete ?");
                Optional<ButtonType> option = alert.showAndWait();

                if(option.get().equals(ButtonType.OK)){
                    
                    if(userAuth.getCodUser() == selectedUserDto.getCodUser()){
                        Alert alert2 = new Alert(AlertType.ERROR);
                        alert2.setHeaderText(null);
                        alert2.setContentText("No te puedes eliminar a ti mismo");
                        alert2.showAndWait();
                    }else if(userAuth.getRol().getCodRol() == selectedUserDto.getRol().getCodRol()){
                        Alert alert2 = new Alert(AlertType.ERROR);
                        alert2.setHeaderText(null);
                        alert2.setContentText("No puedes eliminar a otro admin");
                        alert2.showAndWait();
                    }else{
                        try {
                        ServicesLocator.getUserServices().deleteUser(selectedUserDto.getCodUser());;
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        users.remove(selectedUserDto);
                        usersTable.refresh();

                        Alert alert2 = new Alert(AlertType.INFORMATION);
                        alert2.setHeaderText(null);
                        alert2.setContentText("Successfuly");
                        alert2.showAndWait();
                    }
                }
            }else{
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("You must select a user");
                alert.showAndWait();
            }
            usersTable.setFocusTraversable(false);
    }

    //Se ejecuta al dar clic en el boton update
	public void openForEdit() throws SQLException, IOException {
         
		this.selectedUser = usersTable.getSelectionModel().getSelectedItem();

        if(this.selectedUser != null){
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../fxml/addUser.fxml"));
            loader.load();
            AddUserController controller = loader.getController();
            
            controller.initAtributes(this.selectedUser);

            Parent parent = loader.getRoot();
            Stage stage = new Stage(StageStyle.UNDECORATED);
            stage.setScene(new Scene(parent));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
                
            UserDto user = controller.getSelectedUser();
            if(user!=null){
                ServicesLocator.getUserServices().updateUser(user); 
                rellenarTablaUser();
            } 
        }else{
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("You must select a user");
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
