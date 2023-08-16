package cu.edu.cujae.bd.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import cu.edu.cujae.bd.dto.CarDto;
import cu.edu.cujae.bd.dto.ContractDto;
import cu.edu.cujae.bd.dto.DateDto;
import cu.edu.cujae.bd.dto.DriverDto;
import cu.edu.cujae.bd.dto.PaymentDto;
import cu.edu.cujae.bd.dto.TouristDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ContractServices {
	
	public void insertContract(ContractDto contract)throws SQLException{
		String function ="{call insert_contract(?,?,?,?,?,?,?,?)}";
			
		Connection connection = ServicesLocator.getConnection();
		CallableStatement preparedFunction = connection.prepareCall(function);
		preparedFunction.setInt(1, contract.getTourist().getCodTourist());
		preparedFunction.setInt(2, contract.getCar().getCodCar());
		preparedFunction.setInt(4, contract.getPaymentType().getCodPayment());
		preparedFunction.setInt(5, contract.getBillSpecial());
		preparedFunction.setInt(6, contract.getExtension());
		preparedFunction.setInt(7, contract.getDate().getCodDate());
		preparedFunction.setInt(8, contract.getPriceTotal());

		if(contract.getDriver() != null){
			preparedFunction.setInt(3, contract.getDriver().getCodDriver());
		}else{
			preparedFunction.setNull(3, Types.INTEGER);
		}
		
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
		preparedFunction.setInt(6, contract.getBillSpecial());
		preparedFunction.setInt(7, contract.getExtension());
		preparedFunction.setInt(8, contract.getDate().getCodDate());
		preparedFunction.setInt(9,contract.getPriceTotal());
		
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

	public ObservableList<ContractDto> getAllContract() throws SQLException {
		
		ObservableList<ContractDto> lista = FXCollections.observableArrayList();
		ObservableList<TouristDto> tourists = ServicesLocator.getTouristServices().getAllTourist();
		ObservableList<CarDto> cars = ServicesLocator.getCarServices().getAllCars();
		ObservableList<DriverDto> drivers = ServicesLocator.getDriverServices().getAllDrivers();
		ObservableList<PaymentDto> payments = ServicesLocator.getPaymentServices().getAllPayment();
		ObservableList<DateDto> dates = ServicesLocator.getDateServices().getAllDate();

		String function = "{?= call list_contracts()}";
		Connection connection = ServicesLocator.getConnection();
		connection.setAutoCommit(false);
		
		CallableStatement preparedFunction = connection.prepareCall(function);
		preparedFunction.registerOutParameter(1,java.sql.Types.OTHER);
		preparedFunction.execute();	
		ResultSet resultSet = (ResultSet) preparedFunction.getObject(1);
		
		while(resultSet.next()){
			int codContract = resultSet.getInt(1);
			int billSpecial = resultSet.getInt(6);
			int extension = resultSet.getInt(7);
			int priceTotal = resultSet.getInt(9);

			int codTourist = resultSet.getInt(2);
			TouristDto touristDto = null;
			int codCar = resultSet.getInt(3);
			CarDto carDto = null;
			int codDriver = resultSet.getInt(4);
			DriverDto driverDto = null;
			int codPayment = resultSet.getInt(5);
			PaymentDto paymentDto = null;
			int codDate = resultSet.getInt(8);
			DateDto dateDto = null;
	

			for(int i = 0;i < tourists.size();i++){
				if(codTourist == tourists.get(i).getCodTourist()){
					 touristDto = tourists.get(i);
				}
			}

			for(int i = 0;i < cars.size();i++){
				if(codCar == cars.get(i).getCodCar()){
					carDto = cars.get(i);
				}
			}

			for(int i = 0;i < drivers.size();i++){
				if(codDriver == drivers.get(i).getCodDriver()){
					driverDto = drivers.get(i);
				}
			}

			for(int i = 0;i < payments.size();i++){
				if(codPayment == payments.get(i).getCodPayment()){
					paymentDto = payments.get(i);
				}
			}
			
			for(int i = 0;i < dates.size();i++){
				if(codDate == dates.get(i).getCodDate()){
					dateDto = dates.get(i);
				}
			}

			lista.add(new ContractDto(codContract,touristDto,carDto,driverDto,paymentDto,billSpecial,extension,dateDto,priceTotal));
		}

		resultSet.close();
		preparedFunction.close();
		connection.close();
		return lista;
	}
}
