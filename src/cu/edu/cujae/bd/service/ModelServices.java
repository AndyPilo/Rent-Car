package cu.edu.cujae.bd.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import cu.edu.cujae.bd.dto.BrandDto;
import cu.edu.cujae.bd.dto.ModelDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ModelServices {

	private BrandServices brandServices = ServicesLocator.getBrandServices();

    public void insertModel(ModelDto model) throws SQLException{
        String function ="{call insert_model(?,?)}";
		
		Connection connection = ServicesLocator.getConnection();
		CallableStatement preparedFunction = connection.prepareCall(function);
        preparedFunction.setString(1,model.getNameModel());
        preparedFunction.setInt(2,model.getBrand().getCodBrand());

        preparedFunction.execute();

        preparedFunction.close();
        connection.close();
    }

    public void updateModel(ModelDto model) throws SQLException{
        String function ="{call update_model(?,?)}";
		
		Connection connection = ServicesLocator.getConnection();
		CallableStatement preparedFunction = connection.prepareCall(function);
        preparedFunction.setInt(1, model.getCodModel());
        preparedFunction.setString(2,model.getNameModel());
        preparedFunction.setInt(3,model.getBrand().getCodBrand());

        preparedFunction.execute();

        preparedFunction.close();
        connection.close();
    }

    public void deleteModel(int modelId) throws SQLException{
        String function ="{call delete_model(?)}";
		
		Connection connection = ServicesLocator.getConnection();
		CallableStatement preparedFunction = connection.prepareCall(function);
        preparedFunction.setInt(1, modelId);

        preparedFunction.execute();
        
        preparedFunction.close();
        connection.close();
    }

    public ModelDto getModelById(int modelId) throws SQLException{
		ModelDto model = null;  
		
		String function = "{?= call load_model_by_id(?)}";
		Connection connection = ServicesLocator.getConnection();
		connection.setAutoCommit(false);
		
		CallableStatement preparedFunction = connection.prepareCall(function);
		preparedFunction.registerOutParameter(1, java.sql.Types.OTHER);
		preparedFunction.setInt(2, modelId);
		preparedFunction.execute();
		
		ResultSet resultSet = (ResultSet) preparedFunction.getObject(1);
		if(resultSet.next()){
		String nameModel = resultSet.getString(2);
		int codBrand = resultSet.getInt(3);
		BrandDto brand = ServicesLocator.getBrandServices().getBrandById(codBrand);
		
		model = new ModelDto(nameModel,brand);
		model.setCodModel(modelId);
		}
		
		resultSet.close();
		preparedFunction.close();
		connection.close();
		
		return model;
	}

	public ObservableList<ModelDto> getAllModels() throws SQLException {
		ObservableList<ModelDto> lista = FXCollections.observableArrayList();
		ObservableList<BrandDto> brands = brandServices.getAllBrand();

		String function = "{?= call list_models()}";
		Connection connection = ServicesLocator.getConnection();
		connection.setAutoCommit(false);
		
		CallableStatement preparedFunction = connection.prepareCall(function);
		preparedFunction.registerOutParameter(1,java.sql.Types.OTHER);
		preparedFunction.execute();
		
		ResultSet resultSet = (ResultSet) preparedFunction.getObject(1);
		
		while(resultSet.next()){
			int codBrand = resultSet.getInt(3);
			BrandDto brand = null;

			for(int i = 0;i < brands.size();i++){
				if(codBrand == brands.get(i).getCodBrand()){
					 brand = brands.get(i);
				}
			}

			lista.add(new ModelDto(resultSet.getInt(1),resultSet.getString(2),brand));
		}
		
		resultSet.close();
		preparedFunction.close();
		connection.close();
		
		return lista;
	}
    
}
