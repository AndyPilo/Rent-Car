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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.Bloom;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class CarController implements Initializable {

    public ObservableList<CarDto> cars = FXCollections.observableArrayList();
    private ObservableList<ModelDto> listModels = FXCollections.observableArrayList();
    private ObservableList<SituationDto> listSituation = FXCollections.observableArrayList();

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
    private Button refreshButton;
    @FXML
    private Button insertButton;
    @FXML
    private Button backButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button updateButton;
    @FXML
    private Button closeButton;

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

    public void onNewCar() {
        carsTable.setVisible(false);
        newCarButton.setVisible(false);
        insertCarPane.setVisible(true);
        backButton.setVisible(true);
        refreshButton.setVisible(true);
        insertButton.setVisible(true);

    }

    public void onDeleteButton() {
        CarDto selectedCar = carsTable.getSelectionModel().getSelectedItem();
        if (selectedCar != null) {
            try {
                ServicesLocator.getCarServices().deleteCar(selectedCar.getCodCar());
            } catch (SQLException e) {
                e.printStackTrace();
            }

            cars.remove(selectedCar);
            carsTable.refresh();
        }
    }

    public void close() {
        Stage stage = (Stage) carsTable.getScene().getWindow();
        Model.getInstanse().getViewFactory().closeStage(stage);
    }

    public void moved(){
        Bloom bloom =new Bloom();
        bloom.setThreshold(0.80);
        closeButton.setEffect(bloom);
    }

    public void exited(){
        closeButton.setEffect(null);
    }

    /*public void onUpdate() {
        CarDto filaSeleccionada = carsTable.getSelectionModel().getSelectedItem();

        if (filaSeleccionada != null) {
            onNewCar();
            int codCar = filaSeleccionada.getCodCar();
            plateField.setText(filaSeleccionada.getPlate());
            colorField.setText(filaSeleccionada.getColor());
            kmField.setText(String.valueOf(filaSeleccionada.getKm()));
            modelMenu.setValue(filaSeleccionada.getModel());
            situationMenu.setValue(filaSeleccionada.getSituation());

            CarDto carUpdate = new CarDto(codCar,
                    plateField.getText(),
                    colorField.getText() ,
                    Integer.parseInt(kmField.getText()),
                    modelMenu.getValue(),
                    situationMenu.getValue());

            try {
                ServicesLocator.getCarServices().updateCar(carUpdate);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }*/

    public void onBackButton() {
        carsTable.setVisible(true);
        newCarButton.setVisible(true);
        insertCarPane.setVisible(false);
        backButton.setVisible(false);
        insertButton.setVisible(false);
        onRefreshButton();

    }

    public void onInsertButton() {

        if ((plateField.getText().equals("")) || (kmField.getText().equals(""))
                || (colorField.getText().equals("")) || (modelMenu.getValue() == null)
                || (situationMenu.getValue() == null)) {

            Alert alert2 = new Alert(AlertType.ERROR);
            alert2.setTitle("Error");
            alert2.setHeaderText(null);
            alert2.setContentText("Se produjo un error al insertar el Auto.");
            alert2.showAndWait();
        } else {
            String plate = plateField.getText();
            SituationDto situation = situationMenu.getValue();
            ModelDto model = modelMenu.getValue();
            int km = Integer.parseInt(kmField.getText());
            String color = colorField.getText();

            CarDto carDto = new CarDto(plate, color, km, model, situation);
            try {
                ServicesLocator.getCarServices().insertCar(carDto);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            // Mostrar mensaje de confirmación
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Éxito");
            alert.setHeaderText(null);
            alert.setContentText("Se ha insertado correctamente el Auto.");
            alert.showAndWait();
        }
        onRefreshButton();
    }

    public void onRefreshButton() {
        colorField.setText("");
        plateField.setText("");
        kmField.setText("");
        modelMenu.setValue(null);
        situationMenu.setValue(null);
             
           
            try {
                rellenarTablaCar();
                rellenarChoiceBoxModel();
                rellenarChoiceBoxSituation();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        
        try {
            configurarTablaCar();
            rellenarTablaCar();
            configurarChoiceBoxModel();
            configurarChoiceBoxSituation();
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
