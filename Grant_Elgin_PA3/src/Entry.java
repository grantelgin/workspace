
public class Entry {
	public static final int NEVER_USED = -1;
	public static final int PREVIOUSLY_USED = -2;
	public static final int IN_USE = -3;

	private FilmPlaceTime data;
	private int state;
	public FilmPlaceTime first;

	public Entry() {
		data = new FilmPlaceTime();
		state = NEVER_USED;
		first = new FilmPlaceTime();
	}

	public boolean insert (FilmPlaceTime data, int hashKey) {
		FilmPlaceTime prev = new FilmPlaceTime();
		FilmPlaceTime current = first;

		data.setKey(hashKey);
		while (current.getKey() != -1 && data.getKey() > current.getKey()) {
			prev = current;
			current = current.getNext();
		}

		if (prev.getKey() == -1) 
			first = data;
		else
			prev.setNext(data);

		data.setNext(current);
		
		
		return true;
	}
	
	
	public FilmPlaceTime search(int hashKey, String title) {
		FilmPlaceTime current = first;
		
		while (current != null && current.getKey() <= hashKey) {
			if (current.getTitle().equals(title))
				return current;
			
			current = current.getNext();
		}
		
		return null;
		
	}

	public FilmPlaceTime getData() {
		return data;
	}
	public void setData(FilmPlaceTime data) {
		this.data = data;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
}
