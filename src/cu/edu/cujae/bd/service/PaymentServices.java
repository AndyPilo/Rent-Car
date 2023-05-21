package cu.edu.cujae.bd.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import cu.edu.cujae.bd.dto.PaymentDto;

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
}
