package cu.edu.cujae.bd.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import cu.edu.cujae.bd.dto.DriverDto;

public class DriverServices {

    
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
}
