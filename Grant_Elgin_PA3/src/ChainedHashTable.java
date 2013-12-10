
public class ChainedHashTable {
	private static final int TABLE_SIZE = 31;
	private FilmPlaceTime firstFPT = null;

	private Entry table[];

	public ChainedHashTable () {
		table = new Entry[TABLE_SIZE];

		for (int i = 0; i < table.length; i++) {
			table[i] = new Entry();
		}
	}

	public int hashFunction(String title) {
		int hashKey = 0;

		for (int i = 0; i < title.length(); i++) {
			hashKey = (hashKey + title.charAt(i)) % TABLE_SIZE;
		}

		return hashKey;
	}

	public boolean add(FilmPlaceTime data) {
		boolean result = false;
		int bucket = hashFunction(data.getTitle());
		data.setKey(bucket);
		table[bucket].insert(data, bucket);

		return result;
	}

	
	
	public boolean quickAdd (String title, String place, String time) {

		boolean result = false;
		
		FilmPlaceTime newFilm = new FilmPlaceTime();
		newFilm.setPlace(place);
		newFilm.setTime(time);
		newFilm.setTitle(title);
		
		result = add(newFilm);

		return result;
	}

	public FilmPlaceTime searchByTitle (String title) {
		FilmPlaceTime result = new FilmPlaceTime();

		int bucket = hashFunction(title);

		result = table[bucket].search(bucket, title);

		return result;

	}
	
	public String toString() {
		String rtn = "";

		for (int i = 0; i < TABLE_SIZE; i++) {
			rtn += "table[" + i + "] = ";
			if (table[i].getState() == Entry.NEVER_USED) {
				rtn += "NEVER_USED\n";
			} else if (table[i].getState() == Entry.PREVIOUSLY_USED) {
				rtn += "PREVIOUSLY_USED\n";
			} else {
				rtn += table[i].getData().getTitle() + "\n";
			}
		}


		return rtn;
	}



}
