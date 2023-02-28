import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/** 
 * COP 3530: Project 1 – Array Searches and Sorts  
 * 
 * <p>
 * The Project1 class takes the name of a CSV file from the user
 * that carries values of countries with various aspects attached such as capitol name,
 * GDP, population, etc., open said file, organize the country values from the file into
 * an array of Country objects (utilizing Country.java), and give the user a menu of 
 * 7 options to perform on the array. Options are chosen through user input of a value from
 * 1-7. 
 * <p>
 * <p>
 * These options are...
 * 1) returning a report of all the countries in the array utilizing Country.printCountryLn()
 * 2) sorting the objects alphabetically using their names (insertion sort), 
 * 3) sorting the objects ascending through their CFR values (selection sort),
 * 4) sorting the objects ascending through their GDPPC values (bubble sort),
 * 5) searching for a specific object using user input for the Country name (binary 
 * search if array is sorted by name, sequential search otherwise)
 * 6) printing a Kendall's Tau correlation matrix between GDPPC/caseRate, GDPPC/deathRate,
 * popDens/caseRate, and popDens/deathRate
 * 7) Exiting the program
 * <p>
 * 
 * @author Adam Abdulhalim 
 * @version 9/16/22
 */ 

public class Project1 {
	
	/**
	 * This method loops through an entire array of Country objects, which is provided as a
	 * parameter, and prints each Country alongside their attributes of name, capitol, GDPPC,
	 * case fatality rate, Covid case rate, Covid death rate, and population density.
	 * 
	 * @param array This parameter provides the array of Country objects that need to be printed.
	 */
	
	public static void countryReport(Country [] array) {

		int j = 0;
		System.out.println("Name                             Capitol           GDPPC      CFR         CaseRate    DeathRate  PopDensity");
		System.out.println("-----------------------------------------------------------------------------------------------------------");
		while(j <= 144) {
			array[j].printCountryLn();
			++j;
		}
	}
	
	/**
	 * This method utilizes insertion sort in order to sort the Country objects within an array
	 * alphabetically by their name strings. The java compareTo method is utilized within
	 * the insertion sort in order to compare object names to one another.
	 * 
	 * @param array This parameter provides the array of Country objects that need to be sorted
	 * by name.
	 */
	
	public static void nameInsertionSort(Country [] array) {
		int inner;
		int outer = 1;
		while(outer < 145) { 
		      Country temp = array[outer];            
		      inner = outer - 1;                     
		      while(inner >= 0 && array[inner].getName().compareTo(temp.getName()) > 0) { 
		      
		    	  array[inner + 1] = array[inner];     
		    	  inner--;                         
		     }
		     array[inner + 1] = temp;       
		     outer++;
		}
	}

	/**
	 * This method utilizes selection sort in order to sort the Country objects within an array
	 * by their case fatality rate (CFR) values (ascending).
	 * 
	 * @param array This parameter provides the array of Country objects that need to be sorted
	 * by CFR.
	 */
	
	public static void cfrSelectionSort(Country [] array) {
		
		for(int outer = 0; outer < 144; outer++) {
			
			int lowest = outer;
			
			for(int inner = outer + 1; inner < 145; inner++) {
			
				if(array[inner].getCFR() < array[lowest].getCFR()) {
					lowest = inner;
				}
			}
				if(lowest != outer) {
					Country temp = array[lowest];
					array[lowest] = array[outer];
					array[outer] = temp;
				}
			}
		}
	
	/**
	 * This method utilizes bubble sort in order to sort the Country objects within an array
	 * by their GDP per capita (GDPPC) values (ascending).
	 * 
	 * @param array This parameter provides the array of Country objects that need to be sorted
	 * by GDPPC.
	 */
	
	public static void gdppcBubbleSort(Country [] array) {
		
		for (int outer = 0; outer < 144; outer++) {
			
			for (int inner = 144; inner > outer; inner--) {
			
				if (array[inner].getGDPPC() < array[inner - 1].getGDPPC()) {
			
					Country temp = array[inner - 1];
			
					array[inner - 1] = array[inner];
			
					array[inner] = temp;
			
				}
			}
		}
	}
	
	/**
	 * This method utilizes sequential search in order to search through an array of Country
	 * objects for a specific Country given by user input in the main method. This method is 
	 * utilized when the array is not sorted by name. It utilizes Country.printCountrySearched 
	 * in order to print the found country and its relevant attributes. If a country is not found,
	 * a message to the user is printed saying so.
	 * 
	 * @param array This parameter provides the array of Country objects that need to be searched
	 * through.
	 * @param name This parameter provides the user input of the Country name that needs to be
	 * searched for.
	 */
	
	public static void sequentialSearch(Country [] array, String name) {
		int x = 0;
		while(x < 145) {
			
			if(array[x].getName().compareTo(name) == 0) {
				array[x].printCountrySearched();
				break;
			}
			x++;
		}
		if(x == 145) {
			System.out.println("Error: country not found. \nWarning: Search is case sensitive.\n");
		}
	}
	
