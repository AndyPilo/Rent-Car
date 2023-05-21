package cu.edu.cujae.bd.dto;

public class SituationDto {
	private int codSituation;
	private String situation;
	
	public SituationDto(int codSituation, String situation) {
		super();
		this.codSituation = codSituation;
		this.situation = situation;
	}

	public SituationDto( String situation) {
		super();
		this.situation = situation;
	}
	
	public int getCodSituation() {
		return codSituation;
	}
	public void setCodSituation(int codSituation) {
		this.codSituation = codSituation;
	}
	public String getSituation() {
		return situation;
	}
	public void setSituation(String situation) {
		this.situation = situation;
	}
	
	
}
