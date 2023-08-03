package cu.edu.cujae.bd.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import cu.edu.cujae.bd.dto.DriverCategoryDto;
import cu.edu.cujae.bd.dto.DriverDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DriverServices {

    private DriverCategoryServices driverCategoryServices = ServicesLocator.getDriverCategoryServices();
    
    public void insertDriver(DriverDto driver) throws SQLException{
        String function ="{call insert_driver(?,?,?,?,?)}";
		
		Connection connection = ServicesLocator.getConnection();
		CallableStatement preparedFunction = connection.prepareCall(function);
        preparedFunction.setString(1,driver.getDni());
        preparedFunction.setString(2, driver.getNameDriver());
        preparedFunction.setString(3, driver.getLastName());
        preparedFunction.setString(4, driver.getAddress());
        preparedFunction.setInt(5, driver.getCategory().getCodCategory());

        preparedFunction.execute();

        preparedFunction.close();
        connection.close();
    }


    public void updateDriver(DriverDto driver) throws SQLException{
        String function ="{call update_driver(?,?,?,?,?,?)}";
		
		Connection connection = ServicesLocator.getConnection();
		CallableStatement preparedFunction = connection.prepareCall(function);
        preparedFunction.setInt(1, driver.getCodDriver());
        preparedFunction.setString(2,driver.getDni());
        preparedFunction.setString(3, driver.getNameDriver());
        preparedFunction.setString(4, driver.getLastName());
        preparedFunction.setString(5, driver.getAddress());
        preparedFunction.setInt(6, driver.getCategory().getCodCategory());

        preparedFunction.execute();

        preparedFunction.close();
        connection.close();
    }


    public void deleteDriver(int driverId) throws SQLException{
        String function ="{call delete_driver(?)}";
		
		Connection connection = ServicesLocator.getConnection();
		CallableStatement preparedFunction = connection.prepareCall(function);
        preparedFunction.setInt(1, driverId);

        preparedFunction.execute();

        preparedFunction.close();
        connection.close();
    }

     public ObservableList<DriverDto> getAllDrivers() throws SQLException {
		
		ObservableList<DriverDto> lista = FXCollections.observableArrayList();
		ObservableList<DriverCategoryDto> categorys = driverCategoryServices.getAllCategorys();

		String function = "{?= call list_drivers()}";
		Connection connection = ServicesLocator.getConnection();
		connection.setAutoCommit(false);
		
		CallableStatement preparedFunction = connection.prepareCall(function);
		preparedFunction.registerOutParameter(1,java.sql.Types.OTHER);
		preparedFunction.execute();	
		ResultSet resultSet = (ResultSet) preparedFunction.getObject(1);
		
		while(resultSet.next()){
			int codDriver = resultSet.getInt(1);
			String dni = resultSet.getString(2);
			String name = resultSet.getString(3);
            String lastName = resultSet.getString(4);
            String address = resultSet.getString(5);

			int codCategory = resultSet.getInt(6);
			DriverCategoryDto category = null;

			for(int i = 0;i < categorys.size();i++){
				if(codCategory == categorys.get(i).getCodCategory()){
					category = categorys.get(i);
				}
			}

			lista.add(new DriverDto(codDriver,dni,name,lastName,address,category));
		}

		resultSet.close();
		preparedFunction.close();
		connection.close();
		return lista;
	}
}