	/**
	 * This method utilizes binary search in order to search through an array of Country
	 * objects for a specific Country given by user input in the main method. This method is 
	 * utilized when the array is sorted by name. It utilizes Country.printCountrySearched 
	 * in order to print the found country and its relevant attributes. If a country is not found,
	 * a message to the user is printed saying so.
	 * 
	 * @param array This parameter provides the array of Country objects that need to be searched
	 * through.
	 * @param name This parameter provides the user input of the Country name that needs to be
	 * searched for.
	 */
	
	public static void binarySearch(Country [] array, String name) {

		int lower = 0;
		int upper = 144;
		int mid;
		
		while (lower <= upper) {
			mid = (lower + upper) / 2;
			
			if(array[mid].getName().compareTo(name) == 0) {
				
				array[mid].printCountrySearched();
				break;
			}
			
			else if(array[mid].getName().compareTo(name) > 0) {
				
				upper = mid - 1;
			}
			
			else {
				
				lower = mid + 1;
			}
			
		}
		if(lower > upper) {
			System.out.println("Error: country not found. \nWarning: Search is case sensitive.\n");
		}
		
	}
	
	/**
	 * In the main method, we ask for user input for the CSV file that needs to be opened. We
	 * then open said file and organize the values provided into Country objects using the Country
	 * constructor. Once that is complete, we continuously ask for the user to choose an option
	 * between the 7 menu options mentioned in the Project1 class comment. This repeats until the
	 * user chooses option 7 to close the program.
	 * 
	 * @param args Arguments are taken from the user for CSV file opening, menu option choosing,
	 * and country name searching.
	 */
	
	public static void main(String[] args) {
	
		Scanner kb = new Scanner(System.in);
		System.out.println("Enter the file name: ");
		String fileName = kb.next();
		
		Scanner inFile = null;
		try 
		{	
			inFile = new Scanner(new File(fileName));
		}
		catch (FileNotFoundException e)
		{
			System.out.println("\nFile not found!");
			System.exit(1);
		}
		
		inFile.useDelimiter(",|\\n");
		
		String na;
		String cap;
		double popu, gd, cas, de, ar;
		int i = 0;
		
		inFile.next();
		inFile.next();
		inFile.next();
		inFile.next();
		inFile.next();
		inFile.next();
		inFile.next();
		
		Country[] cArr = new Country[145];
		
		while(inFile.hasNext())
		{
			na = inFile.next();
			
			cap = inFile.next();
			
			popu = inFile.nextDouble();
			
			gd = inFile.nextDouble();
			
			cas = inFile.nextDouble();
			
			de = inFile.nextDouble();
			
			ar = inFile.nextDouble();
		
			cArr[i] = new Country(na, cap, popu, gd, cas, de, ar);
			
			++i;
		}
		
		
		
		while(true) {
			
		System.out.println("1. Print a countries report \n"
				+ "2. Sort by Name  \n"
				+ "3. Sort by Case Fatality Rate \n"
				+ "4. Sort by GDP per capita \n"
				+ "5. Find and print a given country  \n"
				+ "6. Print Kendall’s tau matrix \n"
				+ "7. Quit \n"
				+ "Enter your choice: ");
		String input = kb.next();
		
		if(input.equals("1")) {
			
//			System.out.println("OPTION 1 CHOSEN");
			countryReport(cArr);
			
		}
			
		else if(input.equals("2")) {
			
//			System.out.println("OPTION 2 CHOSEN");
			nameInsertionSort(cArr);
		}	
			
		else if(input.equals("3")) {
			
//			System.out.println("OPTION 3 CHOSEN");
			cfrSelectionSort(cArr);
		}
		
		else if(input.equals("4")) {
			
//			System.out.println("OPTION 4 CHOSEN");
			gdppcBubbleSort(cArr);
		}		
		
		else if(input.equals("5")) {
			System.out.println("Enter country name: ");
			Scanner sr = new Scanner(System.in).useDelimiter("");
			String inputName = sr.nextLine();
//			System.out.println("OPTION 5 CHOSEN");
			if(cArr[0].getName().compareTo("Afghanistan") == 0) {
				binarySearch(cArr, inputName);
			}
			else {
				sequentialSearch(cArr, inputName);
			}
		}	
		
		else if(input.equals("6")) {
			
			System.out.println("Unfortunately, Kendall's Tau functionality is not available at this time.");
		}
		
		else if(input.equals("7")) {
			
//			System.out.println("OPTION 7 CHOSEN");
			System.out.println("Have a good day!");
			kb.close();
			System.exit(0);
		}
		
		else {
			
			System.out.println("Invalid input, reenter an option 1-7:");
		}
			
			
		}
		
	}
}
