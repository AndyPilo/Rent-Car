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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.StringConverter;

public class AddModelController implements Initializable{

    private ObservableList<BrandDto> listBrands = FXCollections.observableArrayList();
    private boolean update = false;

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
        if(update){

        }else{
            if(modelNameField.getText().equals("") || brandMenu.getValue() == null){
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("You must fill in all fields");
                alert.showAndWait();
            }else{
                ServicesLocator.getModelServices().insertModel(new ModelDto( modelNameField.getText(), brandMenu.getValue()));             
                close();
            }         
        }
    }

    public void onAddBrandButton(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../fxml/addBrand.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent parent = loader.getRoot();
        Stage stage = new Stage(StageStyle.UTILITY);
        stage.setScene(new Scene(parent));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

   

    public void close(){
        Stage stage = (Stage) brandMenu.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configurarChoiceBoxBrand();
        rellenarChoiceBoxBrand();
    }
}
