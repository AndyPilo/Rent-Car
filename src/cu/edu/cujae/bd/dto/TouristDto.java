package cu.edu.cujae.bd.dto;

public class TouristDto {
	private int codTourist;
	private String passport;
	private String name;
	private String lastName;
	private int age;
	private char sex;
	private String contact;
	private CountryDto country;
	
	
	public TouristDto(String passport, String name, String last_name, int age,
			char sex, String contact, CountryDto country) {
		super();
	
		this.passport = passport;
		this.name = name;
		this.lastName = last_name;
		this.age = age;
		this.sex = sex;
		this.contact = contact;
		this.country = country;
	}

	public TouristDto(int codTourist,String passport, String name, String last_name, int age,
			char sex, String contact, CountryDto country) {
		super();
	
		this.passport = passport;
		this.name = name;
		this.lastName = last_name;
		this.age = age;
		this.sex = sex;
		this.contact = contact;
		this.country = country;
		this.codTourist = codTourist;
	}
	
	/********************     Getters   And    Setters      **********************/

	public int getCodTourist() {
		return codTourist;
	}

	public void setCodTourist(int codTourist) {
		this.codTourist = codTourist;
	}

	public String getPassport() {
		return passport;
	}

	public void setPassport(String passport) {
		this.passport = passport;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public char getSex() {
		return sex;
	}

	public void setSex(char sex) {
		this.sex = sex;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public CountryDto getCountry() {
		return country;
	}

	public void setCountry(CountryDto country) {
		this.country = country;
	}
}
