package cu.edu.cujae.bd.visual.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import cu.edu.cujae.bd.dto.CarDto;
import cu.edu.cujae.bd.service.ServicesLocator;
import cu.edu.cujae.bd.visual.models.Model;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class DashboardController implements Initializable{
    private ObservableList <CarDto> listCars = FXCollections.observableArrayList();

    @FXML
    private Button closeButton;

    @FXML
    private Label alqCars;

    @FXML
    private Label availableCars;

    @FXML
    private Label tallerCars;

    @FXML
    private Button btnRefresh;

    @FXML
    private FontAwesomeIcon icon;

    public void setAvailableCarsLbl(){
        initListCar();
        int count = 0;
        for (CarDto carDto : listCars) {
            if(carDto.getSituation().getCodSituation()==1){
                count ++;
            }              
        }
        System.out.println(count+ " disponible");
        int i = count;
        availableCars.setText(i + "");
    }

    public void setTallerCarsLbl(){
        int count = 0;
        for (CarDto carDto : listCars) {
            if(carDto.getSituation().getCodSituation()==2){
                count ++;
            }
        }
        System.out.println(count + " taller");
        tallerCars.setText(count +"");
    }

    public void setAlqCarsLbl(){
        int count = 0;
        for (CarDto carDto : listCars) {
            if(carDto.getSituation().getCodSituation()==3){
                count ++;
            }     
        }
        System.out.println(count + " alquilado");
        alqCars.setText(count + "");
    }

    public void setListCar(){
        try {
            this.listCars = ServicesLocator.getCarServices().getAllCars();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void onRefresh(){
        setAvailableCarsLbl();
        setAlqCarsLbl();
        setTallerCarsLbl();
    }

    public void initListCar(){
        try {
            this.listCars = ServicesLocator.getCarServices().getAllCars();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public ObservableList<CarDto> getListCar(){
        return this.listCars;
    }

    public void close(){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        Model.getInstanse().getViewFactory().closeStage(stage);
    }
    public void minimice(){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.setIconified(true);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setAvailableCarsLbl();
        setAlqCarsLbl();
        setTallerCarsLbl();
    }

}
