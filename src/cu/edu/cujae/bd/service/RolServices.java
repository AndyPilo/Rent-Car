package cu.edu.cujae.bd.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import cu.edu.cujae.bd.dto.RolDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class RolServices {
    public RolDto getRolbyId(int id) throws SQLException {
		String function = "{? = call load_rol_by_id(?)}";
		RolDto rol = null;
		
		Connection connection = ServicesLocator.getConnection();
		connection.setAutoCommit(false);
		
		CallableStatement preparedFunction = connection.prepareCall(function);
		preparedFunction.registerOutParameter(1, java.sql.Types.OTHER);
		preparedFunction.setInt(2, id);
		preparedFunction.execute();
		
		ResultSet resultSet = (ResultSet) preparedFunction.getObject(1);
		if(resultSet.next()){

		rol = new RolDto(resultSet.getInt(1),resultSet.getString(2));

		}
		return rol;
	}

	public ObservableList<RolDto> getAllRol() throws SQLException {
		ObservableList<RolDto> lista = FXCollections.observableArrayList();

		String function = "{?= call list_rol()}";
		Connection connection = ServicesLocator.getConnection();
		connection.setAutoCommit(false);
		
		CallableStatement preparedFunction = connection.prepareCall(function);
		preparedFunction.registerOutParameter(1,java.sql.Types.OTHER);
		preparedFunction.execute();
		
		ResultSet resultSet = (ResultSet) preparedFunction.getObject(1);
		
		while(resultSet.next()){	
			lista.add(new RolDto(resultSet.getInt(1),
										resultSet.getString(2)));
		}
		
		resultSet.close();
		preparedFunction.close();
		connection.close();
		
		return lista;
	}
}
