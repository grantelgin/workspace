
public class OpenHashTable {
	
	private static final int NEVER_USED = -1;
	private static final int PREVIOUSLY_USED = -2;
	private static final int TABLE_SIZE = 31;
	
	private Entry table[];
	
	public OpenHashTable() {
		table = new Entry[TABLE_SIZE];
		
		for (int i = 0; i < table.length; i++) {
			table[i] = new Entry();
		}
	}
	
	private int hashFunction(String value) {
		
		int checkSum = 0;
		for (int i = 0; i < value.length(); i++) {
			checkSum += value.charAt(i);
		}
		return checkSum % TABLE_SIZE;
	}
	
	private int linearProbe(int bucket) {
		return (bucket + 1) % TABLE_SIZE;
	}
	
	public boolean add(String value) {

		int bucket = hashFunction(value);

		if ((table[bucket].getState() == Entry.NEVER_USED) || 
				(table[bucket].getState() == Entry.PREVIOUSLY_USED)) {
			table[bucket].setStuff(value);
			table[bucket].setState(Entry.IN_USE);
			return true;
		} else {
			// Collision
			for (int i = 0; i < TABLE_SIZE; i++) {
				bucket = linearProbe(bucket);
				if ((table[bucket].getState() == Entry.NEVER_USED) || 
						(table[bucket].getState() == Entry.PREVIOUSLY_USED)) {
					table[bucket].setStuff(value);
					table[bucket].setState(Entry.IN_USE);
					return true;
				} 
			}

			// Table is full
			return false;
		}


	}
	
	public boolean delete(String value) {
		int bucket = search(value);
		
		if (bucket < 0) {
			return false;
		}
		
		table[bucket].setState(Entry.PREVIOUSLY_USED);
		return true;
	}
	
	public int search(String value) {
		int bucket = hashFunction(value);

		if (table[bucket].getState() == Entry.NEVER_USED) {
			return Entry.NEVER_USED;
		}
		
		if (table[bucket].getStuff().equals(value)) {
			return bucket;
		} else {
			// search for it
			for (int i = 0; i < TABLE_SIZE; i++) {
				bucket = linearProbe(bucket);

				if (table[bucket].getStuff().equals(value)) {
					return bucket;
				} 

				// keep looking
				if (table[bucket].getState() == Entry.PREVIOUSLY_USED)
					continue;

				// Fail
				if (table[bucket].getState() == Entry.NEVER_USED) {
					return NEVER_USED;
				}

			}

			// Table is full
			return NEVER_USED;
		}

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
				rtn += table[i].getStuff() + "\n";
			}
		}
		
		
		return rtn;
	}

}
