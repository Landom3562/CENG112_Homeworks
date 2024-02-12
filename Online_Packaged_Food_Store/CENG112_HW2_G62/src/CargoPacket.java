
public class CargoPacket {
	private int cargoID;
	private String processDate;
	private String[] foodArray;
	private String[] expirationArray;
	
	
	public CargoPacket() {
		
	}
	
	public CargoPacket(int cargoID, String processDate, String[] foodArray, String[] expirationArray) {
		this.cargoID = cargoID;
		this.processDate = processDate;
		this.foodArray = foodArray;
		this.expirationArray = expirationArray;
	}
	
	public int getCargoID() {
		return cargoID;
	}
	
	public String getProcessDate() {
		return processDate;
	}
	
	
	public String[] getFoodArray() {
		return foodArray;
	}
	public String[] getexpirationArray() {
		return expirationArray;
	}
}
