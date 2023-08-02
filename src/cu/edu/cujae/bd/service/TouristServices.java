package cu.edu.cujae.bd.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import cu.edu.cujae.bd.dto.CountryDto;
import cu.edu.cujae.bd.dto.TouristDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TouristServices {

	private CountryServices countryServices = ServicesLocator.getCountryServices();

	public TouristServices() {

	}

	public void insertTourist(TouristDto tourist) throws SQLException {
		String function = "{call insert_tourist(?,?,?,?,?,?,?)}";

		Connection connection = ServicesLocator.getConnection();
		CallableStatement preparedFunction = connection.prepareCall(function);
		preparedFunction.setString(1, tourist.getPassport());
		preparedFunction.setString(2, tourist.getName());
		preparedFunction.setString(3, tourist.getLastName());
		preparedFunction.setInt(4, tourist.getAge());
		preparedFunction.setString(5, String.valueOf(tourist.getSex()));
		preparedFunction.setString(6, tourist.getContact());
		preparedFunction.setInt(7, tourist.getCountry().getCodCountry());
		preparedFunction.execute();

		preparedFunction.close();
		connection.close();
	}

	public void deleteTourist(int touristId) throws SQLException {
		String function = "{call delete_tourist(?)}";

		Connection connection = ServicesLocator.getConnection();
		CallableStatement preparedFunction = connection.prepareCall(function);
		preparedFunction.setInt(1, touristId);
		preparedFunction.execute();

		preparedFunction.close();
		connection.close();
	}

	public void updateTourist(TouristDto tourist) throws SQLException {
		String function = "{call update_tourist(?,?,?,?,?,?,?,?)}";

		Connection connection = ServicesLocator.getConnection();
		CallableStatement preparedFunction = connection.prepareCall(function);
		preparedFunction.setInt(1, tourist.getCodTourist());
		preparedFunction.setString(2, tourist.getPassport());
		preparedFunction.setString(3, tourist.getName());
		preparedFunction.setString(4, tourist.getLastName());
		preparedFunction.setInt(5, tourist.getAge());
		preparedFunction.setString(6, String.valueOf(tourist.getSex()));
		preparedFunction.setString(7, tourist.getContact());
		preparedFunction.setInt(8, tourist.getCountry().getCodCountry());
		preparedFunction.execute();

		preparedFunction.close();
		connection.close();
	}

	public ObservableList<TouristDto> getAllTourist() throws SQLException {
		ObservableList<TouristDto> lista = FXCollections.observableArrayList();
		ObservableList<CountryDto> countrys = countryServices.getAllCountry();

		String function = "{?= call list_tourists()}";
		Connection connection = ServicesLocator.getConnection();
		connection.setAutoCommit(false);

		CallableStatement preparedFunction = connection.prepareCall(function);
		preparedFunction.registerOutParameter(1, java.sql.Types.OTHER);
		preparedFunction.execute();

		ResultSet resultSet = (ResultSet) preparedFunction.getObject(1);

		while (resultSet.next()) {

			int codCountry = resultSet.getInt("cod_country");
			CountryDto country = null;

			for(int i = 0;i < countrys.size();i++){
				if(codCountry == countrys.get(i).getCodCountry()){
					country = countrys.get(i);
				}
			}
			
			lista.add(new TouristDto(resultSet.getInt("cod_tourist"),
					resultSet.getString("passport"),
					resultSet.getString("name"),
					resultSet.getString("last_name"),
					resultSet.getInt("age"),
					resultSet.getString("sex").charAt(0),
					resultSet.getString("contact"),
					country));
		}

		resultSet.close();
		preparedFunction.close();
		connection.close();

		return lista;
	}

	public TouristDto getTouristById(int touristId) throws SQLException {
		TouristDto tourist = null;

		String function = "{?= call load_tourist_by_id(?)}";
		Connection connection = ServicesLocator.getConnection();
		connection.setAutoCommit(false);

		CallableStatement preparedFunction = connection.prepareCall(function);
		preparedFunction.registerOutParameter(1, java.sql.Types.OTHER);
		preparedFunction.setInt(2, touristId);
		preparedFunction.execute();

		ResultSet resultSet = (ResultSet) preparedFunction.getObject(1);
		if (resultSet.next()) {
			String passport = resultSet.getString(2);
			String name = resultSet.getString(3);
			String lastName = resultSet.getString(4);
			int age = resultSet.getInt(5);
			char sex = resultSet.getString(6).charAt(0);
			String contact = resultSet.getString(7);
			CountryDto country = ServicesLocator.getCountryServices().getCountryById(resultSet.getInt(8));

			tourist = new TouristDto(passport, name, lastName, age, sex, contact, country);
			tourist.setCodTourist(touristId);
		}

		resultSet.close();
		preparedFunction.close();
		connection.close();

		return tourist;
	}

}
