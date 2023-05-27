package cu.edu.cujae.bd.visual.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import cu.edu.cujae.bd.dto.CarDto;
import cu.edu.cujae.bd.dto.ModelDto;
import cu.edu.cujae.bd.dto.RolDto;
import cu.edu.cujae.bd.dto.SituationDto;
import cu.edu.cujae.bd.dto.UserDto;
import cu.edu.cujae.bd.service.ServicesLocator;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.Bloom;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PrincipalController implements Initializable{
	
	public ObservableList<UserDto> users = FXCollections.observableArrayList();
	public ObservableList<CarDto> cars = FXCollections.observableArrayList();
	
	@FXML
	private AnchorPane anchorPaneUser;
	
	@FXML
	private AnchorPane anchorPaneCar;
	
	@FXML
	private Button carsButton;
	
	@FXML
	private Button usersButton;
	
	@FXML
	private Button insertUserButton;
	
	@FXML
	private Button logoutButton;
	
	@FXML
	private ImageView imgCarRent;
	
	// TABLE USERS
	@FXML
	private TableView<UserDto> usersTable;
	
	@FXML
	private TableColumn<UserDto,String> usernameColumn;
	
	@FXML
	private TableColumn<UserDto,String> rolColumn;
	
	//TABLE CARS
	@FXML
	private TableView<CarDto> carsTable;
	
	@FXML
	private TableColumn<CarDto,String> plateColumn;
	
	@FXML
	private TableColumn<CarDto,String> situationColumn;
	
	@FXML
	private TableColumn<CarDto,String> colorColumn;
	
	@FXML
	private TableColumn<CarDto,Integer> kmColumn;
	
	@FXML
	private TableColumn<CarDto,String> modelColumn;
	
	
	public void inicio() {
		anchorPaneCar.setVisible(false);	
		anchorPaneUser.setVisible(false);
		usersTable.setVisible(false);
		carsTable.setVisible(false);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		configurarTablaUser();
		configurarTablaCar();
		try {
			rellenarTablaUser();
			rellenarTablaCar();
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		
	}
	
	/*********************************             Tables              **************************************/
	
	public void configurarTablaUser() {
		usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
		rolColumn.setCellValueFactory(cellData -> {
			RolDto rol = cellData.getValue().getRol();
			String nameRol = rol != null ? rol.getRol() : "";
			return new SimpleStringProperty(nameRol);
		});
		usersTable.setItems(users);
	}
	
	public void rellenarTablaUser() throws SQLException {
		users.clear();
		ObservableList<UserDto> usersList = ServicesLocator.getUserServices().getAllUsers();
		users.setAll(usersList);
	}
	
	public void configurarTablaCar() {
		plateColumn.setCellValueFactory(new PropertyValueFactory<>("plate"));
		colorColumn.setCellValueFactory(new PropertyValueFactory<>("color"));
		kmColumn.setCellValueFactory(new PropertyValueFactory<>("km"));
		modelColumn.setCellValueFactory(cellData -> {
    		ModelDto model = cellData.getValue().getModel();
    		String nameModel = model != null ? model.getNameModel() : "";
    		return new SimpleStringProperty(nameModel);
		});
		situationColumn.setCellValueFactory(cellData -> {
    		SituationDto situation = cellData.getValue().getSituation();
    		String nameSituation = situation != null ? situation.getSituation() : "";
    		return new SimpleStringProperty(nameSituation);
		});
		carsTable.setItems(cars);
	}
	
	public void rellenarTablaCar() throws SQLException {
		cars.clear();
		ObservableList<CarDto> carsList = ServicesLocator.getCarServices().getAllCars();
		cars.setAll(carsList);
	}

	
	/*********************************             Action Buttons              **************************************/
	
	public void insertCarButtonOnAction(ActionEvent event) throws IOException {
				
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/insertCar.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.setResizable(false);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.show();	
	}
	
	public void carsButtonOnAction() {
		anchorPaneCar.setVisible(true);	
		anchorPaneUser.setVisible(false);
		usersTable.setVisible(false);
		carsTable.setVisible(true);
		
	}
	
	public void usersButtonOnAction() {
		anchorPaneUser.setVisible(true);
		usersTable.setVisible(true);
		anchorPaneCar.setVisible(false);
		carsTable.setVisible(false);
	}
	
	public void exitButtonOnAction(ActionEvent event) {
		//Main.getStage().close();
	}
	
	
	public void logoutButtonAction(ActionEvent event) throws IOException {
		/*Main main = new Main();
		main.changeSceneTam("fxml/login.fxml",600,500);*/
	}
	
	/*********************************             Effects              **************************************/
	
	public void carsButtonMoved() {
		Bloom bloom = new Bloom();
		carsButton.setEffect(bloom);
		
	}
	public void carsButtonExited() {
		carsButton.setEffect(null);
	}
	public void usersButtonMoved() {
		Bloom bloom = new Bloom();
		usersButton.setEffect(bloom);
	}
	public void usersButtonExited() {
		usersButton.setEffect(null);
	}
	public void iconMoved() {
		Bloom bloom = new Bloom();
		imgCarRent.setEffect(bloom);
	}
	public void iconExited() {
		imgCarRent.setEffect(null);
	}
	public void logoutButtonMoved() {
		Bloom bloom = new Bloom();
		logoutButton.setEffect(bloom);
	}
	public void logoutButtonExited() {
		logoutButton.setEffect(null);
	}
	
}
