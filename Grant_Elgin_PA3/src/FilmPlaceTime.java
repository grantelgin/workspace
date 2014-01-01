
/**
 * The FilmPlaceTime class has the properties required for storing this object in both an OpenHashTable and a ChainedHashTable.
 * FilmPlaceTime objects store data about historical video footage.
 *
 */


public class FilmPlaceTime {
	private String title;
	private String place;
	private String time;
	private int key;
	private FilmPlaceTime next;
	
	public FilmPlaceTime () {
		setTitle("");
		setPlace("");
		setTime("");
		setNext(null);
		setKey(-1);
	}
	
	public String toString() {
		return "Title: " + title + "\nPlace: " + place + "\nTime: " + time + "\n";
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public FilmPlaceTime getNext() {
		return next;
	}

	public void setNext(FilmPlaceTime next) {
		this.next = next;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}
	
	
}
