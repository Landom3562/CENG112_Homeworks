
public class Noodle {
	private int netWeight = 120; //gr
	private int simmerDuration = 3; //minutes
	private String expirationDate;
	
	public Noodle(String expiration) {
		expirationDate = expiration;
	}
	
	public int getNetWeight() {
		return netWeight;
	}
	public int getSimmerDuration() {
		return simmerDuration;
	}
	public String getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}
}
