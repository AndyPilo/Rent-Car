package cu.edu.cujae.bd.visual.controller;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

import cu.edu.cujae.bd.dto.CarDto;
import cu.edu.cujae.bd.dto.DriverDto;
import cu.edu.cujae.bd.dto.ModelDto;
import cu.edu.cujae.bd.dto.PaymentDto;
import cu.edu.cujae.bd.dto.TouristDto;
import cu.edu.cujae.bd.service.ServicesLocator;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;

public class RentCarController implements Initializable{

    private ObservableList<TouristDto> touristList = FXCollections.observableArrayList();
    private ObservableList<PaymentDto> paymentList = FXCollections.observableArrayList();
    private ObservableList<DriverDto> driverList = FXCollections.observableArrayList();
    private ObservableList<CarDto> carsAvailable = FXCollections.observableArrayList();
    private CarDto selectedCar;

    @FXML
    private ChoiceBox<TouristDto> touristMenu;
    @FXML
    private Label LastNameLbl;
    @FXML
    private Label nameLbl;
    @FXML
    private TableView<CarDto> AvailableCarTable;
    @FXML
    private TableColumn<CarDto, String> brandCol;
    @FXML
    private TableColumn<CarDto, String> modelCol;
    @FXML
    private TableColumn<CarDto, String> priceCol;
    @FXML
    private Button rentBtn;
    @FXML
    private ChoiceBox<DriverDto> driverMenu;
    @FXML
    private ChoiceBox<PaymentDto> paymentMenu;
    @FXML
    private DatePicker finalDate;
    @FXML
    private TextField priceExtField;
    @FXML
    private DatePicker startDate;

     
    public void configurarTablaCar() {

        priceCol.setCellValueFactory(new PropertyValueFactory<>("Price"));
        brandCol.setCellValueFactory(new PropertyValueFactory<>("brand"));
        modelCol.setCellValueFactory(cellData -> {
            ModelDto model = cellData.getValue().getModel();
            String nameModel = model != null ? model.getNameModel() : "";
            return new SimpleStringProperty(nameModel);
        });

        AvailableCarTable.setItems(carsAvailable);

    }

    public void rellenarTablaCar(){
        carsAvailable.clear();
        ObservableList<CarDto> carsList = FXCollections.observableArrayList();
        try {
            carsList = ServicesLocator.getCarServices().getAllCarsAvailable();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        carsAvailable.setAll(carsList);
    }

    public void configurarChoiceBoxTourist() {
        touristMenu.setConverter(new StringConverter<TouristDto>() {
            @Override
            public String toString(TouristDto tourist) {
                return tourist.getPassport(); // Devuelve el nombre del modelo como cadena
            }

            @Override
            public TouristDto fromString(String string) {
                return null; // No es necesario implementar este método para ChoiceBox
            }
        });

        touristMenu.setOnAction(event ->{
            TouristDto touristDto = new TouristDto();

            if(touristMenu.getValue()!=null){
                touristDto = touristMenu.getValue();
                nameLbl.setText(touristDto.getName());
                LastNameLbl.setText(touristDto.getLastName());
            }else{
                nameLbl.setText("");
                LastNameLbl.setText("");
            }
        });

    }

    public void configurarChoiceBoxPayment() {
        paymentMenu.setConverter(new StringConverter<PaymentDto>() {
            @Override
            public String toString(PaymentDto payment) {
                return payment.getPayment(); // Devuelve el nombre del modelo como cadena
            }

            @Override
            public PaymentDto fromString(String string) {
                return null; // No es necesario implementar este método para ChoiceBox
            }
        });
    }

    public void configurarChoiceBoxDriver() {
        driverMenu.setConverter(new StringConverter<DriverDto>() {
            @Override
            public String toString(DriverDto driver) {
                return driver.getNameDriver(); // Devuelve el nombre del modelo como cadena
            }

            @Override
            public DriverDto fromString(String string) {
                return null; // No es necesario implementar este método para ChoiceBox
            }
        });
    }

    public void rellenarChoiceBoxTourist() {
        try {
            touristList = ServicesLocator.getTouristServices().getAllTourist();
            touristMenu.setItems(touristList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void rellenarChoiceBoxPayment() {
        try {
            paymentList = ServicesLocator.getPaymentServices().getAllPayment();
            paymentMenu.setItems(paymentList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void rellenarChoiceBoxDriver() {
        try {
            driverList = ServicesLocator.getDriverServices().getAllDrivers();
            driverMenu.setItems(driverList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void onSelected(){
        
        this.selectedCar = AvailableCarTable.getSelectionModel().getSelectedItem();

        if(this.selectedCar!=null && touristMenu.getValue()!= null){
            paymentMenu.setDisable(false);
            driverMenu.setDisable(false);
            priceExtField.setDisable(false);
            startDate.setDisable(false);
            finalDate.setDisable(false);
        }else{
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("You must select a car and a tourist");
            alert.showAndWait();
        }
    }

    public void onCalcular(){
        if(touristMenu.getValue()==null && this.selectedCar==null && paymentMenu.getValue()==null && driverMenu.getValue()== null
                                && (priceExtField.getText().equals("")) 
                                && startDate.getValue()== null 
                                && finalDate.getValue()== null){
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("You must select a car and a tourist");
                alert.showAndWait();

            }else if(startDate.getValue().isAfter(finalDate.getValue())){
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("You must select a car and a tourist");
                alert.showAndWait();
            }else{
                int dayStart = startDate.getValue().getDayOfMonth();
                int dayFinal = startDate.getValue().getDayOfMonth();
               
            }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configurarChoiceBoxTourist();
        configurarChoiceBoxDriver();
        configurarChoiceBoxPayment();
        configurarTablaCar();
        rellenarChoiceBoxTourist();
        rellenarChoiceBoxDriver();
        rellenarChoiceBoxPayment();
        rellenarTablaCar();
    }
}
