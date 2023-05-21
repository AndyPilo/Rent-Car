package cu.edu.cujae.bd.visual.controller;

import cu.edu.cujae.bd.dto.RolDto;
import cu.edu.cujae.bd.dto.UserDto;
import cu.edu.cujae.bd.service.ServicesLocator;
import cu.edu.cujae.bd.service.UserServices;
import cu.edu.cujae.bd.utils.Encription;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class SigninController {

    UserServices userServices = ServicesLocator.getUserServices();

    @FXML
    private TextField userField;

    @FXML
    private PasswordField passField;

    @FXML
    private Button loginButton;

    @FXML
    private Label loginMessage;

    @FXML
    private PasswordField confirmPassField;

    @FXML
    void loginButtonAction(ActionEvent event) {

    }

    @FXML
    void loginButtonExited(MouseEvent event) {

    }

    @FXML
    void loginButtonMoved(MouseEvent event) {

    }

    public void onSigninButtonAction(ActionEvent event) {
        String username = userField.getText();
        String password = Encription.getMd5(passField.getText());
        RolDto rol = new RolDto(2,"user");
        UserDto user = new UserDto(username,password,rol);
        userServices.insertUser(user);

        if(userField.getText().isEmpty()==true || passField.getText().isEmpty() == true ){
            loginMessage.setText("Please enter your data");	
			userField.setText("");
			passField.setText("");
        }else if(passField.getText().equals(confirmPassField.getText())){
            loginMessage.setText("passwords must match");	
			userField.setText("");
			passField.setText("");
        }
    }

}