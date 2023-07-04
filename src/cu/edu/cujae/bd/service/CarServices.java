package cu.edu.cujae.bd.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import cu.edu.cujae.bd.dto.CarDto;
import cu.edu.cujae.bd.dto.ModelDto;
import cu.edu.cujae.bd.dto.SituationDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CarServices {

	private ModelServices modelServices = ServicesLocator.getModelServices();
	private SituationServices situationServices = ServicesLocator.getSituationServices();
	
    public void insertCar(CarDto car) throws SQLException{
    	String function = "{call insert_car(?,?,?,?,?)}";
		
		Connection connection = ServicesLocator.getConnection();		
		CallableStatement preparedFunction = connection.prepareCall(function);
		preparedFunction.setString(1, car.getPlate());
		preparedFunction.setInt(2, car.getModel().getCodModel());
		preparedFunction.setString(3, car.getColor());
		preparedFunction.setInt(4,car.getKm());
		preparedFunction.setInt(5, car.getSituation().getCodSituation());
		
		preparedFunction.execute();
		
		preparedFunction.close();
		connection.close();
    }
    
    public void updateCar(CarDto car) throws SQLException{
    	String function = "{call update_car(?,?,?,?,?,?)}";
		
		Connection connection = ServicesLocator.getConnection();		
		CallableStatement preparedFunction = connection.prepareCall(function);
		preparedFunction.setInt(1,car.getCodCar());
		preparedFunction.setString(2, car.getPlate());
		preparedFunction.setInt(3, car.getModel().getCodModel());
		preparedFunction.setString(4, car.getColor());
		preparedFunction.setInt(5,car.getKm());
		preparedFunction.setInt(6, car.getSituation().getCodSituation());
		
		preparedFunction.execute();
		
		preparedFunction.close();
		connection.close();
    }
    
    public void deleteCar(int carId) throws SQLException{
    	String function = "{call delete_car(?)}";
		
		Connection connection = ServicesLocator.getConnection();		
		CallableStatement preparedFunction = connection.prepareCall(function);
		preparedFunction.setInt(1, carId);
		
		preparedFunction.execute();
		
		preparedFunction.close();
		connection.close();
    }
    
    public ObservableList<CarDto> getAllCars() throws SQLException {
		
		ObservableList<CarDto> lista = FXCollections.observableArrayList();
		ObservableList<ModelDto> models = modelServices.getAllModels();
		ObservableList<SituationDto> situations = situationServices.getAllSituation();

		String function = "{?= call list_cars()}";
		System.out.println("Conexion de Car");
		Connection connection = ServicesLocator.getConnection();
		connection.setAutoCommit(false);
		
		CallableStatement preparedFunction = connection.prepareCall(function);
		preparedFunction.registerOutParameter(1,java.sql.Types.OTHER);
		preparedFunction.execute();	
		ResultSet resultSet = (ResultSet) preparedFunction.getObject(1);
		
		while(resultSet.next()){
			int codCar = resultSet.getInt(1);
			String plate = resultSet.getString(2);
			String color = resultSet.getString(4);
			int km = resultSet.getInt(5);
			int codModel = resultSet.getInt(3);
			ModelDto model = null;
			int codSituation = resultSet.getInt(6);
			SituationDto situation = null;

			for(int i = 0;i < models.size();i++){
				if(codModel == models.get(i).getCodModel()){
					 model = models.get(i);
				}
			}
			for(int i = 0;i < situations.size();i++){
				if(codSituation == situations.get(i).getCodSituation()){
					situation = situations.get(i);
				}
			}

			lista.add(new CarDto(codCar,plate,color,km,model,situation));
		}

		resultSet.close();
		preparedFunction.close();
		connection.close();
		return lista;
	}
    
}
