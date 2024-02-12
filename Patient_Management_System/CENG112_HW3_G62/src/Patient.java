import java.time.LocalDateTime;

public class Patient implements Comparable<Patient> {
	public String nameSurname;
	public int age;
	public String gender;
	public String pregnancy;
	public String disability;
	public LocalDateTime date_time;
	public int priority;
	
	public Patient(String nameSurname, int age, String gender, String pregnancy, String disability, LocalDateTime date_time) {
		this.nameSurname = nameSurname;
		this.age = age;
		this.gender = gender;
		this.pregnancy = pregnancy;
		this.disability = disability;
		this.date_time = date_time;
		
		if(this.disability.equals("disabled")) {
			priority = 5;
		}else if(this.age > 65) {
			priority = 4;
		}else if(this.pregnancy.equals("preg")) {
			priority = 3;
		}else if(this.age < 18) {
			priority = 2;
		}else {
			priority = 1;
		}
	}
	public int compareTo(Patient other) {
		if(priority < other.priority) {
			return 1;
		}else if(priority > other.priority){
			return -1;
		}else if(date_time.isBefore(other.date_time)) {
			return -1;
		}else if(date_time.isAfter(other.date_time)) {
			return 1;
		}else {
			return 0;
		}
	}

	
}
