package cu.edu.cujae.bd.visual.controller;

import cu.edu.cujae.bd.dto.CarDto;
import cu.edu.cujae.bd.visual.models.Model;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class DashboardController{

    @FXML
    private Button closeButton;

    @FXML
    private Label alqCars;

    @FXML
    private Label availableCars;

    @FXML
    private Label tallerCars;

    public void setAvailableCarsLbl(ObservableList<CarDto> listCars){
        int count = 0;
        for (CarDto carDto : listCars) {
            if(carDto.getSituation().getCodSituation()==1){
                count ++;
            }              
        }
        System.out.println(count);
        int i = count;
        availableCars.setText(i + "");
    }

    public void setTallerCarsLbl(ObservableList<CarDto> listCars){
        int count = 0;
        for (CarDto carDto : listCars) {
            if(carDto.getSituation().getCodSituation()==2){
                count ++;
            }
        }
        System.out.println(count);
        int i = count;
        tallerCars.setText(i+"");
    }

    public void setAlqCarsLbl(ObservableList<CarDto> listCars){
        int count = 0;
        for (CarDto carDto : listCars) {
            if(carDto.getSituation().getCodSituation()==3){
                count ++;
            }     
        }
        System.out.println(count);
        alqCars.setText(count + "");
    }

    public void close(){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        Model.getInstanse().getViewFactory().closeStage(stage);
    }
    public void minimice(){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.setIconified(true);
    }

}
