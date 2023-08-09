package cu.edu.cujae.bd.visual.controller;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ResourceBundle;

import cu.edu.cujae.bd.dto.CarDto;
import cu.edu.cujae.bd.dto.DriverDto;
import cu.edu.cujae.bd.dto.ModelDto;
import cu.edu.cujae.bd.dto.PaymentDto;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class RentCarController implements Initializable{

    private ObservableList<TouristDto> touristList = FXCollections.observableArrayList();
    private ObservableList<PaymentDto> paymentList = FXCollections.observableArrayList();
    private ObservableList<DriverDto> driverList = FXCollections.observableArrayList();
    private ObservableList<CarDto> carsAvailable = FXCollections.observableArrayList();
    private CarDto selectedCar;
    private TouristDto selectedTourist;

    @FXML
    private ChoiceBox<TouristDto> touristMenu;
    @FXML
    private Label nameLbl;
    @FXML
    private Label rentLbl;
    @FXML
    private Label priceTotalLbl;
    @FXML
    private TableView<CarDto> AvailableCarTable;
    @FXML
    private TableColumn<CarDto, String> brandCol;
    @FXML
    private TableColumn<CarDto, String> modelCol;
    @FXML
    private TableColumn<CarDto, String> priceCol;
    @FXML
    private TableView<TouristDto> touristTable;
    @FXML
    private TableColumn<TouristDto, String> nameCol;
    @FXML
    private TableColumn<TouristDto, String> lastNameCol;
    @FXML
    private TableColumn<TouristDto, String> passportCol;
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

        AvailableCarTable.setOnMouseClicked(event ->{
            this.selectedCar = AvailableCarTable.getSelectionModel().getSelectedItem();
            if(this.selectedCar != null){
                rentLbl.setText(selectedCar.getBrand() + " " + selectedCar.getModel().getNameModel());     
            }else{
               rentLbl.setText("");
            }
        });

    }

    public void configurarTablaTourist() {

        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        passportCol.setCellValueFactory(new PropertyValueFactory<>("passport"));

        touristTable.setItems(touristList);

        touristTable.setOnMouseClicked(event ->{
            this.selectedTourist = touristTable.getSelectionModel().getSelectedItem();
            if(this.selectedTourist != null){
                nameLbl.setText(this.selectedTourist.getName() + " " + this.selectedTourist.getLastName());
            }else{
                nameLbl.setText("");
            }
        });


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

    public void rellenarTablaTourist(){
        touristList.clear();
        ObservableList<TouristDto> list = FXCollections.observableArrayList();
        try {
            list = ServicesLocator.getTouristServices().getAllTourist();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        touristList.setAll(list);
    }

    /*public void configurarChoiceBoxTourist() {
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

    }*/

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

        if(this.selectedCar!=null && this.selectedTourist!=null){
            paymentMenu.setDisable(false);
            driverMenu.setDisable(false);
            priceExtField.setDisable(false);
            startDate.setDisable(false);
            finalDate.setDisable(false);
            touristTable.setDisable(true);
            AvailableCarTable.setDisable(true);

        }else{
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("You must select a car and a tourist");
            alert.showAndWait();
        }
    }

    public void onCalcular(){
        if(this.selectedCar==null || paymentMenu.getValue() == null || (priceExtField.getText().equals("")) || startDate.getValue()== null || finalDate.getValue()== null || this.selectedTourist == null){
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("todos los campos deben estar llenos");
                alert.showAndWait();
        }else if(startDate.getValue().isAfter(finalDate.getValue())){
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("The start date cannot be more recent than the end date.");
                alert.showAndWait();
        }else if(startDate.getValue().isBefore(LocalDate.now())){
               Alert alert = new Alert(AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("La fecha de inicio de contrato no puede ser de antes de la fecha de hoy");
                alert.showAndWait(); 
        }else{
            int daysDifference = Long.valueOf(ChronoUnit.DAYS.between(startDate.getValue(), finalDate.getValue())).intValue();
            int price = daysDifference * this.selectedCar.getPrice();
            priceTotalLbl.setText("$" + price);
            rentBtn.setDisable(false);
        }
    }

    public void onClear(){
        this.selectedCar = null;
        this.selectedTourist = null;
        startDate.setValue(null);
        finalDate.setValue(null);
        driverMenu.setValue(null);
        paymentMenu.setValue(null);
        priceExtField.setText("");

        paymentMenu.setDisable(true);
        driverMenu.setDisable(true);
        priceExtField.setDisable(true);
        startDate.setDisable(true);
        finalDate.setDisable(true);
        touristTable.setDisable(false);
        AvailableCarTable.setDisable(false);
        rentBtn.setDisable(true);
        priceTotalLbl.setText("$0.0");
        nameLbl.setText(null);
        rentLbl.setText(null);

    }

    public void close() {
        Stage stage = (Stage) rentBtn.getScene().getWindow();
        Model.getInstanse().getViewFactory().closeStage(stage);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //configurarChoiceBoxTourist();
        configurarChoiceBoxDriver();
        configurarChoiceBoxPayment();
        configurarTablaCar();
        configurarTablaTourist();
        //rellenarChoiceBoxTourist();
        rellenarTablaTourist();
        rellenarChoiceBoxDriver();
        rellenarChoiceBoxPayment();
        rellenarTablaCar();
    }
}
