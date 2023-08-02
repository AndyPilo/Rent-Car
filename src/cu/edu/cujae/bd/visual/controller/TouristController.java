package cu.edu.cujae.bd.visual.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import cu.edu.cujae.bd.dto.CountryDto;
import cu.edu.cujae.bd.dto.TouristDto;
import cu.edu.cujae.bd.service.ServicesLocator;
import cu.edu.cujae.bd.visual.models.Model;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class TouristController implements Initializable{

    private ObservableList<TouristDto> tourists = FXCollections.observableArrayList();
    private TouristDto selectedTourist;

    @FXML
    private AnchorPane insertTouristPane;
    @FXML
    private Label touristFormLabel;
    @FXML
    private TableView<TouristDto> touristTable;
    @FXML
    private TableColumn<TouristDto, String> passportColumn;
    @FXML
    private TableColumn<TouristDto, String> nameColumn;
    @FXML
    private TableColumn<TouristDto, String> lastNameColumn;
    @FXML
    private TableColumn<TouristDto, Integer> ageColumn;
    @FXML
    private TableColumn<TouristDto, String> sexColumn;
    @FXML
    private TableColumn<TouristDto, String> contactNumberColumn;
    @FXML
    private TableColumn<TouristDto, String> countryColumn;
    @FXML
    private Button closeButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button updateButton;
    @FXML
    private Button saveButton;
    @FXML
    private Button backButton;
    @FXML
    private Button newTouristButton;
    @FXML
    private TextField passportField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField ageField;
    @FXML
    private TextField contactField;
    @FXML
    private ChoiceBox<Character>sexMenu;
    @FXML
    private ChoiceBox<CountryDto>countryMenu;



    public void close() {
        Stage stage = (Stage) touristTable.getScene().getWindow();
        Model.getInstanse().getViewFactory().closeStage(stage);
    }

    public void minimice(){
        Stage stage = (Stage) touristTable.getScene().getWindow();
        stage.setIconified(true);
    }
    
    /***********************  TABLAS Y CHOICEBOX   *****************************/

    public void configurarTablaCar() {
        passportColumn.setCellValueFactory(new PropertyValueFactory<>("Passport"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("LastName"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("Age"));
        contactNumberColumn.setCellValueFactory(new PropertyValueFactory<>("Contact"));

        countryColumn.setCellValueFactory(cellData -> {
            CountryDto country = cellData.getValue().getCountry();
            String nameCountry = country != null ? country.getName() : "";
            return new SimpleStringProperty(nameCountry);
        });
        sexColumn.setCellValueFactory(cellData -> {
            TouristDto touristDto = cellData.getValue();
            String sex = String.valueOf(touristDto.getSex());
            return new SimpleStringProperty(sex);
        });
        touristTable.setItems(tourists);
    }

    public void rellenarTablaTourist() throws SQLException {
        tourists.clear();
        ObservableList<TouristDto> touristList = ServicesLocator.getTouristServices().getAllTourist();
        tourists.setAll(touristList);
    }

/***********************           VISTAS          *****************************/
public void onTouristForm() {
    touristTable.setVisible(false);
    newTouristButton.setVisible(false);
    deleteButton.setVisible(false);
    updateButton.setVisible(false);
    insertTouristPane.setVisible(true);
    backButton.setVisible(true);
    saveButton.setVisible(true);
}

public void onBackButton() {
    touristTable.setVisible(true);
    newTouristButton.setVisible(true);
    deleteButton.setVisible(true);
    updateButton.setVisible(true);
    insertTouristPane.setVisible(false);
    backButton.setVisible(false);
    saveButton.setVisible(false);
    cleanFields();
}

public void cleanFields(){
    passportField.setText(null);
    nameField.setText(null);
    lastNameField.setText(null);
    ageField.setText(null);
    contactField.setText(null);
    sexMenu.setValue(null);
    countryMenu.setValue(null);
}

//se ejecuta al pulsar el boton new car
    public void openNew() {
        this.selectedTourist = new TouristDto();
        touristFormLabel.setText("New Tourist");
        onTouristForm();
    }

//Se ejecuta al dar clic en el boton update
	public void openForEdit() {
		this.selectedTourist = touristTable.getSelectionModel().getSelectedItem();
        touristFormLabel.setText("Update Tourist");
        if(selectedTourist != null){
            passportField.setText(selectedTourist.getPassport());
            nameField.setText(selectedTourist.getName());
            lastNameField.setText(selectedTourist.getLastName());
            ageField.setText(Integer.toString(selectedTourist.getAge()));
            contactField.setText(selectedTourist.getContact());
            onTouristForm();
        }else{
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("You must select a tourist");
            alert.showAndWait();
        }        
	}

/***********************  METODO INITIALIZE   *****************************/

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configurarTablaCar();
        try{
            rellenarTablaTourist();
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }

}
