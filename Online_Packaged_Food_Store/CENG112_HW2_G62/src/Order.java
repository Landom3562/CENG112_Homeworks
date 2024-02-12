
public class Order {
	private int ID;
	private String orderDate;
	private String [] foodCategories;
	
	public Order(int ID,String orderDate,String[] foodCategories) {
		this.ID = ID;
		this.orderDate = orderDate;
		this.foodCategories = foodCategories;
	}
	
	public int getID() {
		return ID;
	}
	
	public String getOrderDate() {
		return orderDate;
	}
	
	public String[] getFoodCategories() {
		return foodCategories;
	}
}
