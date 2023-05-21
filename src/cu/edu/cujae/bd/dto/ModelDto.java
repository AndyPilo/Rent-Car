package cu.edu.cujae.bd.dto;

public class ModelDto {
	private int codModel;
	private String nameModel;
	private BrandDto brand;
	
	public ModelDto(String nameModel, BrandDto brand) {
		super();
		this.nameModel = nameModel;
		this.brand = brand;
	}
	
	public int getCodModel() {
		return codModel;
	}
	public void setCodModel(int codModel) {
		this.codModel = codModel;
	}
	public String getNameModel() {
		return nameModel;
	}
	public void setNameModel(String nameModel) {
		this.nameModel = nameModel;
	}
	public BrandDto getBrand() {
		return brand;
	}
	public void setBrand(BrandDto brand) {
		this.brand = brand;
	}
		
}
