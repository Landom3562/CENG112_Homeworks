import java.util.Scanner;
import java.io.File;

public class Main {
	public static void main(String[]args) throws Exception {
		//Creating piles.
		String [] expirationDates = {"Dec-24", "Nov-24", "Oct-24", "Sep-24", "Aug-24", "Jul-24", "Jun-24", "May-24", "Apr-24", "Mar-24", "Feb-24", "Jan-24", "Dec-23", "Nov-23", "Oct-23", "Sep-23", "Aug-23", "Jul-23", "Jun-23", "May-23", "Apr-23", "Mar-23", "Feb-23", "Jan-23", "Dec-22", "Nov-22", "Oct-22", "Sep-22", "Aug-22", "Jul-22"};
		StackInterface<Tuna> tunaPile = new ArrayStack<Tuna>(30);StackInterface<Corn> cornPile = new ArrayStack<Corn>(30);
		StackInterface<Pudding> puddingPile = new ArrayStack<Pudding>(30);StackInterface<Noodle> noodlePile = new ArrayStack<Noodle>(30);
		
		for(String expirationDate : expirationDates) {
			Tuna tuna = new Tuna(expirationDate);Corn corn = new Corn(expirationDate);
			Pudding pudding = new Pudding(expirationDate);Noodle noodle = new Noodle(expirationDate);
			tunaPile.push(tuna);cornPile.push(corn);puddingPile.push(pudding);noodlePile.push(noodle);
		}
		//Printing piles
		System.out.println("Expiration dates of piles before packaging(Will pop from the right side)");
		printStacks(tunaPile,cornPile,puddingPile,noodlePile);
		//Reading csv, creating orders and putting orders to the queue accordingly.
		QueueInterface<Order> waitingLine = new ArrayQueue<Order>(30);
		
		File file = new File("orders.csv");
		Scanner scanner = new Scanner(file);
		while(scanner.hasNextLine()) {
			String [] Line = scanner.nextLine().split(",");
			int ID = Integer.parseInt(Line[0]);
			String orderDate = Line[1];
			String[] foodCategories = {Line[2],Line[3],Line[4]};
			Order order = new Order(ID, orderDate, foodCategories);
			waitingLine.enqueue(order);
		}
		scanner.close();
		//Printing waiting line
		System.out.println();
		System.out.println("Waiting line of orders(Will start to dequeue from the top)");
		System.out.println();
		System.out.println("Order ID\tOrder Date\tFood Categories");
		for(Object elem: waitingLine.toArray()) {
			Order order = (Order)elem;
			System.out.println(order.getID()+"\t"+"\t"+order.getOrderDate()+"\t"+order.getFoodCategories()[0]+" "+order.getFoodCategories()[1]+" "+order.getFoodCategories()[2]);
		}
		
		//Creating cargo packet list
		ListInterface<CargoPacket> cargoList = new ArrayList<CargoPacket>(30);
		
		//Filling the list with fillers so I can easily replace their places with whatever cargo I want without sorting according to their ID's.
		CargoPacket filler = new CargoPacket();
		for(int i = 1; i <= 30; i++) {
			cargoList.add(filler);
		}
		
		for(int i = 1; i <= 30; i++) {
			Order order = waitingLine.dequeue();
			int ID = order.getID();
			if(order.getFoodCategories()[0].equals("corn")) {
				String foodArray[] = {"Corn", "Pudding", "Noodle"};
				String expirationArray[] = {cornPile.pop().getExpirationDate(),puddingPile.pop().getExpirationDate(),noodlePile.pop().getExpirationDate()};
				CargoPacket cargo = new CargoPacket(ID,order.getOrderDate(),foodArray,expirationArray);
				cargoList.replace(ID, cargo);
			}else{
				if(order.getFoodCategories()[1].equals("corn")) {
					if(order.getFoodCategories()[2].equals("pudding")) {
						String foodArray[] = {"Tuna", "Corn", "Pudding"};
						String expirationArray[] = {tunaPile.pop().getExpirationDate(),cornPile.pop().getExpirationDate(),puddingPile.pop().getExpirationDate()};
						CargoPacket cargo = new CargoPacket(ID,order.getOrderDate(),foodArray,expirationArray);
						cargoList.replace(ID, cargo);
					}else {
						String foodArray[] = {"Tuna", "Corn", "Noodle"};
						String expirationArray[] = {tunaPile.pop().getExpirationDate(),cornPile.pop().getExpirationDate(),noodlePile.pop().getExpirationDate()};
						CargoPacket cargo = new CargoPacket(ID,order.getOrderDate(),foodArray,expirationArray);
						cargoList.replace(ID, cargo);
					}
				}else {
					String foodArray[] = {"Tuna", "Pudding", "Noodle"};
					String expirationArray[] = {tunaPile.pop().getExpirationDate(),puddingPile.pop().getExpirationDate(),noodlePile.pop().getExpirationDate()};
					CargoPacket cargo = new CargoPacket(ID,order.getOrderDate(),foodArray,expirationArray);
					cargoList.replace(ID, cargo);
				}
			}
		}
		
		//Printing the final state of piles
		System.out.println("Expiration dates of piles after packaging(Will pop from the right side)");
		printStacks(tunaPile,cornPile,puddingPile,noodlePile);
		
		//Printing the initial state of the cargo packet list
		
		System.out.println();
		System.out.println("Initial list of cargo packages");
		printCargoList(cargoList);
		
		//Printing expiration dates of packaged products of the cargo packet with ID25
		
		CargoPacket id25 = cargoList.getEntry(25);
		String[] id25foodArray = id25.getFoodArray();
		String[] id25expirationArray = id25.getexpirationArray();
		
		System.out.println();
		System.out.println("Expiration dates of the packaged products of ID25 :");
		System.out.println(id25foodArray[0]+":"+id25expirationArray[0]+","+id25foodArray[1]+":"+id25expirationArray[1]+","+id25foodArray[2]+":"+id25expirationArray[2]);
		
		//Printing the state of cargo list after removing ID20
		
		System.out.println();
		System.out.println("State of the list after removing ID20");
		cargoList.remove(20);
		printCargoList(cargoList);
		
	}
	public static void printStacks(StackInterface<Tuna> tunaPile,StackInterface<Corn> cornPile,StackInterface<Pudding> puddingPile,StackInterface<Noodle> noodlePile) {
		System.out.println("Tuna piles:");
		for(Object elem: tunaPile.toArray()) {
			Tuna Tuna = (Tuna) elem;
			System.out.print(Tuna.getExpirationDate());
			System.out.print(" ");
		}
		System.out.println();
		System.out.println("Corn piles:");
		for(Object elem: cornPile.toArray()) {
			Corn Corn = (Corn) elem;
			System.out.print(Corn.getExpirationDate());
			System.out.print(" ");
		}
		System.out.println();
		System.out.println("Pudding piles:");
		for(Object elem: puddingPile.toArray()) {
			Pudding Pudding = (Pudding) elem;
			System.out.print(Pudding.getExpirationDate());
			System.out.print(" ");
		}
		System.out.println();
		System.out.println("Noodle piles:");
		for(Object elem: noodlePile.toArray()) {
			Noodle Noodle = (Noodle) elem;
			System.out.print(Noodle.getExpirationDate());
			System.out.print(" ");
		}
		System.out.println();
	}
	public static void printCargoList(ListInterface<CargoPacket> cargoList) {
		System.out.println("Cargo ID\tProcess Date\tPackaged Food Products");
		for(Object elem: cargoList.toArray()) {
			CargoPacket cargo = (CargoPacket)elem;
			System.out.println(cargo.getCargoID()+"\t"+"\t"+cargo.getProcessDate()+"\t"+cargo.getFoodArray()[0]+" "+cargo.getFoodArray()[1]+ " " + cargo.getFoodArray()[2]);
		}
	}
}