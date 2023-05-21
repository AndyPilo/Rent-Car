package cu.edu.cujae.bd.visual.controller;

import cu.edu.cujae.bd.dto.UserDto;
import cu.edu.cujae.bd.service.ServicesLocator;
import cu.edu.cujae.bd.service.UserServices;
import cu.edu.cujae.bd.visual.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.Bloom;

public class LoginController {
	
	private UserServices userServices = ServicesLocator.getUserServices();
	
	@FXML
	private Button loginButton;
	
	@FXML
	private TextField userField;
	
	@FXML
	private PasswordField passField;
	
	@FXML
	private Label loginMessage;
	
	public void loginButtonAction(ActionEvent event) throws Exception{
			Main main = new Main();
		
			UserDto userAuth = userServices.getLoginUser(userField.getText(),passField.getText());
			
			String rol = userAuth != null ? userAuth.getRol().getRol():"";

			if(userField.getText().isEmpty()==true || passField.getText().isEmpty() == true) {
				loginMessage.setText("Please enter your data");	
				userField.setText("");
				passField.setText("");
			}else if(rol.equals("")){
				loginMessage.setText("Invalid login, please try again");
				userField.setText("");
				passField.setText("");
			}else if(rol.equals("admin")){
				main.changeSceneTam("fxml/principal.fxml", 900, 550);
			}else if(rol.equals("user")){
				main.changeSceneTam("fxml/principal.fxml", 900, 550);
			}
			

		loginMessage.setVisible(true);
	}
	
	/*********************************             Effects              **************************************/
	
	public void loginButtonMoved() {
		Bloom bloom = new Bloom();
		loginButton.setEffect(bloom);
	}
	public void loginButtonExited() {
		loginButton.setEffect(null);
	}
}
