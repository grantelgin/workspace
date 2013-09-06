import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * 
 * Grant Elgin
 * CS 232
 * HW7 
 *
 *The FileIO class creates a comma separated value list of available items. The user's input is verified against the available items in the file, and a name and price are returned. 
 *
 */

public class FileIO {

	public static void write()
	{
		String fileName = "ItemsAvailable.txt"; 
		PrintWriter outputStream = null;

		try
		{
			outputStream = new PrintWriter(fileName);
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Error opening the file" + fileName);
			System.exit(0);
		}

		System.out.println("Creating Available Items list...");

		String[] list = Item.listAvailableItems();
		outputStream.println("NAME,PRICE");
		for (int count = 1; count <= 10; count++)
		{
			Item currentItem = Item.validateItem(list[count - 1]);
			double price = currentItem.getPrice();
			String line = currentItem.getName() + "," + price;
			outputStream.println(line);
		}
		outputStream.close();
		read();
	}

	public static void read () 
	{
		String fileName = "ItemsAvailable.txt";
		Scanner inputStream = null;
		System.out.println("The file " + fileName + "\ncontains the following lines:\n");
		try
		{
			inputStream = new Scanner(new File(fileName));
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Error opening the file " + fileName);
			System.exit(0);
		}
		while (inputStream.hasNextLine())
		{
			String line = inputStream.nextLine();
			System.out.println(line);
		}
		inputStream.close();
		System.out.println();
	}


	public static Item validateItemFromFile(String name) {
		Item itemFromFile = new Item();

		String fileName = "ItemsAvailable.txt";

		try
		{
			Scanner inputStream = new Scanner(new File(fileName));
			String line = inputStream.nextLine();

			// Read the rest of the file line by line
			while (inputStream.hasNextLine())
			{

				line = inputStream.nextLine();
				// Turn the string into an array of strings
				String[] ary = line.split(",");
				// Extract each item into an appropriate variable
				if ( name.equalsIgnoreCase(ary[0])) {
					itemFromFile.setName(ary[0].toString());
					itemFromFile.setPrice(Double.parseDouble(ary[1]));
				}

			}

			inputStream.close( );


		}
		catch(FileNotFoundException e)
		{
			System.out.println("Cannot find file " + fileName);
		}
		catch(IOException e)
		{
			System.out.println("Problem with input from file " + fileName); 
		}


		return itemFromFile;
	}
}


