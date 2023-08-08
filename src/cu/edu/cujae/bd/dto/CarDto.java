package cu.edu.cujae.bd.dto;

public class CarDto {
	private int codCar;
	private String plate;
	private String color;
	private int km;
	private ModelDto model;
	private SituationDto situation;
	private String brand;
	
	public CarDto( String plate, String color,
			int km, ModelDto model,SituationDto situation) {
		super();
		this.plate = plate;
		this.color = color;
		this.km = km;
		this.model = model;
		this.situation = situation;
		setBrand();
		
	}
	
	public CarDto( int codCar,String plate, String color,
			int km, ModelDto model,SituationDto situation) {
		super();
		this.codCar = codCar;
		this.plate = plate;
		this.color = color;
		this.km = km;
		this.model = model;
		this.situation = situation;
		setBrand();
	}

	public CarDto(){
		
	}

	public int getCodCar() {
		return codCar;
	}

	public void setCodCar(int codCar) {
		this.codCar = codCar;
	}

	public String getPlate() {
		return plate;
	}

	public void setPlate(String plate) {
		this.plate = plate;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getKm() {
		return km;
	}

	public void setKm(int km) {
		this.km = km;
	}

	public ModelDto getModel() {
		return model;
	}

	public void setModel(ModelDto model) {
		this.model = model;
	}

	public SituationDto getSituation() {
		return situation;
	}

	public void setSituation(SituationDto situation) {
		this.situation = situation;
	}

	public String getBrand() {
		return this.brand;
	}

	public void setBrand() {
		this.brand = getModel().getBrand().getNameBrand();
	}
	
}
