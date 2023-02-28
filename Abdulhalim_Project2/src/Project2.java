import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * COP 3530: Project 2 – Stacks and PriorityQ Queues
 * 
 * <p>
 * The Project1 class takes the name of a CSV file from the user
 * that carries values of countries with various aspects attached such as capitol name,
 * GDP, population, etc., open said file, organize the country values from the file into both
 * a stack and a priority queue of Country objects (separately, utilizing Country.java), and give the user a menu of 
 * 7 options to perform on the array. Options are chosen through user input of a value from
 * 1-7. 
 * <p>
 * <p>
 * These options are...
 * 1) returning a report of all the countries in the stack utilizing Country.printCountryLn() within Stack.printStack
 * 2) pop a country object from the stack
 * 3) push a new country object to the stack
 * 4) returning a report of all the countries in the priority queue utilizing Country.printCountryLn() within PriorityQ.printPriQ
 * 5) remove a country from the priority queue
 * 6) insert a new country into the priority queue
 * 7) Exiting the program
 * <p>
 * 
 * 
 * @author Adam Abdulhalim
 * @version 10/3/22
 *
 */

public class Project2 {

	/**
	 * In the main method, we ask for user input for the CSV file that needs to be opened. We
	 * then open said file and organize the values provided into Country objects using the Country
	 * constructor and push/insert these countries into our stack and priority queue. 
	 * Once that is complete, we continuously ask for the user to choose an option
	 * between the 7 menu options mentioned in the Project1 class comment. This repeats until the
	 * user chooses option 7 to close the program.
	 * 
	 * @param args Arguments are taken from the user for CSV file opening, menu option choosing,
	 * and parameters for new country objects to add to the stack and/or queue.
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

		
		inFile.next();
		inFile.next();
		inFile.next();
		inFile.next();
		inFile.next();
		inFile.next();
		inFile.next();
		
		Stack countryStack = new Stack(150);
		PriorityQ countryQ = new PriorityQ(150);

		while(inFile.hasNext())
		{
			na = inFile.next();
			
			cap = inFile.next();
			
			popu = inFile.nextDouble();
			
			gd = inFile.nextDouble();
			
			cas = inFile.nextDouble();
			
			de = inFile.nextDouble();
			
			ar = inFile.nextDouble();
			
			Country temp = new Country(na, cap, popu, gd, cas, de, ar);
			
			countryStack.push(temp);
			countryQ.insert(temp);
			
		}
		
		System.out.println("\nStack created of 145 countries. \n"
				+ "Priority queue created of 145 countries. \n");
		
		
		while(true) {
			
		System.out.println("1) Print stack \n"
				+ "2) Pop a country object from stack \n"
				+ "3) Push a country object onto stack \n"
				+ "4) Print priority queue \n"
				+ "5) Remove a country object from priority queue \n"
				+ "6) Insert a country object into priority queue \n"
				+ "7) Exit ");
		String input = kb.next();
		
		if(input.equals("1")) {
			
			if(countryStack.isEmpty()) {
				System.out.println("\nThe stack is empty! There is nothing left to print.\n");
				continue;
			}
			
			countryStack.printStack();
			
		}
			
		else if(input.equals("2")) {
			
			if(countryStack.isEmpty()) {
				System.out.println("\nThe stack is empty! There is nothing left to pop.\n");
				continue;
			}
			
			countryStack.pop();
		}	
			
		else if(input.equals("3")) {
			
			if(countryStack.isFull()) {
				System.out.println("\nThe stack is full! There is no room to push.\n");
				continue;
			}
			
			System.out.print("Enter name: ");
			Scanner sr = new Scanner(System.in).useDelimiter("");
			String pushName = sr.nextLine();
			System.out.print("Enter capitol: ");
			String pushCapitol = sr.nextLine();
			System.out.print("Enter population: ");
			double pushPopu = kb.nextDouble();
			System.out.print("Enter GDP (USD): ");
			double pushGDP = kb.nextDouble();
			System.out.print("Enter COVID cases: ");
			double pushCases = kb.nextDouble();
			System.out.print("Enter COVID deaths: ");
			double pushDeaths = kb.nextDouble();
			System.out.print("Enter Area (km2): ");
			double pushArea = kb.nextDouble();
			
			Country pushTemp = new Country(pushName, pushCapitol, pushPopu, pushGDP, pushCases, pushDeaths, pushArea);
			countryStack.push(pushTemp);
			
			System.out.println("\nOne country has been pushed onto the stack.\n");
			

		}
		else if(input.equals("4")) {
			
			if(countryQ.isEmpty()) {
				System.out.println("\nThe queue is empty! There is nothing left to print.\n");
				continue;
			}
			
			countryQ.printPriQ();
			
		}		
		
		else if(input.equals("5")) {
		
			if(countryQ.isEmpty()) {
				System.out.println("\nThe queue is empty! There is nothing left to remove.\n");
				continue;
			}
			
			countryQ.remove();
			
		}	
		
		else if(input.equals("6")) {
			
			if(countryQ.isFull()) {
				System.out.println("\nThe queue is full! There is no room to insert.\n");
				continue;
			}
			
			System.out.print("Enter name: ");
			Scanner ur = new Scanner(System.in).useDelimiter("");
			String pushName = ur.nextLine();
			System.out.print("Enter capitol: ");
			String pushCapitol = ur.nextLine();
			System.out.print("Enter population: ");
			double pushPopu = kb.nextDouble();
			System.out.print("Enter GDP (USD): ");
			double pushGDP = kb.nextDouble();
			System.out.print("Enter COVID cases: ");
			double pushCases = kb.nextDouble();
			System.out.print("Enter COVID deaths: ");
			double pushDeaths = kb.nextDouble();
			System.out.print("Enter Area (km2): ");
			double pushArea = kb.nextDouble();
			
			Country pushTemp = new Country(pushName, pushCapitol, pushPopu, pushGDP, pushCases, pushDeaths, pushArea);
			countryQ.insert(pushTemp);

			System.out.println("\nOne country has been inserted into the priority queue.\n");
			
		}
		
		else if(input.equals("7")) {
			
			System.out.println("\nHave a good day!");
			kb.close();
			System.exit(0);
		}
		
		else {
			
			System.out.println("\nInvalid input, reenter an option 1-7:\n");
		}
			
			
		}
		
	}

}
