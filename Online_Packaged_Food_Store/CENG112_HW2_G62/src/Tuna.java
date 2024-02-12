
public class Tuna {
	private int netWeight = 75; //gr
	private int drainedWeight = 50; //gr
	private String [] ingredients = {"tuna", "sunflower oil", "salt"};
	private String expirationDate; //month-year
	
	
	public Tuna(String expiration) {
		expirationDate = expiration;
	}
	
	public int getNetWeight() {
		return netWeight;
	}
	public int getDrainedWeight() {
		return drainedWeight;
	}
	public String[] getIngredients() {
		return ingredients;
	}
	public String getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}
}	
