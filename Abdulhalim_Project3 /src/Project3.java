import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * COP 3530: Project 3 – Linked Lists
 * 
 * <p>
 * The Project3 class takes the name of a CSV file from the user
 * that carries values of countries with various aspects attached such as capitol name,
 * GDP, population, etc., open said file, organize the country values from the file into a
 * a stack of Country objects, and then popping said objects out of the stack and into a priority queue which orders
 * the objects ascending through death rate. We only accept countries from the stack and place them into the priority queue
 * if they fall under the "FAIR", "GOOD", or "VERY GOOD" groupings, which covers the range of countries with death rates between
 * 20 and 350. Both the stack and queue are made using linked lists, the first being a double-ended
 * singly-linked list and the second being a doubly linked list. We give the user a menu of 
 * 3 options to perform utilizing the newly created priority queue. Options are chosen through user input of a value from
 * 1-3. 
 * <p>
 * <p>
 * These options are...
 * <p>
 * <p>
 * 1) Entering a range of death rate values and deleting all links that contain a death rate within said range.
 * <p>
 * <p>
 * 2) Printing the priority queue.
 * <p>
 * 3) Exiting the program
 * <p>
 * 
 * 
 * @author Adam Abdulhalim
 * @version 10/26/22
 *
 */

public class Project3 {

	/**
	 * In the main method, we ask for user input for the CSV file that needs to be opened. We
	 * then open said file and organize the values provided into Country objects using the Country
	 * constructor and push these countries into our stack and subsequently pop them out and into our priority queue. 
	 * Once that is complete, we continuously ask for the user to choose an option
	 * between the 3 menu options mentioned in the Project3 class comment. This repeats until the
	 * user chooses option 3 to close the program.
	 * 
	 * @param args Arguments are taken from the user for CSV file opening, menu option choosing,
	 * and parameters for interval deletion range.
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

		Stack countryStack = new Stack();
		
		while(inFile.hasNext())
		{
			na = inFile.next();
			
			cap = inFile.next();
			
			popu = inFile.nextDouble();
			
			gd = inFile.nextDouble();
			
			cas = inFile.nextDouble();
			
			de = inFile.nextDouble();
			
			ar = Double.parseDouble(inFile.next());
			
			Country temp = new Country(na, cap, popu, gd, cas, de, ar);
			
			if(temp.getDeathRate() >= 20 && temp.getDeathRate() < 350) {
			
				countryStack.push(temp);
			}
			
		}
		
		System.out.println("\nStack Contents: \n");
		System.out.println("Name                             Capitol           GDPPC      CFR         CaseRate    DeathRate  PopDensity");
		System.out.println("-----------------------------------------------------------------------------------------------------------");
		countryStack.printStack(countryStack.getFirst());
		
		PriorityQ countryQ = new PriorityQ();
		
		while(!countryStack.isEmpty()) {
			countryQ.insert(countryStack.pop());
		}

		System.out.println("\nPriority Queue Contents: \n");
		System.out.println("Name                             Capitol           GDPPC      CFR         CaseRate    DeathRate  PopDensity");
		System.out.println("-----------------------------------------------------------------------------------------------------------");
		countryQ.printPriorityQ(countryQ.getFirst());
		
		while(true) {
			
		System.out.println("1. Enter a DR interval for deletions on priority queue \n"
				+ "2. Print priority queue \n"
				+ "3. Exit");
		String input = kb.next();
		
		if(input.equals("1")) {
			double upper, lower;
			boolean deleted = false;
			
			System.out.print("Enter lower bound: ");
			try {
			lower = kb.nextDouble();
			System.out.print("Enter upper bound: ");
			upper = kb.nextDouble();
			}
			catch (InputMismatchException e){
				System.out.println("\nInvalid interval! Input must be integers\n");
				continue;
			}
			
			if(lower > upper) {
				System.out.println("\nInvalid interval! Lower bound must be less than or equal to upper bound.\n");
				continue;
			}
	
			deleted = countryQ.intervalDelete(countryQ, lower, upper);
			
			if (deleted == true) {
				System.out.println("\nCountries within the death rate range of " + lower + " and " + upper + " have been deleted.\n");
			}
		}
			
		else if(input.equals("2")) {
			
			if(countryQ.getFirst() == null)
				System.out.println("\nPriority Queue is empty, there is nothing to print.\n");
			else {
				System.out.println("\nPriority Queue Contents: \n");
				System.out.println("Name                             Capitol           GDPPC      CFR         CaseRate    DeathRate  PopDensity");
				System.out.println("-----------------------------------------------------------------------------------------------------------");
				countryQ.printPriorityQ(countryQ.getFirst());
			}
		}	
			
		else if(input.equals("3")) {
			
			System.out.println("\nHave a good day!");
			kb.close();
			System.exit(0);

		}
		
		else {
			
			System.out.println("\nInvalid input, reenter an option 1-3:\n");
		}
			
			
		}
		
	}

}
