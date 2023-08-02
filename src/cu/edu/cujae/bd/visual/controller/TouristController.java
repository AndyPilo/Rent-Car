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
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.Bloom;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class TouristController implements Initializable{

    private ObservableList<TouristDto> tourists = FXCollections.observableArrayList();
    private TouristDto selectedTourist;

    @FXML
    private AnchorPane insertTouristPane;
    @FXML
    private TextField plateField;
    @FXML
    private TextField colorField;
    @FXML
    private TextField colorField1;
    @FXML
    private TextField kmField;
    @FXML
    private ChoiceBox<?> situationMenu;
    @FXML
    private TextField colorField11;
    @FXML
    private ChoiceBox<?> modelMenu;
    @FXML
    private Button refreshButton;
    @FXML
    private Button backButton;
    @FXML
    private Button newTouristButton;
    @FXML
    private Button insertButton;
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

    public void close() {
        Stage stage = (Stage) touristTable.getScene().getWindow();
        Model.getInstanse().getViewFactory().closeStage(stage);
    }
    public void moved(){
        Bloom bloom =new Bloom();
        bloom.setThreshold(0.60);
        closeButton.setEffect(bloom);
    }

    public void exited(){
        closeButton.setEffect(null);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configurarTablaCar();
        try{
            rellenarTablaTourist();
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
    /***********************  Nueva logica   *****************************/
  /* 
    //se ejecuta al pulsar el boton new tourist
    public void openNew() {
        this.selectedTourist = new TouristDto();
    }

    //Se ejecuta al dar clic en el boton update
	public void openForEdit() {
		this.selectedTourist = touristTable.getSelectionModel().getSelectedItem();
	}

    public void saveTourist() throws SQLException{
        if (this.selectedTourist.getCodTourist() == null) {
            
            ServicesLocator.getTouristServices().insertTourist(this.selectedTourist);
        }
        else {
			ServicesLocator.getTouristServices().updateTourist(this.selectedTourist);
        }
		//Actualizar en la tabla
        rellenarTablaTourist();    
    }
*/



}
