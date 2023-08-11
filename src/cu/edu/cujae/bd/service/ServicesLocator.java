package cu.edu.cujae.bd.service;

import java.sql.SQLException;

import cu.edu.cujae.bd.utils.Connection;

public class ServicesLocator {
	
	private static TouristServices touristServices = null;
	private static BrandServices brandServices = null;
	private static ModelServices modelServices = null;
	private static CountryServices countryServices = null;
	private static DriverServices driverServices = null;
	private static CarServices carServices = null;
	private static PaymentServices paymentServices = null;
	private static ContractServices contractServices = null;
	private static DriverCategoryServices driverCategoryServices = null;
	private static UserServices userServices = null;
	private static SituationServices situationServices = null;
	private static RolServices rolServices = null;
	private static DateServices dateServices = null;
	
	public static java.sql.Connection getConnection(){

		Connection connection = null;
		
		try {
			connection = new Connection("localhost", "BD_rentalCar", "postgres", "postgres");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection.getConnection();
	}

	public static TouristServices getTouristServices(){
		if(touristServices == null){
			touristServices = new TouristServices();
		}
		return touristServices;
	}

	public static CarServices getCarServices(){
		if(carServices == null){
			carServices = new CarServices();
		}
		return carServices;
	}
	
	public static BrandServices getBrandServices(){
		if(brandServices == null){
			brandServices = new BrandServices();
		}
		return brandServices;
	}

	public static ModelServices getModelServices(){
		if(modelServices == null){
			modelServices = new ModelServices();
		}
		return modelServices;
	}

	public static DriverServices getDriverServices(){
		if(driverServices == null){
			driverServices = new DriverServices();
		}
		return driverServices;
	}
	
	public static CountryServices getCountryServices(){
		if(countryServices == null){
			countryServices = new CountryServices();
		}
		return countryServices;
	}
	
	public static PaymentServices getPaymentServices(){
		if(paymentServices == null){
			paymentServices = new PaymentServices();
		}
		return paymentServices;
	}
	
	public static ContractServices getContractServices(){
		if(contractServices == null){
			contractServices = new ContractServices();
		}
		return contractServices;
	}
	
	public static DriverCategoryServices getDriverCategoryServices(){
		if(driverCategoryServices == null){
			driverCategoryServices = new DriverCategoryServices();
		}
		return driverCategoryServices;
	}
	
	public static UserServices getUserServices(){
		if(userServices == null){
			userServices = new UserServices();
		}
		return userServices;
	}

	public static SituationServices getSituationServices(){
		if(situationServices == null){
			situationServices = new SituationServices();
		}
		return situationServices;
	}

	public static RolServices getRolServices(){
		if(rolServices == null){
			rolServices = new RolServices();
		}
		return rolServices;
	}

	public static DateServices getDateServices(){
		if(dateServices == null){
			dateServices = new DateServices();
		}
		return dateServices;
	}
}
