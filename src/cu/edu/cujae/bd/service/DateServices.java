package cu.edu.cujae.bd.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import cu.edu.cujae.bd.dto.BrandDto;
import cu.edu.cujae.bd.dto.DateDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DateServices {

    public void insertDate(DateDto date) throws SQLException{
        String function = "{call insert_date(?,?)}";
        Connection connection = ServicesLocator.getConnection();
        CallableStatement preparedFunction = connection.prepareCall(function);
        preparedFunction.setDate(1,date.getStarDate());
        preparedFunction.setDate(2, date.getFinalDate());
        preparedFunction.execute();
        preparedFunction.close();
        connection.close();
    }

    public void updateDate(DateDto date) throws SQLException{
        String function = "{call update_date(?,?,?)}";
        Connection connection = ServicesLocator.getConnection();
        CallableStatement preparedFunction = connection.prepareCall(function);
        preparedFunction.setInt(1,date.getCodDate());
        preparedFunction.setDate(2, date.getStarDate());
        preparedFunction.setDate(2, date.getFinalDate());
        preparedFunction.execute();       
        preparedFunction.close();
        connection.close();
    }

    public void deleteDate(int dateId) throws SQLException{
        String function = "{call delete_date(?)}";
        Connection connection = ServicesLocator.getConnection();
        CallableStatement preparedFunction = connection.prepareCall(function);
        preparedFunction.setInt(1,dateId);
        preparedFunction.execute();     
        preparedFunction.close();
        connection.close();
    }

    public ObservableList<DateDto> getAllDate() throws SQLException{
        ObservableList<DateDto> lista = FXCollections.observableArrayList();
		String function = "{?= call list_dates()}";

        Connection connection = ServicesLocator.getConnection();
		connection.setAutoCommit(false);
		
		CallableStatement preparedFunction = connection.prepareCall(function);
		preparedFunction.registerOutParameter(1,java.sql.Types.OTHER);
		preparedFunction.execute();	
		ResultSet resultSet = (ResultSet) preparedFunction.getObject(1);

        while(resultSet.next()){	
			lista.add(new DateDto(resultSet.getInt(1),
                                   resultSet.getDate(2),
                                   resultSet.getDate(3)));
		}
		
		resultSet.close();
		preparedFunction.close();
		connection.close();

        return lista;
    }

    public DateDto getLastDate() throws SQLException{
		DateDto date = null;  	
		String function = "{?= call ultima_fecha()}";
		Connection connection = ServicesLocator.getConnection();
		connection.setAutoCommit(false);
		
		CallableStatement preparedFunction = connection.prepareCall(function);
		preparedFunction.registerOutParameter(1, java.sql.Types.OTHER);
		preparedFunction.execute();
		
		ResultSet resultSet = (ResultSet) preparedFunction.getObject(1);
		if(resultSet.next()){
		int codFecha = resultSet.getInt(1);	
		Date startDate = resultSet.getDate(2);
		Date finalDate = resultSet.getDate(3);

        date = new DateDto(codFecha, startDate, finalDate);
		}
        	
		resultSet.close();
		preparedFunction.close();
		connection.close();
		
		return date;
	}

    

}
