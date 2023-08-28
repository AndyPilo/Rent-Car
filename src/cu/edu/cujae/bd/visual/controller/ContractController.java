package cu.edu.cujae.bd.visual.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

import cu.edu.cujae.bd.dto.CarDto;
import cu.edu.cujae.bd.dto.ContractDto;
import cu.edu.cujae.bd.dto.DateDto;
import cu.edu.cujae.bd.dto.DriverDto;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
        colSBill.setCellValueFactory(new PropertyValueFactory<>("billSpecial"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("priceTotal"));
        colExtension.setCellValueFactory(new PropertyValueFactory<>("extension"));
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
            String nameDriver = driver != null ? driver.getNameDriver() : "No Driver";
            return new SimpleStringProperty(nameDriver);
        }); 
        colPayment.setCellValueFactory(cellData -> {
            PaymentDto payment = cellData.getValue().getPaymentType();
            String typePayment = payment != null ? payment.getPayment() : "";
            return new SimpleStringProperty(typePayment);
        }); 
        colSDate.setCellValueFactory(cellData -> {
            DateDto sdDto = cellData.getValue().getDate();
            String startDate = sdDto != null ? sdDto.getStarDate().toString() : "";
            return new SimpleStringProperty(startDate);
        });
        colFDate.setCellValueFactory(cellData -> {
            DateDto fdDto = cellData.getValue().getDate();
            String finalDate = fdDto != null ? fdDto.getFinalDate().toString() : "";
            return new SimpleStringProperty(finalDate);
        });
         
        
        contractTable.setItems(contracts);
    }

    public void rellenarTablaContract(){
        contracts.clear();
        ObservableList<ContractDto> contractList = FXCollections.observableArrayList();
        try {
            contractList = ServicesLocator.getContractServices().getAllContract();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        contracts.setAll(contractList);
    }


    public void insertRent() throws IOException, SQLException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../fxml/rentCar.fxml"));
        loader.load();

        RentCarController controller = loader.getController();
        controller.initAtributes();

        Parent parent = loader.getRoot();
        Stage stage = new Stage(StageStyle.UNDECORATED);
        stage.setScene(new Scene(parent));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();

        ContractDto contract = controller.getNewContract();
        if(contract!=null){
        ServicesLocator.getContractServices().insertContract(contract);; 
        rellenarTablaContract();
        }  

    }

    public void deleteContract(){
        ContractDto selectedContract = contractTable.getSelectionModel().getSelectedItem();
            if (selectedContract != null) {

                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setHeaderText(null);
                alert.setTitle("Confirmation Message");
                alert.setContentText("Are you sure you want to delete ?");
                Optional<ButtonType> option = alert.showAndWait();

                if(option.get().equals(ButtonType.OK)){
                    try {
                    ServicesLocator.getContractServices().deleteContract(selectedContract.getCodContract());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    contracts.remove(selectedContract);
                    contractTable.refresh();

                    CarDto updateCar = selectedContract.getCar();
                    updateCar.setSituation(new SituationDto(1,"disponible"));
                    try {
                        ServicesLocator.getCarServices().updateCar(updateCar);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    Alert alert2 = new Alert(AlertType.INFORMATION);
                    alert2.setHeaderText(null);
                    alert2.setContentText("Successfuly");
                    alert2.showAndWait();
                }
            }else{
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("You must select a contract");
                alert.showAndWait();
            }
    }

    public void close() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        Model.getInstanse().getViewFactory().closeStage(stage);
    }

    public void minimice(){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.setIconified(true);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configurarTablaContract();
        rellenarTablaContract();
    }
}
