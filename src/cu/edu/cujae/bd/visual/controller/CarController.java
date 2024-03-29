package cu.edu.cujae.bd.visual.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

import cu.edu.cujae.bd.dto.BrandDto;
import cu.edu.cujae.bd.dto.CarDto;
import cu.edu.cujae.bd.dto.ContractDto;
import cu.edu.cujae.bd.dto.ModelDto;
import cu.edu.cujae.bd.dto.SituationDto;
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.StringConverter;

public class CarController implements Initializable {

    public ObservableList<CarDto> cars = FXCollections.observableArrayList();
    private ObservableList<CarDto> carsList = FXCollections.observableArrayList();
    private ObservableList<ModelDto> listModels = FXCollections.observableArrayList();
    private ObservableList<SituationDto> listSituation = FXCollections.observableArrayList();
    private ObservableList<BrandDto> listBrands = FXCollections.observableArrayList();
    private CarDto selectedCar;
    private BrandDto selectedBrand;
    private ModelDto selectedModel;

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
    private TableColumn<CarDto, String> brandColumn;
    @FXML
    private TableColumn<CarDto, String> priceColumn;
    @FXML
    private Button newCarButton;
    @FXML
    private AnchorPane insertCarPane;
    @FXML
    private TextField colorField;
    @FXML
    private TextField plateField;
    @FXML
    private TextField priceField;
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
    private Button refreshButton;
    @FXML
    private Label carFormLabel;
    @FXML
    private Button addModelButton;
    @FXML
    private Button addSituationButton;
    @FXML
    private AnchorPane  brandPane;
    @FXML
    private AnchorPane  modelPane;
    @FXML
    private Button deleteModelButton;
    @FXML
    private Button deleteBrandButton;
    @FXML
    private Button updateModelButton;
    @FXML
    private Button updateBrandButton;
    @FXML
    private Button newModelButton;
    @FXML
    private Button newBrandButton;
    @FXML
    private AnchorPane carButtonPane;
    @FXML
    private TableView<ModelDto> modelTable;
    @FXML
    private TableView<BrandDto> brandTable;
    @FXML
    private TableColumn<ModelDto, String> colModelM;
    @FXML
    private TableColumn<ModelDto, String> colBrandM;
    @FXML
    private TableColumn<BrandDto, String> colBrandB;
    @FXML
    private Label infSituationLbl;
        


/****************************    TABLAS Y CHOICEBOX     ****************************/

    public void configurarTablaCar() {
        plateColumn.setCellValueFactory(new PropertyValueFactory<>("Plate"));
        colorColumn.setCellValueFactory(new PropertyValueFactory<>("Color"));
        brandColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("Price"));
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

    public void configurarTablaModel() {
        colModelM.setCellValueFactory(new PropertyValueFactory<>("nameModel"));
        
        colBrandM.setCellValueFactory(cellData -> {
            BrandDto brand = cellData.getValue().getBrand();
            String nameBrand = brand != null ? brand.getNameBrand() : "";
            return new SimpleStringProperty(nameBrand);
        });
        
        modelTable.setItems(listModels);

    }

    public void configurarTablaBrand() {
        colBrandB.setCellValueFactory(new PropertyValueFactory<>("nameBrand"));
        
        brandTable.setItems(listBrands);
    }

    public void rellenarTablaCar() throws SQLException {
        cars.clear();
        this.carsList = ServicesLocator.getCarServices().getAllCars();
        cars.setAll(carsList);
    }

    public void rellenarTablaModel() throws SQLException {
        listModels.clear();
        ObservableList<ModelDto> listaModelos = ServicesLocator.getModelServices().getAllModels();
        listModels.setAll(listaModelos);
    }

    public void rellenarTablaBrand() throws SQLException {
        listBrands.clear();
        ObservableList<BrandDto> listaMarcas = ServicesLocator.getBrandServices().getAllBrand();
        listBrands.setAll(listaMarcas);
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
        situationMenu.setDisable(false);
        infSituationLbl.setVisible(false);
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
            priceField.setText(String.valueOf(selectedCar.getPrice()));
            ModelDto model = new ModelDto();
            for(int i = 0;i<listModels.size();i++){
                if(this.selectedCar.getModel().getNameModel().equals(listModels.get(i).getNameModel())){
                    model = listModels.get(i);
                }
            }
            modelMenu.setValue(model);
            SituationDto situation = null;
            for(int i = 0;i<listSituation.size();i++){
                if(this.selectedCar.getSituation().getSituation().equals(listSituation.get(i).getSituation())){
                    situation = listSituation.get(i);
                }
            }
            situationMenu.setValue(situation);
            onCarForm();

            boolean alquilado = false;
            ObservableList<ContractDto> contractList;
            try {
                contractList = ServicesLocator.getContractServices().getAllContract();
                for (ContractDto contractDto : contractList) {
                if(selectedCar.getCodCar() == contractDto.getCar().getCodCar()){
                    alquilado = true;
                    
                }
            }
            } catch (SQLException e) {
                e.printStackTrace();
            }         

            if(alquilado){
                situationMenu.setDisable(true);
                infSituationLbl.setVisible(true);
            }
                
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

                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setHeaderText(null);
                alert.setTitle("Confirmation Message");
                alert.setContentText("Are you sure you want to delete ?");
                Optional<ButtonType> option = alert.showAndWait();

                if(option.get().equals(ButtonType.OK)){
                    try {
                    ServicesLocator.getCarServices().deleteCar(selectedCarDto.getCodCar());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    cars.remove(selectedCarDto);
                    carsTable.refresh();

                    Alert alert2 = new Alert(AlertType.INFORMATION);
                    alert2.setHeaderText(null);
                    alert2.setContentText("Successfuly");
                    alert2.showAndWait();
                }
            }else{
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("You must select a car");
                alert.showAndWait();
            }
    }

