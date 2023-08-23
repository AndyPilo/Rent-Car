package cu.edu.cujae.bd.visual.controller;

import cu.edu.cujae.bd.dto.BrandDto;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddBrandController {
    private BrandDto selectedBrand;

    @FXML
    private TextField brandNameField;

    public void initAtributes(){
        this.selectedBrand = new BrandDto();
    }

    public void initAtributes(BrandDto brand){
        this.selectedBrand = brand;
        this.brandNameField.setText(brand.getNameBrand());
    }

    public void saveBrand(){

        if(brandNameField.getText().equals("")){  
            Alert alert2 = new Alert(AlertType.INFORMATION);
                alert2.setHeaderText(null);
                alert2.setContentText("You must fill in all fields");
                alert2.showAndWait();
        }else{
            String nameBrand = brandNameField.getText();
            
            if(this.selectedBrand != null){
                this.selectedBrand.setNameBrand(nameBrand);
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Éxito");
                alert.setHeaderText(null);
                alert.setContentText("The new brand has been updated successfully.");
                alert.showAndWait();
                Stage stage = (Stage) brandNameField.getScene().getWindow();
                stage.close();
            }else{                   
                this.selectedBrand.setNameBrand(brandNameField.getText());
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Éxito");
                alert.setHeaderText(null);
                alert.setContentText("The brand has been inserted successfully.");
                alert.showAndWait();                      
                Stage stage = (Stage) brandNameField.getScene().getWindow();
                stage.close();
            } 
        }
    }

     public void closeBrandWindows(){
        this.selectedBrand = null;
        Stage stage = (Stage) brandNameField.getScene().getWindow();
        stage.close();
    }

    public BrandDto getSelectedBrand(){
        return this.selectedBrand;
    }
}
