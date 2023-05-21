package cu.edu.cujae.bd.dto;

import java.sql.Date;

public class ContractDto {
	private int codContract;
	private TouristDto tourist;
	private CarDto car;
	private DriverDto driver;
	private PaymentDto paymentType;
	private int bill;
	private int billSpecial;
	private int extension;
	private Date startDate;
	private Date finalDate;
	
	
	public ContractDto(TouristDto tourist, CarDto car,
			Date starDate, Date finalDate, int extension, int bill,
			int billSpecial, DriverDto driver, PaymentDto paymentType) {
		super();
		this.tourist = tourist;
		this.car = car;
		this.startDate = starDate;
		this.finalDate = finalDate;
		this.extension = extension;
		this.bill = bill;
		this.billSpecial = billSpecial;
		this.driver = driver;
		this.paymentType = paymentType;
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
	public Date getStarDate() {
		return startDate;
	}
	public void setStarDate(Date starDate) {
		this.startDate = starDate;
	}
	public Date getFinalDate() {
		return finalDate;
	}
	public void setFinalDate(Date finalDate) {
		this.finalDate = finalDate;
	}
	public int getExtension() {
		return extension;
	}
	public void setExtension(int extension) {
		this.extension = extension;
	}
	public int getBill() {
		return bill;
	}
	public void setBill(int bill) {
		this.bill = bill;
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
	
	
}
