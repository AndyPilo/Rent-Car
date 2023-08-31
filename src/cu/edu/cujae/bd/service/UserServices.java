package cu.edu.cujae.bd.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import cu.edu.cujae.bd.dto.CountryDto;
import cu.edu.cujae.bd.dto.RolDto;
import cu.edu.cujae.bd.dto.UserDto;
import cu.edu.cujae.bd.utils.Encription;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class UserServices {

    public void insertUser(UserDto user){
		String function = "{call insert_user(?,?,?)}";
		Connection connection = ServicesLocator.getConnection();
		try {
			CallableStatement prepareFunction = connection.prepareCall(function);
			prepareFunction.setString(1, user.getUsername());
			prepareFunction.setString(2, Encription.getMd5(user.getPassword()));
			prepareFunction.setInt(3, user.getRol().getCodRol());

			prepareFunction.execute();

			prepareFunction.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteUser(int userId) throws SQLException{
    	String function = "{call delete_user(?)}";
		
		Connection connection = ServicesLocator.getConnection();		
		CallableStatement preparedFunction = connection.prepareCall(function);
		preparedFunction.setInt(1, userId);
		
		preparedFunction.execute();
		
		preparedFunction.close();
		connection.close();
    }

	public UserDto getUserbyId(int id) throws SQLException {
		String function = "{? = call load_user_by_id(?)}";
		UserDto user = null;
		
		Connection connection = ServicesLocator.getConnection();
		connection.setAutoCommit(false);
		
		CallableStatement preparedFunction = connection.prepareCall(function);
		preparedFunction.registerOutParameter(1, java.sql.Types.OTHER);
		preparedFunction.setInt(2, id);
		preparedFunction.execute();
		
		ResultSet resultSet = (ResultSet) preparedFunction.getObject(1);
		if(resultSet.next()){
		
		String username = resultSet.getString(2);
		String password = resultSet.getString(3);
		RolDto rol = ServicesLocator.getRolServices().getRolbyId(resultSet.getInt(4));
		
		user = new UserDto(id,username,password,rol);
		}
		return user;
	}
	
	public ObservableList<UserDto> getAllUsers() throws SQLException {
		ObservableList<UserDto> lista = FXCollections.observableArrayList();
		String function = "{?= call list_users()}";
		Connection connection = ServicesLocator.getConnection();
		connection.setAutoCommit(false);
		
		CallableStatement preparedFunction = connection.prepareCall(function);
		preparedFunction.registerOutParameter(1,java.sql.Types.OTHER);
		preparedFunction.execute();
		
		ResultSet resultSet = (ResultSet) preparedFunction.getObject(1);
		
		while(resultSet.next()){
			RolDto rol = ServicesLocator.getRolServices().getRolbyId(resultSet.getInt(4));
			lista.add(new UserDto(resultSet.getInt(1),
								  resultSet.getString(2),
								  resultSet.getString(3),
								  rol));
		}
		resultSet.close();
		preparedFunction.close();
		connection.close();
		
		return lista;
	}
	
	public UserDto getLoginUser(String username, String password) {
		UserDto userAuth = null;

		try {
			String function = "{?= call get_login_user(?,?)}";
			Connection connection = ServicesLocator.getConnection();
			connection.setAutoCommit(false);
			
			CallableStatement preparedFunction = connection.prepareCall(function);
			preparedFunction.registerOutParameter(1,java.sql.Types.OTHER); 
			preparedFunction.setString(2, username); 
			preparedFunction.setString(3, Encription.getMd5(password)); // Encrypting password to save in database
			preparedFunction.execute();

			ResultSet resultSet = (ResultSet) preparedFunction.getObject(1);
			
			while (resultSet.next()) {
				RolDto rol = ServicesLocator.getRolServices().getRolbyId(resultSet.getInt(4));

				userAuth = new UserDto(resultSet.getInt(1), 
									resultSet.getString(2), 
									resultSet.getString(3), 
								    rol);
			}	
			
		}catch (SQLException e) {
			e.printStackTrace();
		}		
		return userAuth;
	}

	public void updateUser(UserDto user) throws SQLException{
		String function = "{call update_user(?,?,?)}";
		Connection connection = ServicesLocator.getConnection();		
		CallableStatement preparedFunction = connection.prepareCall(function);
		preparedFunction.setInt(1, user.getCodUser());
		preparedFunction.setString(2, user.getUsername());
		preparedFunction.setInt(3, user.getRol().getCodRol());
		preparedFunction.execute();
		
		preparedFunction.close();
		connection.close();	
	}
}
