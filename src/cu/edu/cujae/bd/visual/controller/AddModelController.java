package cu.edu.cujae.bd.visual.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import cu.edu.cujae.bd.dto.BrandDto;
import cu.edu.cujae.bd.dto.ModelDto;
import cu.edu.cujae.bd.service.ServicesLocator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class AddModelController implements Initializable{

    private ObservableList<BrandDto> listBrands = FXCollections.observableArrayList();
    private ModelDto selectedModel;

    @FXML
    private ChoiceBox<BrandDto> brandMenu;
    @FXML
    private TextField modelNameField;

     public void configurarChoiceBoxBrand() {
        brandMenu.setConverter(new StringConverter<BrandDto>() {
            @Override
            public String toString(BrandDto brand) {
                return brand.getNameBrand(); // Devuelve el nombre del modelo como cadena
            }

            @Override
            public BrandDto fromString(String string) {
                return null; // No es necesario implementar este método para ChoiceBox
            }
        });

    }

    public void rellenarChoiceBoxBrand() {
        try {
            listBrands = ServicesLocator.getBrandServices().getAllBrand();
            brandMenu.setItems(listBrands);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void save() throws IOException,SQLException{
        if(modelNameField.getText().equals("") || brandMenu.getValue()==null){
            
            Alert alert2 = new Alert(AlertType.INFORMATION);
                alert2.setHeaderText(null);
                alert2.setContentText("You must fill in all fields");
                alert2.showAndWait();
        }else{
             String nameModel = modelNameField.getText();

            if(this.selectedModel.getCodModel()==0){
                this.selectedModel.setNameModel(nameModel);
                this.selectedModel.setBrand(brandMenu.getValue()); 

                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Éxito");
                alert.setHeaderText(null);
                alert.setContentText("The new model has been inserted successfully.");
                alert.showAndWait();                      
                Stage stage = (Stage) brandMenu.getScene().getWindow();
                stage.close();  
            }else{
                this.selectedModel.setNameModel(nameModel);
                this.selectedModel.setBrand(brandMenu.getValue());
                
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Éxito");
                alert.setHeaderText(null);
                alert.setContentText("The model has been updated successfully.");
                alert.showAndWait();                     
                Stage stage = (Stage) brandMenu.getScene().getWindow();
                stage.close(); 
            }
        }
    }

    public void initAtributes(){
        this.selectedModel = new ModelDto();
    }

    public void initAtributes(ModelDto model){
        this.selectedModel = model;
        this.modelNameField.setText(model.getNameModel());
        BrandDto brandDto = null;
        for (BrandDto brand : listBrands) {
            if(model.getBrand().getCodBrand() == brand.getCodBrand()){
                brandDto = brand;       
            }
        }
        brandMenu.setValue(brandDto);
    }

    public void close(){
        this.selectedModel = null;
        Stage stage = (Stage) brandMenu.getScene().getWindow();
        stage.close();
    }

    public ModelDto getSelectedModel(){
        return this.selectedModel;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configurarChoiceBoxBrand();
        rellenarChoiceBoxBrand();
    }
}
