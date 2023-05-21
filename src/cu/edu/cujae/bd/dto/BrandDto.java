package cu.edu.cujae.bd.dto;

public class BrandDto {
	private int codBrand;
	private String nameBrand;
	
	public BrandDto(String nameBrand){
		this.nameBrand = nameBrand;
	}

	public int getCodBrand() {
		return codBrand;
	}
	public void setCodBrand(int codBrand) {
		this.codBrand = codBrand;
	}
	public String getNameBrand() {
		return nameBrand;
	}
	public void setNameBrand(String nameBrand) {
		this.nameBrand = nameBrand;
	}

}
