package cu.edu.cujae.bd.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import cu.edu.cujae.bd.dto.BrandDto;
import cu.edu.cujae.bd.dto.CarDto;
import cu.edu.cujae.bd.dto.SituationDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BrandServices {

    
    public void insertBrand(BrandDto brand) throws SQLException{
        String function = "{call insert_brand(?)}";
        Connection connection = ServicesLocator.getConnection();
        CallableStatement preparedFunction = connection.prepareCall(function);
        preparedFunction.setString(1,brand.getNameBrand());
        preparedFunction.execute();
        preparedFunction.close();
        connection.close();
    }

    public void updateBrand(BrandDto brand) throws SQLException{
        String function = "{call update_brand(?,?)}";
        Connection connection = ServicesLocator.getConnection();
        CallableStatement preparedFunction = connection.prepareCall(function);
        preparedFunction.setInt(1,brand.getCodBrand());
        preparedFunction.setString(2, brand.getNameBrand());
        preparedFunction.execute();       
        preparedFunction.close();
        connection.close();
    }

    public void deleteBrand(int brandId) throws SQLException{
        String function = "{call delete_brand(?)}";
        Connection connection = ServicesLocator.getConnection();
        CallableStatement preparedFunction = connection.prepareCall(function);
        preparedFunction.setInt(1,brandId);
        preparedFunction.execute();     
        preparedFunction.close();
        connection.close();
    }

    public ObservableList<BrandDto> getAllBrand() throws SQLException{
        ObservableList<BrandDto> lista = FXCollections.observableArrayList();
		String function = "{?= call list_brands()}";

        System.out.println("Conexion de Brand");
        Connection connection = ServicesLocator.getConnection();
		connection.setAutoCommit(false);
		
		CallableStatement preparedFunction = connection.prepareCall(function);
		preparedFunction.registerOutParameter(1,java.sql.Types.OTHER);
		preparedFunction.execute();	
		ResultSet resultSet = (ResultSet) preparedFunction.getObject(1);

        while(resultSet.next()){	
			lista.add(new BrandDto(resultSet.getInt(1),
                                   resultSet.getString(2)));
		}
		
		resultSet.close();
		preparedFunction.close();
		connection.close();

        return lista;
    }
    
    public BrandDto getBrandById(int brandId) throws SQLException{
		BrandDto brand = null;  	
		String function = "{?= call load_brand_by_id(?)}";
		Connection connection = ServicesLocator.getConnection();
		connection.setAutoCommit(false);
		
		CallableStatement preparedFunction = connection.prepareCall(function);
		preparedFunction.registerOutParameter(1, java.sql.Types.OTHER);
		preparedFunction.setInt(2, brandId);
		preparedFunction.execute();
		
		ResultSet resultSet = (ResultSet) preparedFunction.getObject(1);
		if(resultSet.next()){
		String nameBrand = resultSet.getString(2);	
		brand = new BrandDto(nameBrand);
		brand.setCodBrand(brandId);
		}
		
		resultSet.close();
		preparedFunction.close();
		connection.close();
		
		return brand;
	}
}
