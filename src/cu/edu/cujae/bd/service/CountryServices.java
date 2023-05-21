package cu.edu.cujae.bd.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import cu.edu.cujae.bd.dto.CountryDto;

public class CountryServices {
	
	
	public void insertCountry(CountryDto country) throws SQLException{
		String function = "{call insert_country(?)}";
		Connection connection = ServicesLocator.getConnection();		
		CallableStatement preparedFunction = connection.prepareCall(function);
		preparedFunction.setString(1, country.getName());
		preparedFunction.execute();
		
		preparedFunction.close();
		connection.close();	
	}
	
	public void deleteCountry(int id) throws SQLException{
		String function = "{call delete_country(?)}";
		Connection connection = ServicesLocator.getConnection();		
		CallableStatement preparedFunction = connection.prepareCall(function);
		preparedFunction.setInt(1, id);
		preparedFunction.execute();
		
		preparedFunction.close();
		connection.close();
	}
	
	public void updateCountry(CountryDto country) throws SQLException{
		String function = "{call update_country(?,?)}";
		Connection connection = ServicesLocator.getConnection();		
		CallableStatement preparedFunction = connection.prepareCall(function);
		preparedFunction.setInt(1, country.getCodCountry());
		preparedFunction.setString(2, country.getName());
		preparedFunction.execute();
		
		preparedFunction.close();
		connection.close();	
	}
	
	public CountryDto getCountryById (int countryId) throws SQLException{
		CountryDto country = null;
		String function = "{?= call load_country_by_id(?)}";
		
		Connection connection = ServicesLocator.getConnection();
		connection.setAutoCommit(false);
		
		CallableStatement preparedFunction = connection.prepareCall(function);
		preparedFunction.registerOutParameter(1, java.sql.Types.OTHER);
		preparedFunction.setInt(2, countryId);
		preparedFunction.execute();
		
		ResultSet resultSet = (ResultSet) preparedFunction.getObject(1);
		resultSet.next();
		
		country = new CountryDto(resultSet.getInt(1),resultSet.getString(2));
		
		resultSet.close();
		preparedFunction.close();
		connection.close();
		
		return country;
		
	}
}
