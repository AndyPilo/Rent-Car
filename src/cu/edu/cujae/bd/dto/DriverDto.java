package cu.edu.cujae.bd.dto;

public class DriverDto {
	private int codDriver;
	private String dni;
	private String nameDriver;
	private String lastName;
	private String address;
	private DriverCategoryDto category;
	
	
	public DriverDto(String dni, String nameDriver, String lastName, String address, DriverCategoryDto category) {
		super();
		this.dni = dni;
		this.nameDriver = nameDriver;
		this.lastName = lastName;
		this.address = address;
		this.category = category;
	}
	public DriverDto(int codDriver,String dni, String nameDriver, String lastName, String address, DriverCategoryDto category) {
		super();
		this.codDriver = codDriver;
		this.dni = dni;
		this.nameDriver = nameDriver;
		this.lastName = lastName;
		this.address = address;
		this.category = category;
	}
	public DriverDto(){
		
	}

	public int getCodDriver() {
		return codDriver;
	}

	public void setCodDriver(int codDriver) {
		this.codDriver = codDriver;
	}

	public String getDni() {
		return dni;
	}


	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNameDriver() {
		return nameDriver;
	}

	public void setNameDriver(String nameDriver) {
		this.nameDriver = nameDriver;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public DriverCategoryDto getCategory() {
		return category;
	}

	public void setCategory(DriverCategoryDto categoty) {
		this.category = categoty;
	}
	
}
