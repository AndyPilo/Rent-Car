package cu.edu.cujae.bd.dto;

public class ContractDto {
	private int codContract;
	private TouristDto tourist;
	private CarDto car;
	private DriverDto driver;
	private PaymentDto paymentType;
	private int billSpecial;
	private int extension;
	private DateDto date;
	private int priceTotal;	
	
	
	public ContractDto(TouristDto tourist, CarDto car,DriverDto driver,PaymentDto paymentType,
			int billSpecial , int extension, DateDto date, int priceTotal) {
		super();
		this.tourist = tourist;
		this.car = car;
		this.driver = driver;
		this.paymentType = paymentType;
		this.billSpecial = billSpecial;	
		this.extension = extension;
		this.date = date;
		this.priceTotal = priceTotal;		
	}

	public ContractDto(int codContract,TouristDto tourist, CarDto car,DriverDto driver,PaymentDto paymentType,
			int billSpecial , int extension, DateDto date, int priceTotal) {
		super();
		this.codContract = codContract;
		this.tourist = tourist;
		this.car = car;
		this.driver = driver;
		this.paymentType = paymentType;
		this.billSpecial = billSpecial;	
		this.extension = extension;
		this.date = date;
		this.priceTotal = priceTotal;		
	}


	
	public int getCodContract() {
		return codContract;
	}
	public void setCodContract(int codContract) {
		this.codContract = codContract;
	}
	public TouristDto getTourist() {
		return tourist;
	}
	public void setTourist(TouristDto tourist) {
		this.tourist = tourist;
	}
	public CarDto getCar() {
		return car;
	}
	public void setCar(CarDto car) {
		this.car = car;
	}
	public int getExtension() {
		return extension;
	}
	public void setExtension(int extension) {
		this.extension = extension;
	}
	public int getBillSpecial() {
		return billSpecial;
	}
	public void setBillSpecial(int billSpecial) {
		this.billSpecial = billSpecial;
	}
	public DriverDto getDriver() {
		return driver;
	}
	public void setDriver(DriverDto driver) {
		this.driver = driver;
	}
	public PaymentDto getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(PaymentDto paymentType) {
		this.paymentType = paymentType;
	}

	public DateDto getDate() {
		return date;
	}

	public void setDate(DateDto date) {
		this.date = date;
	}

	public int getPriceTotal() {
		return priceTotal;
	}

	public void setPriceTotal(int priceTotal) {
		this.priceTotal = priceTotal;
	}
	
	
}
