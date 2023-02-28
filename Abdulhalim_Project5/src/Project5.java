import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * COP 3530: Project 5 – Hash Tables
 * 
 * <p>
 * The Project5 class takes the name of a CSV file from the user that carries
 * values of countries with various aspects attached such as capitol name, GDP,
 * population, etc., open said file, and organize the country values from the
 * file into a Hash Table of Node objects. We give the user a menu of 6 options
 * to perform utilizing the newly created hash table. Options are chosen through
 * user input of a value from 1-6.
 * <p>
 * <p>
 * These options are...
 * <p>
 * <p>
 * 1) Printing the Hash Table contents.
 * <p>
 * <p>
 * 2) Delete a country in the Hash Table using user input.
 * <p>
 * <p>
 * 3) Insert a new country into the Hash table using user input.
 * <p>
 * <p>
 * 4) Search for and print out the details of a country in the Hash Table using
 * user input.
 * <p>
 * <p>
 * 5) Print out the amount of empty and collided cells in the Hash Table.
 * <p>
 * <p>
 * 6) Exit program.
 * <p>
 * 
 * @author Adam Abdulhalim
 * @version 12/10/22
 *
 */

public class Project5 {

	/**
	 * In the main method, we ask for user input for the CSV file that needs to be
	 * opened. We then open said file and organize the values provided into Node
	 * objects using the Node constructor and insert these country Nodes into our
	 * Hash Table at their respective index. Once that is complete, we continuously
	 * ask for the user to choose an option between the 6 menu options mentioned in
	 * the Project5 class comment. This repeats until the user chooses option 6 to
	 * close the program.
	 * 
	 * @param args Arguments are taken from the user for CSV file opening, menu
	 *             option choosing, and parameters for necessary menu options.
	 */

	public static void main(String[] args) {
		Scanner kb = new Scanner(System.in);
		Scanner ds = new Scanner(System.in).useDelimiter("");
		System.out.println("Enter the file name: ");
		String fileName = kb.next();

		Scanner inFile = null;
		try {
			inFile = new Scanner(new File(fileName));
		} catch (FileNotFoundException e) {
			System.out.println("\nFile not found!");
			System.exit(1);
		}

		inFile.useDelimiter(",|\\n");

		String na;
		String cap;
		double gd, de, ar, gdppc;
		long popu, cas;

		inFile.next();
		inFile.next();
		inFile.next();
		inFile.next();
		inFile.next();
		inFile.next();
		inFile.next();

		HashTable table = new HashTable();

		while (inFile.hasNext()) {
			na = inFile.next();
			// take name here

			cap = inFile.next();

			popu = inFile.nextLong();
			// take population here

			gd = inFile.nextDouble();

			cas = inFile.nextLong();
			// take cases here

			de = inFile.nextDouble();

			ar = Double.parseDouble(inFile.next());

			gdppc = gd / popu;

			table.insert(na, popu, cas);
		}

		while (true) {

			System.out.println("1) Print hash table \n" + "2) Delete a country of a given name  \n"
					+ "3) Insert a country of its name, population, and COVID cases  \n"
					+ "4) Search and print a country and its case rate for a given name \n"
					+ "5) Print numbers of empty cells and collided cells \n" + "6) Exit ");
			String input = kb.next();

			if (input.equals("1")) {

				table.display();

			}

			else if (input.equals("2")) {

				String delName;

				System.out.println("Enter country name: ");
				delName = ds.nextLine();
				delName = delName.substring(0, 1).toUpperCase() + delName.substring(1);

				table.delete(delName);

			}

			else if (input.equals("3")) {

				String inName;
				long inPop;
				long inCas;

				System.out.println("Enter country name: ");
				inName = ds.nextLine();
				inName = inName.substring(0, 1).toUpperCase() + inName.substring(1);

				System.out.println("Enter country population: ");
				try {
					inPop = kb.nextLong();
				} catch (InputMismatchException e) {
					System.out.println("\nInvalid input! Population must be a long.\n");
					continue;
				}

				System.out.println("Enter country covid cases: ");
				try {
					inCas = kb.nextLong();
				} catch (InputMismatchException e) {
					System.out.println("\nInvalid input! Covid case amount must be a long.\n");
					continue;
				}

				table.insert(inName, inPop, inCas);

				System.out.println(inName + " has been inserted into the hash table.");

			}

			else if (input.equals("4")) {

				String searchName;

				System.out.println("Enter country name: ");
				searchName = ds.nextLine();
				searchName = searchName.substring(0, 1).toUpperCase() + searchName.substring(1);

				int sInd = table.find(searchName);

				if (sInd == -1)
					System.out.println(searchName + " is not a country");
				else {
					//System.out.println(searchName + " resides at index " + sInd);
				}
			}

			else if (input.equals("5")) {

				table.printEmptyAndCollidedCells();

			}

			else if (input.equals("6")) {

				System.out.println("\nHave a good day!");
				kb.close();
				ds.close();
				System.exit(0);

			}

			else {

				System.out.println("\nInvalid input, reenter an option 1-6:\n");
			}

		}

	}

}
