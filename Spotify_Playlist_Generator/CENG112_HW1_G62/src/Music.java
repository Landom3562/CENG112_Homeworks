
public class  Music {
	public int Genre_id;
	public int [] Track_id;
	public int [] Track_duration;
	public int [] Track_popularity;
	public int Size;
	public int Count;
	private int Total_duration;
	
	public Music(int genre_id, int size) {
		Genre_id = genre_id;
		Size = size;
		Track_id = new int[Size];
		Track_duration = new int[Size];
		Track_popularity = new int[Size];
		Count = 0;
	}
	
	public String getTotalDuration() {
		String minutes = String.valueOf(Total_duration / 60);
		String seconds = String.valueOf(Total_duration % 60);
		return minutes + " minutes " + seconds + " seconds";
	}
	
	public void reset() {
		Track_id = new int[Size];
		Track_duration = new int[Size];
		Track_popularity = new int[Size];
		Count = 0;
	}
	
	public void add_track(int track_id, int track_duration, int track_popularity) {
		if(Size > Count) {
			Track_id[Count] = track_id;
			Track_duration[Count] = track_duration;
			Track_popularity[Count] = track_popularity;
			Total_duration += Track_duration[Count];
			Count += 1;
		}else if(Size == Count) {
			int[] temp_id = new int[Size*2];
			int[] temp_duration = new int[Size*2];
			int[] temp_popularity = new int[Size*2];
			for(int i = 0; i<Count; i++) {
				temp_id[i] = Track_id[i];
				temp_duration[i] = Track_duration[i];
				temp_popularity[i] = Track_popularity[i];
			}
			Track_id = temp_id;
			Track_duration = temp_duration;
			Track_popularity = temp_popularity;
			Size *= 2;
			Track_id[Count] = track_id;
			Track_duration[Count] = track_duration;
			Track_popularity[Count] = track_popularity;
			Total_duration += Track_duration[Count];
			Count += 1;
		}
	}
	public int getIndexOf(int[] array, int value){
		int index = -1;
		for(int i = 0; i < array.length; i++) {
			if(array[i] == value) {
				index = i;
			}
		}
		return index;
	}
	public int remove_track(int track_id) {
		int removen_entry = -8; //Error code
		int index = getIndexOf(Track_id, track_id);
		if(index != -1) {
			removen_entry = Track_id[index];
			Total_duration -= Track_duration[index];
			Track_id[index] = 0;
			Track_duration[index] = 0;
			Track_popularity[index] = 0;
			for(int i = index; i < Count - 1; i++) {
				Track_id[i] = Track_id[i + 1];
				Track_duration[i] = Track_duration[i + 1];
				Track_popularity[i] = Track_popularity[i + 1];
			}
			Count -= 1;
			Track_id[Count] = 0;
			Track_duration[Count] = 0;
			Track_popularity[Count] = 0;
			
		}
		return removen_entry;
	}
	public void swapAll(int index1, int index2) {
		int temp = Track_id[index1];
		Track_id[index1] = Track_id[index2];
		Track_id[index2] = temp;
		
		temp = Track_duration[index1];
		Track_duration[index1] = Track_duration[index2];
		Track_duration[index2] = temp;
		
		temp = Track_popularity[index1];
		Track_popularity[index1] = Track_popularity[index2];
		Track_popularity[index2] = temp;
	}
	public void popularitySort() {
		for(int i = 0; i < Count - 1; i++) {
			for(int j = Count - 1; j > i; j--) {
				if(Track_popularity[j] > Track_popularity[j-1]) {
					swapAll(j,j-1);
				}
			}
		}
		
	}
	
}
