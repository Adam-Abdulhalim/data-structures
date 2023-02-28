import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * COP 3530: Project 4 – Binary Search Trees
 * 
 * <p>
 * The Project4 class takes the name of a CSV file from the user that carries
 * values of countries with various aspects attached such as capitol name, GDP,
 * population, etc., open said file, and organize the country values from the
 * file into a Binary Search Tree of Node objects. We give the user a menu of 9
 * options to perform utilizing the newly created binary search tree. Options
 * are chosen through user input of a value from 1-9.
 * <p>
 * <p>
 * These options are...
 * <p>
 * <p>
 * 1) Printing the Binary Search Tree contents utilizing inOrder traversal.
 * <p>
 * <p>
 * 2) Printing the Binary Search Tree contents utilizing preOrder traversal.
 * <p>
 * <p>
 * 3) Printing the Binary Search Tree contents utilizing postOrder traversal.
 * <p>
 * <p>
 * 4) Insert a new country into the Binary Search Tree using user input.
 * <p>
 * <p>
 * 5) Deleting a country in the Binary Search Tree using user input.
 * <p>
 * <p>
 * 6) Search for and print out the path of a country node in the Binary Search
 * Tree.
 * <p>
 * <p>
 * 7) Print the bottom X countries in the Binary Search Tree in regards to
 * GDPPC. X value is given by user.
 * <p>
 * <p>
 * 8) Print the top X countries in the Binary Search Tree in regards to GDPPC. X
 * value is given by user.
 * <p>
 * <p>
 * 9) Exit program.
 * 
 * @author Adam Abdulhalim
 * @version 11/18/22
 *
 */

public class Project4 {

	/**
	 * In the main method, we ask for user input for the CSV file that needs to be
	 * opened. We then open said file and organize the values provided into Node
	 * objects using the Node constructor and push these country Nodes into our
	 * Binary Search Tree. Once that is complete, we continuously ask for the user
	 * to choose an option between the 9 menu options mentioned in the Project4
	 * class comment. This repeats until the user chooses option 9 to close the
	 * program.
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
		double popu, gd, cas, de, ar, gdppc;

		inFile.next();
		inFile.next();
		inFile.next();
		inFile.next();
		inFile.next();
		inFile.next();
		inFile.next();

		BinarySearchTree BST = new BinarySearchTree();

		while (inFile.hasNext()) {
			na = inFile.next();

			cap = inFile.next();

			popu = inFile.nextDouble();

			gd = inFile.nextDouble();

			cas = inFile.nextDouble();

			de = inFile.nextDouble();

			ar = Double.parseDouble(inFile.next());

			gdppc = gd / popu;

			BST.insert(na, gdppc);

		}

		while (true) {

			System.out.println("1) Print tree inorder \n" + "2) Print tree preorder \n" + "3) Print tree postorder \n"
					+ "4) Insert a country with name and GDP per capita \n" + "5) Delete a country for a given name \n"
					+ "6) Search and print a country and its path for a given name. \n"
					+ "7) Print bottom countries regarding GDPPC \n" + "8) Print top countries regarding GDPPC \n"
					+ "9) Exit");
			String input = kb.next();

			if (input.equals("1")) {

				BST.printInorder(BST.root);

			}

			else if (input.equals("2")) {

				BST.printPreorder(BST.root);

			}

			else if (input.equals("3")) {

				BST.printPostorder(BST.root);

			}

			else if (input.equals("4")) {

				String inName;
				double inGDPPC;

				System.out.println("Enter country name: ");
				inName = kb.next();
				inName = inName.substring(0, 1).toUpperCase() + inName.substring(1);
				System.out.println("Enter country GDPPC: ");
				try {
					inGDPPC = kb.nextDouble();
				} catch (InputMismatchException e) {
					System.out.println("\nInvalid input! GDPPC must be a double\n");
					continue;
				}

				BST.insert(inName, inGDPPC);

			}

			else if (input.equals("5")) {

				String delName;

				System.out.println("Enter country name: ");
				delName = ds.nextLine();
				delName = delName.substring(0, 1).toUpperCase() + delName.substring(1);

				BST.delete(delName);

			}

			else if (input.equals("6")) {

				String searchName;

				System.out.println("Enter country name: ");
				searchName = kb.next();
				searchName = searchName.substring(0, 1).toUpperCase() + searchName.substring(1);

				BST.find(searchName);

			}

			else if (input.equals("7")) {

				int num;

				System.out.println("Enter the number of countries: ");
				try {
					num = kb.nextInt();
				} catch (InputMismatchException e) {
					System.out.println("\nInvalid input! Number of countries must be an int.\n");
					continue;
				}

				PriorityQ2 pq2 = new PriorityQ2(200);

				BST.printBottomCountries(BST.root, pq2, num);

			}

			else if (input.equals("8")) {

				int num;

				System.out.println("Enter the number of countries: ");
				try {
					num = kb.nextInt();
				} catch (InputMismatchException e) {
					System.out.println("\nInvalid input! Number of countries must be an int.\n");
					continue;
				}

				PriorityQ2 pq = new PriorityQ2(200);

				BST.printTopCountries(BST.root, pq, num);

			}

			else if (input.equals("9")) {

				System.out.println("\nHave a good day!");
				kb.close();
				ds.close();
				System.exit(0);

			}

			else {

				System.out.println("\nInvalid input, reenter an option 1-3:\n");
			}

		}

	}

}
