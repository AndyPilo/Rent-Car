package cu.edu.cujae.bd.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import cu.edu.cujae.bd.dto.SituationDto;

public class SituationServices {
    
    public SituationDto getSituationById(int situationId) throws SQLException{
		SituationDto situation = null;  
		
		String function = "{?= call load_situation_by_id(?)}";
		Connection connection = ServicesLocator.getConnection();
		connection.setAutoCommit(false);
		
		CallableStatement preparedFunction = connection.prepareCall(function);
		preparedFunction.registerOutParameter(1, java.sql.Types.OTHER);
		preparedFunction.setInt(2, situationId);
		preparedFunction.execute();
		
		ResultSet resultSet = (ResultSet) preparedFunction.getObject(1);
		if(resultSet.next()){
		String nameSituation = resultSet.getString(2);
		
		situation = new SituationDto(nameSituation);
		situation.setCodSituation(situationId);
		}
		
		resultSet.close();
		preparedFunction.close();
		connection.close();
		
		return situation;
	}

}
