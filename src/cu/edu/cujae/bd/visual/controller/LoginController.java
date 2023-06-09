package cu.edu.cujae.bd.visual.controller;

import java.net.URL;
import java.util.ResourceBundle;

import cu.edu.cujae.bd.dto.UserDto;
import cu.edu.cujae.bd.service.ServicesLocator;
import cu.edu.cujae.bd.service.UserServices;
import cu.edu.cujae.bd.visual.models.Model;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.Bloom;
import javafx.stage.Stage;

public class LoginController implements Initializable{
	
	private UserServices userServices = ServicesLocator.getUserServices();
	@FXML
	private Button loginButton;
	@FXML
	private TextField userField;
	@FXML
	private PasswordField passField;
	@FXML
	private Label lblMessage;
	@FXML
    private Button closeButton;
	@FXML
    private Button AwesomeIcon;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loginButton.setOnAction(evento -> onLoginButton());
	}

	public void onLoginButton(){
		Stage stage = (Stage) lblMessage.getScene().getWindow(); // PARA OBTENER LA VENTANA DEL LOGIN Y LUEGO CERRARLA
		
		UserDto userAuth = userServices.getLoginUser(userField.getText(),passField.getText());
		String rol = userAuth != null ? userAuth.getRol().getRol():"";

		if(userField.getText().isEmpty()==true || passField.getText().isEmpty() == true) {
			lblMessage.setText("Please enter your data");	
			userField.setText("");
			passField.setText("");
		}else if(rol.equals("")){
			lblMessage.setText("Invalid login, please try again");
			userField.setText("");
			passField.setText("");
		}else if(rol.equals("admin")){		
			Model.getInstanse().getViewFactory().showApplicationWindow();
			Model.getInstanse().getViewFactory().closeStage(stage);
		}else if(rol.equals("user")){
			Model.getInstanse().getViewFactory().closeStage(stage);
			Model.getInstanse().getViewFactory().showApplicationWindow();
		}			
	}

	public void close() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        Model.getInstanse().getViewFactory().closeStage(stage);
    }
  
	
	/*********************************             Effects              **************************************/
	
	public void loginButtonMoved() {
		Bloom bloom = new Bloom();
		loginButton.setEffect(bloom);
	}
	public void loginButtonExited() {
		loginButton.setEffect(null);
	}
	public void moved(){
        //AwesomeIcon.setStyle("-fx-background-color:  #bf0603");
    }
    public void exited(){
        //AwesomeIcon.setStyle("-fx-background-color:  #000000");
		
    }
}
