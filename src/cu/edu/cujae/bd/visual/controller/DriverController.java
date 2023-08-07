package cu.edu.cujae.bd.visual.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import cu.edu.cujae.bd.dto.DriverCategoryDto;
import cu.edu.cujae.bd.dto.DriverDto;
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

public class DriverController implements Initializable{
    private ObservableList<DriverDto> drivers = FXCollections.observableArrayList();
    private ObservableList<DriverCategoryDto> listCategorys = FXCollections.observableArrayList();
    private DriverDto selectedDriver;
    
    @FXML
    private AnchorPane insertDriverPane;
    @FXML
    private Label driverFormLabel;
    @FXML
    private ChoiceBox<DriverCategoryDto> driverMenu;
    @FXML
    private TableView<DriverDto> driverTable;
    @FXML
    private TableColumn<DriverDto, String> dniColumn;
    @FXML
    private TableColumn<DriverDto, String> nameColumn;
    @FXML
    private TableColumn<DriverDto, String> lastNameColumn;
    @FXML
    private TableColumn<DriverDto, String> addressColumn;
    @FXML
    private TableColumn<DriverDto, String> categoryColumn;
    @FXML
    private TextField dniField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField addressField;
    @FXML
    private Button minimiceButton;
    @FXML
    private Button newDriverButton;
    @FXML
    private Button saveButton;
    @FXML
    private Button updateButton;
    @FXML
    private Button closeButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button backButton;

/****************************    TABLAS Y CHOICEBOX     ****************************/

 public void configurarTablaDriver() {
        dniColumn.setCellValueFactory(new PropertyValueFactory<>("dni"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("nameDriver"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("Address"));
        categoryColumn.setCellValueFactory(cellData -> {
            DriverCategoryDto category = cellData.getValue().getCategory();
            String nameCategory = category != null ? category.getCategory() : "";
            return new SimpleStringProperty(nameCategory);
        });
        
        driverTable.setItems(drivers);

    }

    public void rellenarTablaDriver() throws SQLException {
        drivers.clear();
        ObservableList<DriverDto> driversList = ServicesLocator.getDriverServices().getAllDrivers();
        drivers.setAll(driversList);
    }

    public void configurarChoiceBoxCategory() {
        driverMenu.setConverter(new StringConverter<DriverCategoryDto>() {
            @Override
            public String toString(DriverCategoryDto category) {
                return category.getCategory(); // Devuelve el nombre de la categoria como cadena
            }

            @Override
            public DriverCategoryDto fromString(String string) {
                return null; // No es necesario implementar este método para ChoiceBox
            }
        });

    }

    public void rellenarChoiceBoxCategory() {
        try {
            listCategorys = ServicesLocator.getDriverCategoryServices().getAllCategorys();
            driverMenu.setItems(listCategorys);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
/****************************    BOTONES     ****************************/

    //se ejecuta al pulsar el boton new car
    public void openNew() {
        this.selectedDriver = new DriverDto();
        driverFormLabel.setText("New Driver");
        onDriverForm();
    }

    //Se ejecuta al dar clic en el boton update
	public void openForEdit() {
		this.selectedDriver = driverTable.getSelectionModel().getSelectedItem();
        driverFormLabel.setText("Update Driver");
        if(this.selectedDriver != null){
            dniField.setText(selectedDriver.getDni());
            nameField.setText(selectedDriver.getNameDriver());
            lastNameField.setText(selectedDriver.getLastName());
            addressField.setText(selectedDriver.getAddress());
            onDriverForm();
        }else{
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("You must select a driver");
            alert.showAndWait();
        }        
	}

    //Se ejecuta al dar clic en el boton delete
    public void onDeleteButton() {
        DriverDto selectedDriverDto = driverTable.getSelectionModel().getSelectedItem();
        if (selectedDriverDto != null) {
            try {
                ServicesLocator.getDriverServices().deleteDriver(selectedDriverDto.getCodDriver());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            drivers.remove(selectedDriverDto);
            driverTable.refresh();
        }else{
            Alert alert = new Alert(AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("You must select a driver");
                alert.showAndWait();
        }
    }

    //Se ejecuta al dar clic en el boton save
    public void onSaveButton() throws SQLException{
        boolean camposLLenos = validarCamposLLenos();

        if(camposLLenos){
            if(selectedDriver.getCodDriver() == 0){
                    DriverDto driverDto = new DriverDto(dniField.getText(), 
                                        nameField.getText(), 
                                        lastNameField.getText(),  
                                        addressField.getText(), 
                                        driverMenu.getValue());
                    
                    ServicesLocator.getDriverServices().insertDriver(driverDto);
                    

                    // Mostrar mensaje de exito
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Éxito");
                    alert.setHeaderText(null);
                    alert.setContentText("The new driver has been inserted successfully.");
                    alert.showAndWait();

                    //Actualizar en la tabla
                    rellenarTablaDriver();
                
                    onBackButton();           
            }else{
                    DriverDto  driverUpdate = new DriverDto(selectedDriver.getCodDriver()
                            , dniField.getText()
                            , nameField.getText() 
                            , lastNameField.getText()
                            , addressField.getText()
                            , driverMenu.getValue());
                
                    ServicesLocator.getDriverServices().updateDriver(driverUpdate);;

                    // Mostrar mensaje de exito
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Éxito");
                    alert.setHeaderText(null);
                    alert.setContentText("The driver has been updated successfully.");
                    alert.showAndWait();
                        
                    //Actualizar en la tabla
                    rellenarTablaDriver();
                        
                    onBackButton();
            }
        }
    }
/***********************           VISTAS          *****************************/
    public void onBackButton() {
        driverTable.setVisible(true);
        newDriverButton.setVisible(true);
        deleteButton.setVisible(true);
        updateButton.setVisible(true);
        insertDriverPane.setVisible(false);
        backButton.setVisible(false);
        saveButton.setVisible(false);
        cleanFields();
    }

    public void onDriverForm() {
        driverTable.setVisible(false);
        newDriverButton.setVisible(false);
        deleteButton.setVisible(false);
        updateButton.setVisible(false);
        insertDriverPane.setVisible(true);
        backButton.setVisible(true);
        saveButton.setVisible(true);
    }

    public void cleanFields(){
        dniField.setText("");
        nameField.setText("");
        lastNameField.setText("");
        addressField.setText("");
        driverMenu.setValue(null);
    }

    public boolean validarCamposLLenos(){
        boolean camposLLenos = true;
         if ((dniField.getText().equals("")) || (nameField.getText().equals(""))
                || (lastNameField.getText().equals("")) || (addressField.getText().equals(""))
                || (driverMenu.getValue() == null)) {

                Alert alert2 = new Alert(AlertType.ERROR);
                alert2.setTitle("Error");
                alert2.setHeaderText(null);
                alert2.setContentText("You must fill in all fields");
                alert2.showAndWait();
                camposLLenos = false;
            }
            return camposLLenos;
    }

     public void close() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        Model.getInstanse().getViewFactory().closeStage(stage);
     }
     public void minimice(){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.setIconified(true);
     }

/***********************  METODO INITIALIZE   *****************************/
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configurarTablaDriver();
        configurarChoiceBoxCategory();
        try {
            rellenarTablaDriver();
            rellenarChoiceBoxCategory();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
