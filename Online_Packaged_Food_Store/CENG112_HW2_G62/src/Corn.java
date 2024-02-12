
public class Corn {
	private int netWeight = 220; //gr
	private int drainedWeight = 132; //gr
	private String productionCountry = "turkey";
	private String expirationDate; //month-year
	
	public Corn(String expiration) {
		expirationDate = expiration;
	}
	
	public int getNetWeight() {
		return netWeight;
	}
	public int getDrainedWeight() {
		return drainedWeight;
	}
	public String getProductionCountry() {
		return productionCountry;
	}
	public String getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}
}
