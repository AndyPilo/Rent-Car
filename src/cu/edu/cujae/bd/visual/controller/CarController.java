package cu.edu.cujae.bd.visual.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import cu.edu.cujae.bd.dto.CarDto;
import cu.edu.cujae.bd.dto.ModelDto;
import cu.edu.cujae.bd.dto.SituationDto;
import cu.edu.cujae.bd.service.ServicesLocator;
import cu.edu.cujae.bd.visual.models.Model;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class CarController implements Initializable {

    public ObservableList<CarDto> cars = FXCollections.observableArrayList();
    private ObservableList<ModelDto> listModels = FXCollections.observableArrayList();
    private ObservableList<SituationDto> listSituation = FXCollections.observableArrayList();
    private CarDto selectedCar;

    @FXML
    private TableView<CarDto> carsTable;
    @FXML
    private TableColumn<CarDto, String> plateColumn;
    @FXML
    private TableColumn<CarDto, String> situationColumn;
    @FXML
    private TableColumn<CarDto, String> colorColumn;
    @FXML
    private TableColumn<CarDto, String> kmColumn;
    @FXML
    private TableColumn<CarDto, String> modelColumn;
    @FXML
    private Button newCarButton;
    @FXML
    private AnchorPane insertCarPane;
    @FXML
    private TextField colorField;
    @FXML
    private TextField plateField;
    @FXML
    private ChoiceBox<ModelDto> modelMenu;
    @FXML
    private TextField kmField;
    @FXML
    private ChoiceBox<SituationDto> situationMenu;
    @FXML
    private Button saveButton;
    @FXML
    private Button backButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button updateButton;
    @FXML
    private Button closeButton;
    @FXML
    private Label carFormLabel;

/****************************    TABLAS Y CHOICEBOX     ****************************/

    public void configurarTablaCar() {
        plateColumn.setCellValueFactory(new PropertyValueFactory<>("Plate"));
        colorColumn.setCellValueFactory(new PropertyValueFactory<>("Color"));
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
        kmColumn.setCellValueFactory(cellData -> {
            CarDto car = cellData.getValue();
            String km = car != null ? String.valueOf(car.getKm()) : "";
            return new SimpleStringProperty(km);
        });
        carsTable.setItems(cars);

    }

    public void rellenarTablaCar() throws SQLException {
        cars.clear();
        ObservableList<CarDto> carsList = ServicesLocator.getCarServices().getAllCars();
        cars.setAll(carsList);
    }

    public void configurarChoiceBoxModel() {
        modelMenu.setConverter(new StringConverter<ModelDto>() {
            @Override
            public String toString(ModelDto model) {
                return model.getNameModel(); // Devuelve el nombre del modelo como cadena
            }

            @Override
            public ModelDto fromString(String string) {
                return null; // No es necesario implementar este método para ChoiceBox
            }
        });

    }

    public void rellenarChoiceBoxModel() {
        try {
            listModels = ServicesLocator.getModelServices().getAllModels();
            modelMenu.setItems(listModels);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void configurarChoiceBoxSituation() {
        situationMenu.setConverter(new StringConverter<SituationDto>() {
            @Override
            public String toString(SituationDto situationDto) {
                return situationDto.getSituation(); // Devuelve el nombre del modelo como cadena
            }

            @Override
            public SituationDto fromString(String string) {
                return null; // No es necesario implementar este método para ChoiceBox
            }
        });

    }

    public void rellenarChoiceBoxSituation() {
        try {
            listSituation = ServicesLocator.getSituationServices().getAllSituation();
            situationMenu.setItems(listSituation);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

/****************************    BOTONES     ****************************/

    //se ejecuta al pulsar el boton new car
    public void openNew() {
        this.selectedCar = new CarDto();
        carFormLabel.setText("New Car");
        onCarForm();
    }

    //Se ejecuta al dar clic en el boton update
	public void openForEdit() {
		this.selectedCar = carsTable.getSelectionModel().getSelectedItem();
        carFormLabel.setText("Update Car");
        if(selectedCar != null){
            plateField.setText(selectedCar.getPlate());
            colorField.setText(selectedCar.getColor());
            kmField.setText(String.valueOf(selectedCar.getKm()));
            onCarForm();
        }else{
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("You must select a car");
            alert.showAndWait();
        }        
	}

    //Se ejecuta al dar clic en el boton delete
    public void onDeleteButton() {
        CarDto selectedCarDto = carsTable.getSelectionModel().getSelectedItem();
            if (selectedCarDto != null) {
                try {
                    ServicesLocator.getCarServices().deleteCar(selectedCarDto.getCodCar());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                cars.remove(selectedCarDto);
                carsTable.refresh();
            }else{
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("You must select a car");
                alert.showAndWait();
            }
    }

    //Se ejecuta al dar clic en el boton save
    public void onSaveButton() throws SQLException{
        boolean camposLLenos = validarCamposLLenos();

        if(camposLLenos){
            if(selectedCar.getCodCar() == 0){
                    CarDto carDto = new CarDto(plateField.getText(), 
                                        colorField.getText(), 
                                        Integer.parseInt(kmField.getText()),  
                                        modelMenu.getValue(), 
                                        situationMenu.getValue());
                    
                    ServicesLocator.getCarServices().insertCar(carDto);
                    

                    // Mostrar mensaje de exito
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Éxito");
                    alert.setHeaderText(null);
                    alert.setContentText("The new car has been inserted successfully.");
                    alert.showAndWait();

                    //Actualizar en la tabla
                    rellenarTablaCar();
                
                    onBackButton();           
            }else{
                    CarDto  carUpdate = new CarDto(selectedCar.getCodCar()
                            , plateField.getText()
                            , colorField.getText() 
                            , Integer.parseInt(kmField.getText())
                            , modelMenu.getValue()
                            , situationMenu.getValue());
                
                    ServicesLocator.getCarServices().updateCar(carUpdate);

                    // Mostrar mensaje de exito
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Éxito");
                    alert.setHeaderText(null);
                    alert.setContentText("The car has been updated successfully.");
                    alert.showAndWait();
                        
                    //Actualizar en la tabla
                    rellenarTablaCar();
                        
                    onBackButton();
            }
        }
    }
    
/***********************           VISTAS          *****************************/
    public void onBackButton() {
        System.out.println("Aqui en vista");
        carsTable.setVisible(true);
        newCarButton.setVisible(true);
        deleteButton.setVisible(true);
        updateButton.setVisible(true);
        insertCarPane.setVisible(false);
        backButton.setVisible(false);
        saveButton.setVisible(false);
        System.out.println("Saliendo de vista");
        cleanFields();
    }

    public void onCarForm() {
        carsTable.setVisible(false);
        newCarButton.setVisible(false);
        deleteButton.setVisible(false);
        updateButton.setVisible(false);
        insertCarPane.setVisible(true);
        backButton.setVisible(true);
        saveButton.setVisible(true);
    }

    public void cleanFields(){
        colorField.setText("");
        plateField.setText("");
        kmField.setText("");
        modelMenu.setValue(null);
        situationMenu.setValue(null);
    }

    public boolean validarCamposLLenos(){
        boolean camposLLenos = true;
         if ((plateField.getText().equals("")) || (kmField.getText().equals(""))
                || (colorField.getText().equals("")) || (modelMenu.getValue() == null)
                || (situationMenu.getValue() == null)) {

                Alert alert2 = new Alert(AlertType.INFORMATION);
                alert2.setHeaderText(null);
                alert2.setContentText("You must fill in all fields");
                alert2.showAndWait();
                camposLLenos = false;
            }
            return camposLLenos;
    }

    public void close() {
        Stage stage = (Stage) carsTable.getScene().getWindow();
        Model.getInstanse().getViewFactory().closeStage(stage);
    }
    public void minimice(){
        Stage stage = (Stage) carsTable.getScene().getWindow();
        stage.setIconified(true);
    }

/***********************  METODO INITIALIZE   *****************************/

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        
        try {
            configurarTablaCar();
            rellenarTablaCar();
            configurarChoiceBoxModel();
            configurarChoiceBoxSituation();
            rellenarChoiceBoxModel();
            rellenarChoiceBoxSituation();

        } catch (SQLException e) {       
            e.printStackTrace();
        }

        //Solo numeros en el campo de KM
        kmField.addEventFilter(KeyEvent.KEY_TYPED, event -> {
            if (!event.getCharacter().matches("\\d")) {
                event.consume();
            }
        });
    }
}
