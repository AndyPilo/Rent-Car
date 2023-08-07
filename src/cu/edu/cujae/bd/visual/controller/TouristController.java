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
import javafx.util.StringConverter;

public class TouristController implements Initializable{

    private ObservableList<TouristDto> tourists = FXCollections.observableArrayList();
    private ObservableList<CountryDto> listcountry = FXCollections.observableArrayList();
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
    private ChoiceBox<String>sexMenu;
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

    public void configurarChoiceBoxCountry() {
        countryMenu.setConverter(new StringConverter<CountryDto>() {
            @Override
            public String toString(CountryDto country) {
                return country.getName(); // Devuelve el nombre del modelo como cadena
            }

            @Override
            public CountryDto fromString(String string) {
                return null; // No es necesario implementar este método para ChoiceBox
            }
        });
    }
    public void rellenarChoiceBoxModel() {
        try {
            listcountry = ServicesLocator.getCountryServices().getAllCountry();
            countryMenu.setItems(listcountry);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void configurarChoiceBoxSex() {
        sexMenu.getItems().addAll("M", "F");
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
    passportField.setText("");
    nameField.setText("");
    lastNameField.setText("");
    ageField.setText("");
    contactField.setText("");
    sexMenu.setValue(null);
    countryMenu.setValue(null);
}

public boolean validarCamposLLenos(){
    boolean camposLLenos = true;
     if ((passportField.getText().equals("")) || (nameField.getText().equals(""))
            || (lastNameField.getText().equals("")) || (ageField.getText().equals("")) 
            || (contactField.getText().equals("")) || (sexMenu.getValue() == null) 
            || (countryMenu.getValue() == null)) {

            Alert alert2 = new Alert(AlertType.ERROR);
            alert2.setTitle("Error");
            alert2.setHeaderText(null);
            alert2.setContentText("You must fill in all fields");
            alert2.showAndWait();
            camposLLenos = false;
        }
        return camposLLenos;
}


 /***********************            BOTONES           *****************************/   

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

    //Se ejecuta al dar clic en el boton delete
    public void onDeleteButton() {
        TouristDto selectedTouristDto = touristTable.getSelectionModel().getSelectedItem();
        if (selectedTouristDto != null) {
            try {
                ServicesLocator.getTouristServices().deleteTourist(selectedTouristDto.getCodTourist());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            tourists.remove(selectedTouristDto);
            touristTable.refresh();
        }else{
            Alert alert = new Alert(AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("You must select a tourist");
                alert.showAndWait();
        }
    }

    //Se ejecuta al dar clic en el boton save
    public void onSaveButton() throws SQLException{
        boolean camposLLenos = validarCamposLLenos();

        if(camposLLenos){
            if(selectedTourist.getPassport() == null){
                    TouristDto touristDto = new TouristDto(passportField.getText() 
                                        , nameField.getText()
                                        , lastNameField.getText()  
                                        , Integer.parseInt(ageField.getText())
                                        , sexMenu.getValue().charAt(0)
                                        , contactField.getText()
                                        , countryMenu.getValue());
                    
                    ServicesLocator.getTouristServices().insertTourist(touristDto);
                    

                    // Mostrar mensaje de exito
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Éxito");
                    alert.setHeaderText(null);
                    alert.setContentText("The new tourist has been inserted successfully.");
                    alert.showAndWait();

                    //Actualizar en la tabla
                    rellenarTablaTourist();
                
                    onBackButton();           
            }else{
                    TouristDto  touristUpdate = new TouristDto(selectedTourist.getCodTourist()
                                        , passportField.getText() 
                                        , nameField.getText()
                                        , lastNameField.getText()  
                                        , Integer.parseInt(ageField.getText())
                                        , sexMenu.getValue().charAt(0)
                                        , contactField.getText()
                                        , countryMenu.getValue());
                
                    ServicesLocator.getTouristServices().updateTourist(touristUpdate);

                    // Mostrar mensaje de exito
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Éxito");
                    alert.setHeaderText(null);
                    alert.setContentText("The new tourist has been updated successfully.");
                    alert.showAndWait();
                        
                    //Actualizar en la tabla
                    rellenarTablaTourist();
                        
                    onBackButton();
            }
        }
    }

/***********************  METODO INITIALIZE   *****************************/

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configurarTablaCar();
        configurarChoiceBoxCountry();
        configurarChoiceBoxSex();
        try{
            rellenarTablaTourist();
            rellenarChoiceBoxModel();
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }

}
