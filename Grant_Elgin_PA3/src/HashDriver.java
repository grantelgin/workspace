
public class HashDriver {

	public static void main(String[] args) {
		HashDriver me = new HashDriver ();
		me.doIt();
	}
	
	public void doIt () {
		OpenHashTable table = new OpenHashTable();
		// populate table 
		FilmPlaceTime newFilm = new FilmPlaceTime();
		newFilm.setPlace("Boston");
		newFilm.setTime("1950's");
		newFilm.setTitle("Let's go to town");
		
		table.add(newFilm);
		table.quickAdd("1940 Boston Maine Airways Instrument School", "Logan Airport", "1940");
		table.quickAdd("Boston The Way It Was", "Scollay Square", "1930");
		table.quickAdd("1929 Mt. Washington Steamer Lake Winnipesaukee", "Lake Winnipesaukee NH", "1929");
		table.quickAdd("1931 Airplanes Over Boston", "Boston", "1931");
		table.quickAdd("1932 Original Celtics", "Temple University", "1932");
		table.quickAdd("1933 NY Giants at Braves Field Boston Braves", "Boston", "1933");
		table.quickAdd("Rose Bowl Alabama 29 to Stanford 13 1935", "Pasadena CA", "1935");
		table.quickAdd("1941 Sugar Bowl - Tennessee vs. Boston College", "New Orleans LA", "1941");
		table.quickAdd("Boston Horseless Fire Department", "Boston MA", "1899");
		table.quickAdd("Colour on the Thames (1935)", "London England", "1935");
		table.quickAdd("LN 501 473 Seeing Boston 1906 footagefarm.com", "Boston", "1906");
		table.quickAdd("Full Trolley Ride Through Boston 1903", "Boston", "1903");
		table.quickAdd("1939 Color Movie Boston Skyline, Charles River", "Boston MA", "1939");
		table.quickAdd("New York, 1940s", "Manhattan NY", "1940's");
		table.quickAdd("Boston Common and Proper, 1930s", "Boston Common", "1930's");
		table.quickAdd("1939 'Drive' thru Cambridge,Ma", "Cambridge MA", "1939");
		
		//show table
		System.out.println(table);

		// search and display an item
		FilmPlaceTime fpt = table.searchByTitle("1933 NY Giants at Braves Field Boston Braves");
		System.out.println("fpt: " + fpt.getTitle());
		
		// search for an item to be deleted, then delete it. 
		table.delete(table.searchByTitle("LN 501 473 Seeing Boston 1906 footagefarm.com"));
		table.delete(table.searchByTitle("Let's go to town"));
		table.delete(table.searchByTitle("1929 Mt. Washington Steamer Lake Winnipesaukee"));
		table.delete(table.searchByTitle("Rose Bowl Alabama 29 to Stanford 13 1935"));
		table.delete(table.searchByTitle("Boston The Way It Was"));
		
		// show table after deleting items
		System.out.println(table);

		System.out.println(table.searchByTitle("Let's go to town"));
		
		//add 5 new items
		table.quickAdd("Video 12_1990: Boston Central Artery before the Big Dig", "Boston", "1990");
		table.quickAdd("Drive through Boston in 1964", "Boston", "1964");
		table.quickAdd("Boston Mass - 1975", "Boston", "1975");
		table.quickAdd("Bill Burr Gives Us A Tour of Boston - Part 1 - September 2011", "Boston", "2011");
		table.quickAdd("Boston Central Artery '88 - v2 - (1 of 5)", "Boston", "1988");
		
		// display final version of the OpenHashTable.
		System.out.println(table);
		

		ChainedHashTable chTable = new ChainedHashTable();
		//populate table
		newFilm.setPlace("Boston");
		newFilm.setTime("1950's");
		newFilm.setTitle("Let's go to town");
		
		chTable.add(newFilm);
		chTable.quickAdd("1940 Boston Maine Airways Instrument School", "Logan Airport", "1940");
		chTable.quickAdd("Boston The Way It Was", "Scollay Square", "1930");
		chTable.quickAdd("1929 Mt. Washington Steamer Lake Winnipesaukee", "Lake Winnipesaukee NH", "1929");
		chTable.quickAdd("1931 Airplanes Over Boston", "Boston", "1931");
		chTable.quickAdd("1932 Original Celtics", "Temple University", "1932");
		chTable.quickAdd("1933 NY Giants at Braves Field Boston Braves", "Boston", "1933");
		chTable.quickAdd("Rose Bowl Alabama 29 to Stanford 13 1935", "Pasadena CA", "1935");
		chTable.quickAdd("1941 Sugar Bowl - Tennessee vs. Boston College", "New Orleans LA", "1941");
		chTable.quickAdd("Boston Horseless Fire Department", "Boston MA", "1899");
		chTable.quickAdd("Colour on the Thames (1935)", "London England", "1935");
		chTable.quickAdd("LN 501 473 Seeing Boston 1906 footagefarm.com", "Boston", "1906");
		chTable.quickAdd("Full Trolley Ride Through Boston 1903", "Boston", "1903");
		chTable.quickAdd("1939 Color Movie Boston Skyline, Charles River", "Boston MA", "1939");
		chTable.quickAdd("New York, 1940s", "Manhattan NY", "1940's");
		chTable.quickAdd("Boston Common and Proper, 1930s", "Boston Common", "1930's");
		chTable.quickAdd("1939 'Drive' thru Cambridge,Ma", "Cambridge MA", "1939");

		//show table
		System.out.println(chTable);
		
		// search and display an item
		fpt = chTable.searchByTitle("1933 NY Giants at Braves Field Boston Braves");
		System.out.println("fpt: " + fpt.getTitle());
		
		// search for an item to be deleted, then delete it. 
		chTable.delete(chTable.searchByTitle("LN 501 473 Seeing Boston 1906 footagefarm.com"));
		chTable.delete(chTable.searchByTitle("Let's go to town"));
		chTable.delete(chTable.searchByTitle("1929 Mt. Washington Steamer Lake Winnipesaukee"));
		chTable.delete(chTable.searchByTitle("Rose Bowl Alabama 29 to Stanford 13 1935"));
		chTable.delete(chTable.searchByTitle("Boston The Way It Was"));
		
		// show table after deleting items
		System.out.println(chTable);

		System.out.println(chTable.searchByTitle("Let's go to town"));
				
		//add 5 new items
		chTable.quickAdd("Video 12_1990: Boston Central Artery before the Big Dig", "Boston", "1990");
		chTable.quickAdd("Drive through Boston in 1964", "Boston", "1964");
		chTable.quickAdd("Boston Mass - 1975", "Boston", "1975");
		chTable.quickAdd("Bill Burr Gives Us A Tour of Boston - Part 1 - September 2011", "Boston", "2011");
		chTable.quickAdd("Boston Central Artery '88 - v2 - (1 of 5)", "Boston", "1988");
				
		// display final version of the OpenHashTable.
		System.out.println(chTable);
		
		
				
	}
}