     //Se ejecuta al dar clic en el boton delete de model
     public void onDeleteButtonModel() {
        ModelDto selectedModelDto = modelTable.getSelectionModel().getSelectedItem();
            if (selectedModelDto != null) {
                try {
                    ServicesLocator.getModelServices().deleteModel(selectedModelDto.getCodModel());;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                listModels.remove(selectedModelDto);
                modelTable.refresh();
            }else{
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("You must select a model");
                alert.showAndWait();
            }
    }

    //Se ejecuta al dar clic en el boton delete de brand
     public void onDeleteButtonBrand() {
        BrandDto selectedBrandDto = brandTable.getSelectionModel().getSelectedItem();
            if (selectedBrandDto != null) {
                try {
                    ServicesLocator.getBrandServices().deleteBrand(selectedBrandDto.getCodBrand());;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                listBrands.remove(selectedBrandDto);
                brandTable.refresh();
            }else{
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("You must select a brand");
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
                                        situationMenu.getValue(),
                                        Integer.parseInt(priceField.getText()));
                    
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
                        , situationMenu.getValue()
                        , Integer.parseInt(priceField.getText()));
            
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

    public void onAddModelButton() throws SQLException, IOException{
        
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../fxml/addModel.fxml"));
            loader.load();
            AddModelController controller = loader.getController();
            controller.initAtributes();

            Parent parent = loader.getRoot();
            Stage stage = new Stage(StageStyle.UNDECORATED);
            stage.setScene(new Scene(parent));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

            ModelDto model = controller.getSelectedModel();

            if(model != null){
                ServicesLocator.getModelServices().insertModel(model);
                rellenarTablaModel();
            }                   
    }

    public void onAddBrandButton() throws SQLException, IOException{
         
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../fxml/addBrand.fxml"));
            loader.load();
            AddBrandController controller = loader.getController();
            controller.initAtributes();

            Parent parent = loader.getRoot();
            Stage stage = new Stage(StageStyle.UNDECORATED);
            stage.setScene(new Scene(parent));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

            BrandDto brand = controller.getSelectedBrand();
            if(brand != null){
                ServicesLocator.getBrandServices().insertBrand(brand);
                rellenarTablaBrand();
            }
    }

    public void onUpdModelButton() throws IOException, SQLException{
        this.selectedModel = modelTable.getSelectionModel().getSelectedItem();

        if(selectedModel!=null){
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../fxml/addModel.fxml"));
            loader.load();
            AddModelController controller = loader.getController();

            controller.initAtributes(selectedModel);

            Parent parent = loader.getRoot();
            Stage stage = new Stage(StageStyle.UNDECORATED);
            stage.setScene(new Scene(parent));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
                
            ModelDto model = controller.getSelectedModel();
            if(model != null){
                ServicesLocator.getModelServices().updateModel(model); 
                rellenarTablaBrand();
            }        
        }else{
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Info");
            alert.setHeaderText(null);
            alert.setContentText("You must select a model");
            alert.showAndWait();
        }
    }

    public void onUpdBrandButton() throws SQLException, IOException{
        this.selectedBrand = brandTable.getSelectionModel().getSelectedItem();

        if(selectedBrand!=null){
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../fxml/addBrand.fxml"));
            loader.load();
            AddBrandController controller = loader.getController();

            controller.initAtributes(selectedBrand);

            Parent parent = loader.getRoot();
            Stage stage = new Stage(StageStyle.TRANSPARENT);
            stage.setScene(new Scene(parent));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
                
            BrandDto brand = controller.getSelectedBrand();
            if(brand != null){ 
                ServicesLocator.getBrandServices().updateBrand(brand);
                rellenarTablaBrand();
            }          
        }else{
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Info");
            alert.setHeaderText(null);
            alert.setContentText("You must select a brand");
            alert.showAndWait();
        }
    }

    public void onModelButton(){
        modelPane.setVisible(true);
        carButtonPane.setVisible(false);
        brandPane.setVisible(false);

    }

    public void onBrandButton(){
        modelPane.setVisible(false);
        carButtonPane.setVisible(false);
        brandPane.setVisible(true);
    }
    
    public void onCarButton(){
        modelPane.setVisible(false);
        carButtonPane.setVisible(true);
        brandPane.setVisible(false);
    }

    public void refresh(){
        configurarTablaModel();
        configurarTablaBrand();
        try {
            rellenarTablaBrand();
            rellenarTablaModel();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
/***********************           VISTAS          *****************************/
    public void onBackButton() {
        carsTable.setVisible(true);
        newCarButton.setVisible(true);
        deleteButton.setVisible(true);
        updateButton.setVisible(true);
        insertCarPane.setVisible(false);
        backButton.setVisible(false);
        saveButton.setVisible(false);
        refreshButton.setVisible(true);
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
        refreshButton.setVisible(false);
    }

    public void cleanFields(){
        colorField.setText("");
        plateField.setText("");
        kmField.setText("");
        modelMenu.setValue(null);
        situationMenu.setValue(null);
        priceField.setText("");
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

    public ObservableList<CarDto> getCarList(){  
        return this.carsList;
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

        configurarTablaCar();
        configurarTablaModel();
        configurarTablaBrand();
        configurarChoiceBoxModel();
        configurarChoiceBoxSituation();

        try {
            rellenarTablaCar();
            rellenarTablaModel();
            rellenarTablaBrand();        
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
