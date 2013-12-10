
public class OpenHashTable {
	private static final int TABLE_SIZE = 31;

	private Entry table[];

	public OpenHashTable() {
		table = new Entry[TABLE_SIZE];

		for (int i = 0; i < table.length; i++) {
			table[i] = new Entry();
		}
	}

	private int hashFunction(String title) {

		int checkSum = 0;
		for (int i = 0; i < title.length(); i++) {
			checkSum += title.charAt(i);
		}
		return checkSum % TABLE_SIZE;
	}

	private int linearProbe(int bucket) {
		return (bucket + 1) % TABLE_SIZE;
	}
	
	private int quadraticProbe(int bucket, int i) {
		return (bucket + i * i) % TABLE_SIZE;
	}

	public boolean add(FilmPlaceTime data) {

		int bucket = hashFunction(data.getTitle());
		//int bucket = search(data);
		
		if ((table[bucket].getState() == Entry.NEVER_USED) || (table[bucket].getState() == Entry.PREVIOUSLY_USED)) {
			table[bucket].setData(data);
			table[bucket].setState(Entry.IN_USE);
			return true;
		} else {
			// Collision
			System.out.println("Collision! " + data.getTitle() + " collision with: " + table[bucket].getData().getTitle());
			for (int i = 0; i < TABLE_SIZE; i++) {
				//bucket = linearProbe(bucket);
				bucket = quadraticProbe(bucket, i);
				System.out.println("i = " + i + ", bucket = " + bucket);
				if ((table[bucket].getState() == Entry.NEVER_USED) || (table[bucket].getState() == Entry.PREVIOUSLY_USED)) {
					table[bucket].setData(data);
					table[bucket].setState(Entry.IN_USE);
					return true;
				} 
			}

			// Table is full
			return false;
		}


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

	public boolean delete(FilmPlaceTime data) {
		int bucket = search(data);

		if (bucket < 0) {
			return false;
		}

		table[bucket].setState(Entry.PREVIOUSLY_USED);
		return true;
	}

	public int search(FilmPlaceTime data) {
		int bucket = hashFunction(data.getTitle());

		if (table[bucket].getState() == Entry.NEVER_USED) {
			return Entry.NEVER_USED;
			//System.out.println("Bucket " + bucket + " has not yet been used. Adding to bucket " + bucket);
		}

		if (table[bucket].getData().getTitle().equals(data.getTitle())) {
			System.out.println(table[bucket].getData().getTitle() + " found in bucket " + bucket);
			return bucket;
		} else {
			// search for it
			for (int i = 0; i < TABLE_SIZE; i++) {
				bucket = linearProbe(bucket);

				if (table[bucket].getData().getTitle().equals(data.getTitle())) {
					System.out.println(table[bucket].getData().getTitle() + " found in bucket " + bucket);
					return bucket;
				} 

				// keep looking
				if (table[bucket].getState() == Entry.PREVIOUSLY_USED)
					continue;

				// Fail
				if (table[bucket].getState() == Entry.NEVER_USED) {
					return Entry.NEVER_USED;
				}
				System.out.println("Searched bucket " + bucket);
			}

			// Table is full
			return Entry.NEVER_USED;
		}

	}
	
	public FilmPlaceTime searchByTitle (String title) {
		FilmPlaceTime result = new FilmPlaceTime();
		
		int bucket = 0;
		for (int i = 0; i < TABLE_SIZE; i++) {
		if (table[i].getData().getTitle().equals(title)){
			//bucket = hashFunction(table[i].getData().getTitle());
			result = table[i].getData();
		}
		}
		
		
		
		//result = table[bucket].getData();
				
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
