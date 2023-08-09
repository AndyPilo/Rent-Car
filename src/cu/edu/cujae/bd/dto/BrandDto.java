package cu.edu.cujae.bd.dto;

import javafx.collections.ObservableList;

public class BrandDto {
	private int codBrand;
	private String nameBrand;
	private ObservableList<ModelDto> models;
	
	public BrandDto(String nameBrand){
		this.nameBrand = nameBrand;
	}
	public BrandDto(int codBrand,String nameBrand){
		this.codBrand = codBrand;
		this.nameBrand = nameBrand;
	}

	public BrandDto(){
		
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
