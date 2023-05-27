package cu.edu.cujae.bd.visual.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import cu.edu.cujae.bd.dto.CountryDto;
import cu.edu.cujae.bd.dto.TouristDto;
import cu.edu.cujae.bd.service.ServicesLocator;
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
import javafx.scene.layout.AnchorPane;

public class TouristController implements Initializable{

    public ObservableList<TouristDto> tourists = FXCollections.observableArrayList();

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
    private TableColumn<TouristDto, Integer> contactNumberColumn;
    @FXML
    private TableColumn<TouristDto, String> countryColumn;

    public void configurarTablaCar() {
        passportColumn.setCellValueFactory(new PropertyValueFactory<>("Passport"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("Last Name"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("Age"));
        contactNumberColumn.setCellValueFactory(new PropertyValueFactory<>("Contact Number"));
        countryColumn.setCellValueFactory(cellData -> {
            CountryDto country = cellData.getValue().getCountry();
            String nameCountry = country != null ? country.getName() : "";
            return new SimpleStringProperty(nameCountry);
        });
        sexColumn.setCellValueFactory(cellData -> {
            CountryDto country = cellData.getValue().getCountry();
            String sex = country != null ? String.valueOf(country.getName()):"";
            return new SimpleStringProperty(sex);
        });
        touristTable.setItems(tourists);
    }

    public void rellenarTablaCar() throws SQLException {
        tourists.clear();
        ObservableList<TouristDto> touristList = ServicesLocator.getTouristServices().getAllTourist();
        tourists.setAll(touristList);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configurarTablaCar();
        try{
            rellenarTablaCar();
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }

}
