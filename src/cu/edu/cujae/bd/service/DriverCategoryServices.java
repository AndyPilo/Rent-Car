package cu.edu.cujae.bd.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import cu.edu.cujae.bd.dto.DriverCategoryDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DriverCategoryServices {
	
	public void insertCategory(DriverCategoryDto category) throws SQLException{
		String function ="{call insert_driver_category(?)}";
		
		Connection connection = ServicesLocator.getConnection();
		CallableStatement preparedFunction = connection.prepareCall(function);
		preparedFunction.setString(1, category.getCategory());
		
		preparedFunction.execute();

        preparedFunction.close();
        connection.close();
    }
	
    public void updateCategory(){
    	
    }
    
    public void deleteCategory(){
	
    }

     public ObservableList<DriverCategoryDto> getAllCategorys() throws SQLException {
		
		ObservableList<DriverCategoryDto> lista = FXCollections.observableArrayList();

		String function = "{?= call list_categorys()}";
		Connection connection = ServicesLocator.getConnection();
		connection.setAutoCommit(false);
		
		CallableStatement preparedFunction = connection.prepareCall(function);
		preparedFunction.registerOutParameter(1,java.sql.Types.OTHER);
		preparedFunction.execute();	
		ResultSet resultSet = (ResultSet) preparedFunction.getObject(1);
		
		while(resultSet.next()){
			int codCategory = resultSet.getInt(1);
			String category = resultSet.getString(2);

			lista.add(new DriverCategoryDto(codCategory,category));
		}

		resultSet.close();
		preparedFunction.close();
		connection.close();
		return lista;
	}
}
