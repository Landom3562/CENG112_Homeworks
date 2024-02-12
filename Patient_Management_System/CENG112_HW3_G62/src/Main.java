import java.time.LocalDateTime;
import java.io.File;
import java.util.Scanner;

public class Main {
	public static void main(String[]args) throws Exception {
		//Reading the records.txt document.
		File file = new File("records.txt");
		Scanner scanner = new Scanner(file);
		SortedListInterface<Patient> patientList = new LinkedSortedList<Patient>();
		
		PriorityQueueInterface<Patient> waitingLine = new LinkedPriorityQueue<Patient>();
		
		while(scanner.hasNextLine()) {
			String[] Line = scanner.nextLine().split(",");
			String name_surname = Line[1];
			int age = Integer.parseInt(Line[2]);
			String gender = Line[3];
			String pregnancy = Line[4];
			String disability = Line[5];
			String[] date = Line[6].split("/");
			String[] time = Line[7].split(":");
			LocalDateTime date_time = LocalDateTime.of(Integer.parseInt(date[2]), Integer.parseInt(date[1]), Integer.parseInt(date[0]), Integer.parseInt(time[0]), Integer.parseInt(time[1]));
			Patient patient = new Patient(name_surname,age,gender,pregnancy,disability,date_time);
			patientList.add(patient);
			waitingLine.enqueue(patient);
		}
		scanner.close();
		
		//Printing the sorted list.
		System.out.println("List of patients that has been sorted according to their appointment times.");
		printPatients(patientList.toArray(),0);
		
		//Printing the waiting line.
		System.out.println("Waiting line of patients according to their priorities");
		printPatients(waitingLine.toArray(),0);
		
		//Printing the state of the waiting line after every 5 treatments.
		System.out.println("Printing the state of the waiting line after every 5 treatments.");
		int counter = 5;
		@SuppressWarnings("rawtypes")
		Comparable[] array = waitingLine.toArray();
		while(!waitingLine.isEmpty()) {
			for(int i = 0; i < 5; i++) {
				waitingLine.dequeue();
			}
			if(waitingLine.isEmpty()) {
				System.out.println("Final patient that received treatment");
				printPatients(array,counter-1);
				break;
			}
			printPatients(array,counter);
			counter += 5;
		}
	}
	@SuppressWarnings("rawtypes")
	public static void printPatients(Comparable[] array, int start) {
		System.out.println();
		System.out.println("Name Surname\tAge\tGender\tPregnancy\tDisabillity\tAppointment Time");
		for(int i = start; i < array.length; i++) {
			Patient patient = (Patient) array[i];
			if(patient.pregnancy.equals("not-preg")) {//There were a disorder in the print statements when pregnancy=not-preg.
				System.out.println(patient.nameSurname+"\t"+patient.age+"\t"+patient.gender+"\t"+patient.pregnancy+"\t"+patient.disability+"\t"+patient.date_time);
			}else {
				System.out.println(patient.nameSurname+"\t"+patient.age+"\t"+patient.gender+"\t"+patient.pregnancy+"\t\t"+patient.disability+"\t"+patient.date_time);
			}
		}
		System.out.println();
	}
}
