package cu.edu.cujae.bd.visual.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import cu.edu.cujae.bd.dto.CarDto;
import cu.edu.cujae.bd.dto.ContractDto;
import cu.edu.cujae.bd.dto.DriverDto;
import cu.edu.cujae.bd.dto.ModelDto;
import cu.edu.cujae.bd.dto.PaymentDto;
import cu.edu.cujae.bd.dto.SituationDto;
import cu.edu.cujae.bd.dto.TouristDto;
import cu.edu.cujae.bd.service.ServicesLocator;
import cu.edu.cujae.bd.visual.models.Model;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ContractController implements Initializable{

    private ObservableList<ContractDto> contracts = FXCollections.observableArrayList();

    @FXML
    private Button closeButton;
    @FXML
    private TableView<ContractDto> contractTable;
    @FXML
    private TableColumn<ContractDto, String> colCar;
    @FXML
    private TableColumn<ContractDto, String> colDriver;
    @FXML
    private TableColumn<ContractDto, String> colExtension;
    @FXML
    private TableColumn<ContractDto, String> colFDate;
    @FXML
    private TableColumn<ContractDto, String> colPayment;
    @FXML
    private TableColumn<ContractDto, String> colPrice;
    @FXML
    private TableColumn<ContractDto, String> colSBill;
    @FXML
    private TableColumn<ContractDto, String> colSDate;
    @FXML
    private TableColumn<ContractDto, String> colTourist;


    public void configurarTablaContract() {
        System.out.println("hola 1");
        colSBill.setCellValueFactory(new PropertyValueFactory<>("billSpecial"));
        colSDate.setCellValueFactory(new PropertyValueFactory<>("codContract"));
        colFDate.setCellValueFactory(new PropertyValueFactory<>("codContract"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("codContract"));
        colExtension.setCellValueFactory(new PropertyValueFactory<>("codContract"));
        colTourist.setCellValueFactory(cellData -> {
            TouristDto tourist = cellData.getValue().getTourist();
            String nameTourist = tourist != null ? tourist.getName() : "";
            return new SimpleStringProperty(nameTourist);
        });   
        colCar.setCellValueFactory(cellData -> {
            CarDto car = cellData.getValue().getCar();
            String carPlate = car != null ? car.getPlate() : "";
            return new SimpleStringProperty(carPlate);
        }); 
        colDriver.setCellValueFactory(cellData -> {
            DriverDto driver = cellData.getValue().getDriver();
            String nameDriver = driver != null ? driver.getNameDriver() : "";
            return new SimpleStringProperty(nameDriver);
        }); 
        colPayment.setCellValueFactory(cellData -> {
            PaymentDto payment = cellData.getValue().getPaymentType();
            String typePayment = payment != null ? payment.getPayment() : "";
            return new SimpleStringProperty(typePayment);
        }); 
        
        contractTable.setItems(contracts);
        System.out.println("hola 2");
    }

    public void rellenarTablaContract(){
        System.out.println("hola 3");
        contracts.clear();
        ObservableList<ContractDto> contractList = FXCollections.observableArrayList();
        try {
            contractList = ServicesLocator.getContractServices().getAllContract();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        contracts.setAll(contractList);
        System.out.println("hola 4");
    }


    public void showRentCarWindow() throws IOException{
        FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../fxml/rentCar.fxml"));
            loader.load();
            //AddModelController controller = loader.getController();
            //controller.initAtributes();

            Parent parent = loader.getRoot();
            Stage stage = new Stage(StageStyle.UTILITY);
            stage.setScene(new Scene(parent));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
    }

    public void updateContract(){

    }

    public void insertContract(){

    }

    public void deleteContract(){

    }

    public void close() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        Model.getInstanse().getViewFactory().closeStage(stage);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configurarTablaContract();
        rellenarTablaContract();
    }
}
