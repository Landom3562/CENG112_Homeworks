
public class Playlist {
	public int[] Tracks;
	public int Total_popularity;
	public int Size;
	public int Count;
	public int Total_duration;
	public int Acoustic_duration;
	public int Instrumental_duration;
	public int Rock_duration;
	public int Rap_duration;
	public int Jazz_duration;
	public int Pop_duration;
	
	public Playlist(int choice, Music Acoustic, Music Instrumental, Music Rock, Music Rap, Music Jazz, Music Pop) {
		Size = 50;
		Count = 0;
		Tracks = new int[Size];
		switch(choice) {
		case 1:
			while(Instrumental.Count != 0 && Total_duration < 2700) {
				Instrumental_duration += Instrumental.Track_duration[0];
				addToPlist_removeFromBag(Instrumental);
			}
			while(Acoustic.Count != 0 && Total_duration < 2700) {
				Acoustic_duration += Acoustic.Track_duration[0];
				addToPlist_removeFromBag(Acoustic);
			}
			while(Jazz.Count != 0 && Total_duration < 2700) {
				Jazz_duration += Jazz.Track_duration[0];
				addToPlist_removeFromBag(Jazz);
			}
			break;
		case 2:
			while(Rap.Count != 0 && Total_duration < 3600) {
				Rap_duration += Rap.Track_duration[0];
				addToPlist_removeFromBag(Rap);
			}
			while(Rock.Count != 0 && Total_duration < 3600) {
				Rock_duration += Rock.Track_duration[0];
				addToPlist_removeFromBag(Rock);
			}
			while(Pop.Count != 0 && Total_duration < 3600) {
				Pop_duration += Pop.Track_duration[0];
				addToPlist_removeFromBag(Pop);
			}
			break;
		case 3:
			while(Jazz.Count != 0 && Total_duration < 5400) {
				Jazz_duration += Jazz.Track_duration[0];
				addToPlist_removeFromBag(Jazz);
			}
			while(Acoustic.Count != 0 && Total_duration < 5400) {
				Acoustic_duration += Acoustic.Track_duration[0];
				addToPlist_removeFromBag(Acoustic);
			}
			while(Instrumental.Count != 0 && Total_duration < 5400) {
				Instrumental_duration += Instrumental.Track_duration[0];
				addToPlist_removeFromBag(Instrumental);
			}
			break;
		case  4:
			while(Instrumental.Count != 0 && Total_duration < 7200) {
				Instrumental_duration += Instrumental.Track_duration[0];
				addToPlist_removeFromBag(Instrumental);
			}
			while(Acoustic.Count != 0 && Total_duration < 7200) {
				Acoustic_duration += Acoustic.Track_duration[0];
				addToPlist_removeFromBag(Acoustic);
			}
			while(Jazz.Count != 0 && Total_duration < 7200) {
				Jazz_duration += Jazz.Track_duration[0];
				addToPlist_removeFromBag(Jazz);
			}
			break;
		case  5:
			while(Rock.Count != 0 && Total_duration < 10800) {
				Rock_duration += Rock.Track_duration[0];
				addToPlist_removeFromBag(Rock);
			}
			while(Pop.Count != 0 && Total_duration < 10800) {
				Pop_duration += Pop.Track_duration[0];
				addToPlist_removeFromBag(Pop);
			}
			while(Acoustic.Count != 0 && Total_duration < 10800) {
				Acoustic_duration += Acoustic.Track_duration[0];
				addToPlist_removeFromBag(Acoustic);
			}
			break;
		}
	}
	public void addToPlist_removeFromBag(Music genre) {
		if(Size > Count) {
			Tracks[Count] = genre.Track_id[0];
			Total_duration += genre.Track_duration[0];
			Total_popularity += genre.Track_popularity[0];
			Count += 1;
		}else if(Size == Count) {
			int[] temp_id = new int[Size*2];
			for(int i = 0; i<Count; i++) {
				temp_id[i] = Tracks[i];
			}
			Tracks = temp_id;
			Size *= 2;
			Tracks[Count] = genre.Track_id[0];
			Total_duration += genre.Track_duration[0];
			Total_popularity += genre.Track_popularity[0];
			Count += 1;
		}
		genre.remove_track(genre.Track_id[0]);
	}
	public void display() {
		System.out.println("Number of tracks in the list = " + Count);
		System.out.println("Average popularity of the playlist = " + Total_popularity/Count);
		System.out.println("Total duration of Acoustic songs = "+ Acoustic_duration/3600 + " hours " + (Acoustic_duration%3600)/60 + " minutes " + Acoustic_duration%60 + "seconds");
		System.out.println("Total duration of Instrumental songs = "+ Instrumental_duration/3600 + " hours " + (Instrumental_duration%3600)/60 + " minutes " + Instrumental_duration%60 + "seconds");
		System.out.println("Total duration of Rock songs = "+ Rock_duration/3600 + " hours " + (Rock_duration%3600)/60 + " minutes " + Rock_duration%60 + "seconds");
		System.out.println("Total duration of Rap songs = "+ Rap_duration/3600 + " hours " + (Rap_duration%3600)/60 + " minutes " + Rap_duration%60 + "seconds");
		System.out.println("Total duration of Jazz songs = "+ Jazz_duration/3600 + " hours " + (Jazz_duration%3600)/60 + " minutes " + Jazz_duration%60 + "seconds");
		System.out.println("Total duration of Pop songs = "+ Pop_duration/3600 + " hours " + (Pop_duration%3600)/60 + " minutes " + Pop_duration%60 + "seconds");
		System.out.println("Total duration of the playlist = " + Total_duration/3600 + " hours " + (Total_duration%3600)/60 + " minutes " + Total_duration%60 + "seconds");
	}
	public void clear() {
		Size = 50;
		Count = 0;
		Tracks = new int[Size];
		Acoustic_duration = 0;
		Instrumental_duration = 0;
		Rock_duration = 0;
		Rap_duration = 0;
		Jazz_duration = 0;
		Pop_duration = 0;
		Total_duration = 0;
		Total_popularity = 0;
	}
}
