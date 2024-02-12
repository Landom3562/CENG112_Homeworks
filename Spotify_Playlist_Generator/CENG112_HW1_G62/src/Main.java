import java.io.File;
import java.util.Scanner;
// Deniz KAYA 280201033

public class Main {
	public static void main(String [] args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		int choice = 0;
		do {
			System.out.println("Select the type of playlist you want to create. Enter 0 if you want to quit.");
			System.out.println("1-Sleeping");
			System.out.println("2-Workout");
			System.out.println("3-Dining");
			System.out.println("4-Meditation");
			System.out.println("5-Road Trip");
			System.out.print("Your choice:");
			choice = scanner.nextInt();
			System.out.println();
			if(choice <= 0 || choice > 5) {
				break;
			}
			File file = new File("tracks.txt");
			@SuppressWarnings("resource")
			Scanner scannerFile = new Scanner(file);
			Music Acoustic = new Music(1,20);
			Music Instrumental = new Music(2,20);
			Music Rock = new Music(3,20);
			Music Rap = new Music(4,20);
			Music Jazz = new Music(5,20);
			Music Pop = new Music(6,20);
			while(scannerFile.hasNextLine()) {
				String [] Line = scannerFile.nextLine().split(",");
				int genre_id = Integer.parseInt(Line[0]);
				int track_id = Integer.parseInt(Line[1]);
				int track_duration = Integer.parseInt(Line[2]);
				int track_popularity = Integer.parseInt(Line[3]);
				switch(genre_id) {
				case 1:
					Acoustic.add_track(track_id, track_duration, track_popularity);
					break;
				case 2:
					Instrumental.add_track(track_id, track_duration, track_popularity);
					break;
				case 3:
					Rock.add_track(track_id, track_duration, track_popularity);
					break;
				case 4:
					Rap.add_track(track_id, track_duration, track_popularity);
					break;
				case 5:
					Jazz.add_track(track_id, track_duration, track_popularity);
					break;
				case 6:
					Pop.add_track(track_id, track_duration, track_popularity);
					break;
				}
	
			}
			System.out.println("Number of tracks in Acoustic bag = " + Acoustic.Count + " Total duration of tracks in Acoustic = " + Acoustic.getTotalDuration());
			System.out.println("Number of tracks in Instrumental bag = " + Instrumental.Count + " Total duration of tracks in Instrumental = " + Instrumental.getTotalDuration());
			System.out.println("Number of tracks in Rock bag = " + Rock.Count + " Total duration of tracks in Rock = " + Rock.getTotalDuration());
			System.out.println("Number of tracks in Rap bag = " + Rap.Count + " Total duration of tracks in Rap = " + Rap.getTotalDuration());
			System.out.println("Number of tracks in Jazz bag = " + Jazz.Count + " Total duration of tracks in Jazz = " + Jazz.getTotalDuration());
			System.out.println("Number of tracks in Pop bag = " + Pop.Count + " Total duration of tracks in Pop = " + Pop.getTotalDuration());
			System.out.println();
			Acoustic.popularitySort();
			Instrumental.popularitySort();
			Rock.popularitySort();
			Rap.popularitySort();
			Jazz.popularitySort();
			Pop.popularitySort();
			switch(choice) {
			case 1:
				Playlist Sleeping = new Playlist(1,Acoustic,Instrumental,Rock,Rap,Jazz,Pop);
				Sleeping.display();
				Sleeping.clear();
				break;
			case 2:
				Playlist Workout = new Playlist(2,Acoustic,Instrumental,Rock,Rap,Jazz,Pop);
				Workout.display();
				Workout.clear();
				break;
			case 3:
				Playlist Dining = new Playlist(3,Acoustic,Instrumental,Rock,Rap,Jazz,Pop);
				Dining.display();
				Dining.clear();
				break;
			case 4:
				Playlist Meditation = new Playlist(4,Acoustic,Instrumental,Rock,Rap,Jazz,Pop);
				Meditation.display();
				Meditation.clear();
				break;
			case 5:
				Playlist RoadTrip = new Playlist(5,Acoustic,Instrumental,Rock,Rap,Jazz,Pop);
				RoadTrip.display();
				RoadTrip.clear();
				break;
			}
			System.out.println();
			Acoustic.reset();
			Instrumental.reset();
			Rock.reset();
			Rap.reset();
			Jazz.reset();
			Pop.reset();
		}while(choice != 0);
		System.out.println("Goodbye!");
		scanner.close();
	}
}


























