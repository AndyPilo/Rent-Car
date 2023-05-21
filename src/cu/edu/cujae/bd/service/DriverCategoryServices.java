package cu.edu.cujae.bd.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import cu.edu.cujae.bd.dto.DriverCategoryDto;

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
}
