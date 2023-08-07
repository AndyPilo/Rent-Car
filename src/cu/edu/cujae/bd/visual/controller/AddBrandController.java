package cu.edu.cujae.bd.visual.controller;

import java.io.IOException;
import java.sql.SQLException;

import cu.edu.cujae.bd.dto.BrandDto;
import cu.edu.cujae.bd.service.ServicesLocator;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddBrandController {
    private boolean update = false;

    @FXML
    private TextField brandNameField;

    public void saveBrand() throws IOException,SQLException{
        if(update){

        }else{
            if(brandNameField.getText().equals("")){
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("You must fill in all fields");
                alert.showAndWait();
            }else{
                ServicesLocator.getBrandServices().insertBrand(new BrandDto(brandNameField.getText()));             
                closeBrandWindows();
            }  
            
            
        }
    }

     public void closeBrandWindows(){
        Stage stage = (Stage) brandNameField.getScene().getWindow();
        stage.close();
    }
}
