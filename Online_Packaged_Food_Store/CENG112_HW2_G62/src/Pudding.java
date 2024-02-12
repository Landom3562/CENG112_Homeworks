
public class Pudding {
	private int netWeight = 120; //gr
	private String flavor = "banana";
	private String expirationDate;
	
	public Pudding(String expiration) {
		expirationDate = expiration;
	}
	
	public int getNetWeight() {
		return netWeight;
	}
	public String getFlavor() {
		return flavor;
	}
	public String getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}
}
