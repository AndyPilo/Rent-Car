package cu.edu.cujae.bd.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import cu.edu.cujae.bd.dto.ContractDto;

public class ContractServices {
	
	public void insertContract(ContractDto contract)throws SQLException{
		String function ="{call insert_contract(?,?,?,?,?,?,?,?,?)}";
			
		Connection connection = ServicesLocator.getConnection();
		CallableStatement preparedFunction = connection.prepareCall(function);
		preparedFunction.setInt(1, contract.getTourist().getCodTourist());
		preparedFunction.setInt(2, contract.getCar().getCodCar());
		preparedFunction.setInt(3, contract.getDriver().getCodDriver());
		preparedFunction.setInt(4, contract.getPaymentType().getCodPayment());
		preparedFunction.setInt(5, contract.getBill());
		preparedFunction.setInt(6, contract.getBillSpecial());
		preparedFunction.setInt(7, contract.getExtension());
		preparedFunction.setDate(8, contract.getStarDate());
		preparedFunction.setDate(9,contract.getFinalDate());
		
		preparedFunction.execute();

	    preparedFunction.close();
	    connection.close();
    }
	
    public void updateContract(ContractDto contract)throws SQLException{
    	String function ="{call update_contract(?,?,?,?,?,?,?,?,?,?)}";
 		
 		Connection connection = ServicesLocator.getConnection();
 		CallableStatement preparedFunction = connection.prepareCall(function);
 		preparedFunction.setInt(1, contract.getCodContract());
 		preparedFunction.setInt(2, contract.getTourist().getCodTourist());
		preparedFunction.setInt(3, contract.getCar().getCodCar());
		preparedFunction.setInt(4, contract.getDriver().getCodDriver());
		preparedFunction.setInt(5, contract.getPaymentType().getCodPayment());
		preparedFunction.setInt(6, contract.getBill());
		preparedFunction.setInt(7, contract.getBillSpecial());
		preparedFunction.setInt(8, contract.getExtension());
		preparedFunction.setDate(9, contract.getStarDate());
		preparedFunction.setDate(10,contract.getFinalDate());
		
		preparedFunction.execute();

	    preparedFunction.close();
	    connection.close();
    }
    
    public void deleteContract(int contractId) throws SQLException{
    	String function ="{call delete_contract(?)}";
 		
 		Connection connection = ServicesLocator.getConnection();
 		CallableStatement preparedFunction = connection.prepareCall(function);
 		preparedFunction.setInt(1, contractId);
 		
 		preparedFunction.execute();

	    preparedFunction.close();
	    connection.close();
    }
}
