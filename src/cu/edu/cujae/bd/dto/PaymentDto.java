package cu.edu.cujae.bd.dto;

public class PaymentDto {
	private int codPayment;
	private String payment;
	
	
	public PaymentDto( String namePayment) {
		super();
		this.payment = namePayment;
	}


	public int getCodPayment() {
		return codPayment;
	}


	public void setCodPayment(int codPayment) {
		this.codPayment = codPayment;
	}


	public String getPayment() {
		return payment;
	}


	public void setPayment(String namePayment) {
		this.payment = namePayment;
	}
	
	
}
