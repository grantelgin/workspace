
/**
 * ChainedHashTable has the add, delete, search and show methods required for the table level of a chained hash table. 
 * Functions for handling the linked lists inside each bucket are in the Entry class.
 * 
 *
 */

public class ChainedHashTable {
	private static final int TABLE_SIZE = 31;

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
	public String showBucket (Entry entry) {
		String result = "";
		FilmPlaceTime current = entry.first;
		while (current.getTitle() != "") {
			result += current.getTitle();
			if (current.getNext().getTitle() != "") {
				result += " --> ";
			}
			current = current.getNext();

		}
		return result;
	}

	public boolean add(FilmPlaceTime data) {
		boolean result = false;
		int bucket = hashFunction(data.getTitle());
		if ((table[bucket].getState() == Entry.NEVER_USED) || (table[bucket].getState() == Entry.PREVIOUSLY_USED)) {
			table[bucket].setState(Entry.IN_USE);
		}
		else {
			// collision
			System.out.println("Collision! " + data.getTitle() + " collision with: " + showBucket(table[bucket]) + "\nPrepending to head of Entry list...");
		}
		data.setKey(bucket);
		result = table[bucket].insert(data, bucket);	

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
				// = table[bucket].search(bucket, title);

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
				FilmPlaceTime fpt = table[i].first;
				while (fpt != null) {
					rtn += fpt.getTitle();
					if (fpt.getNext().getTitle() != "") {
						fpt = fpt.getNext();
						rtn += " --> ";
					}
					else {
						fpt = null;
						rtn += "\n";
					}
				}
			}
		}

		return rtn;
	}

}
