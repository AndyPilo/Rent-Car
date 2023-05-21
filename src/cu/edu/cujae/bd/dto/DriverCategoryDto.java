package cu.edu.cujae.bd.dto;

public class DriverCategoryDto {
    private int codCategory;
    private String category;

    public DriverCategoryDto( String category){
        this.category = category;
    }

    public int getCodCategory() {
		return codCategory;
	}

	public void setCodCategory(int codCategory) {
		this.codCategory = codCategory;
	}

    public String getCategory() {
		return category;
	}

	public void setCodDriver(String category) {
		this.category = category;
	}

}
