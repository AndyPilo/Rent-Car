package cu.edu.cujae.bd.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import cu.edu.cujae.bd.dto.PaymentDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PaymentServices {
	
    public void insertPayment(PaymentDto payment) throws SQLException{
    	String function ="{call insert_payment(?)}";
		
		Connection connection = ServicesLocator.getConnection();
		CallableStatement preparedFunction = connection.prepareCall(function);
		preparedFunction.setString(1,payment.getPayment());
		
		preparedFunction.execute();

        preparedFunction.close();
        connection.close();
    }
    
    public void updatePayment(PaymentDto payment)throws SQLException{
    	String function ="{call update_payment(?,?)}";
		
		Connection connection = ServicesLocator.getConnection();
		CallableStatement preparedFunction = connection.prepareCall(function);
		preparedFunction.setInt(1,payment.getCodPayment());
		preparedFunction.setString(2,payment.getPayment());
		
		preparedFunction.execute();

        preparedFunction.close();
        connection.close();
    }
    
    public void deletePayment(int paymentId)throws SQLException{
    	String function ="{call delete_payment(?)}";
		
		Connection connection = ServicesLocator.getConnection();
		CallableStatement preparedFunction = connection.prepareCall(function);
		preparedFunction.setInt(1,paymentId);
		
		preparedFunction.execute();

        preparedFunction.close();
        connection.close();
    }

	public ObservableList<PaymentDto> getAllPayment() throws SQLException{
        ObservableList<PaymentDto> lista = FXCollections.observableArrayList();
		String function = "{?= call list_payments()}";

        Connection connection = ServicesLocator.getConnection();
		connection.setAutoCommit(false);
		
		CallableStatement preparedFunction = connection.prepareCall(function);
		preparedFunction.registerOutParameter(1,java.sql.Types.OTHER);
		preparedFunction.execute();	
		ResultSet resultSet = (ResultSet) preparedFunction.getObject(1);

        while(resultSet.next()){	
			lista.add(new PaymentDto(resultSet.getInt(1),
                                   resultSet.getString(2)));
		}
		
		resultSet.close();
		preparedFunction.close();
		connection.close();

        return lista;
    }
}
