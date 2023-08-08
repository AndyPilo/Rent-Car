package cu.edu.cujae.bd.visual.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import cu.edu.cujae.bd.dto.BrandDto;
import cu.edu.cujae.bd.dto.ModelDto;
import cu.edu.cujae.bd.dto.RolDto;
import cu.edu.cujae.bd.dto.UserDto;
import cu.edu.cujae.bd.service.ServicesLocator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class AddUserController implements Initializable{
    private ObservableList<RolDto> roles = FXCollections.observableArrayList();
    private UserDto selectedUser;
    
    @FXML
    private Button cancelButton;
    @FXML
    private PasswordField confPassField;
    @FXML
    private PasswordField passField;
    @FXML
    private ChoiceBox<RolDto> rolMenu;
    @FXML
    private Button saveButton;
    @FXML
    private TextField userField;

    public void initAtributes(){
        this.selectedUser = new UserDto();
    }

    public void initAtributes(UserDto user){
        this.selectedUser = user;
        this.userField.setText(user.getUsername());
    }

    public void configurarChoiceBoxSituation() {
        rolMenu.setConverter(new StringConverter<RolDto>() {
            @Override
            public String toString(RolDto rolDto) {
                return rolDto.getRol(); // Devuelve el nombre del modelo como cadena
            }

            @Override
            public RolDto fromString(String string) {
                return null; // No es necesario implementar este método para ChoiceBox
            }
        });

    }

    public void rellenarChoiceBoxSituation() {
        try {
            roles = ServicesLocator.getRolServices().getAllRol();
            rolMenu.setItems(roles);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void save () throws SQLException{
        if(userField.getText().equals("") || passField.getText().equals("") 
        || confPassField.getText().equals("") || rolMenu.getValue()==null){
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("You must fill in all fields");
            alert.showAndWait();
        }else if(!passField.getText().equals(confPassField.getText())){

            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Las contraseñas no coinciden");
            alert.showAndWait();
        }else if(this.selectedUser.getCodUser()==0){
            selectedUser.setUsername(userField.getText());
            selectedUser.setPassword(passField.getText());
            selectedUser.setRol(rolMenu.getValue());
            close();
        }else{
            selectedUser.setUsername(userField.getText());
            selectedUser.setPassword(passField.getText());
            selectedUser.setRol(rolMenu.getValue()); 
            close();
        }
      
    }
    
    public UserDto getSelectedUser(){
        return this.selectedUser;
    }

    public void close(){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configurarChoiceBoxSituation();
        rellenarChoiceBoxSituation();
    }

}
