package cu.edu.cujae.bd.dto;

public class CountryDto {
	private int codCountry;
	private String name;
	
	public CountryDto(int codCountry, String name) {
		super();
		this.codCountry = codCountry;
		this.name = name;
	}
	
	public CountryDto(String name){
		this.name = name;
	}

	/********************     Getters   And    Setters      **********************/
	
	public int getCodCountry() {
		return codCountry;
	}

	public void setCodCountry(int codCountry) {
		this.codCountry = codCountry;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
