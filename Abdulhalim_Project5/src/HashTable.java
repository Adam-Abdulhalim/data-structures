/**
 * 
 * This class was created in order for us to be able to store Node objects
 * (private class provided by professor) in a Hash Table format. Within this
 * class we also have the private Node class and the public NodeList class. Node
 * objects hold country name, population, covid cases info, as well as a
 * reference to the next Node in the list (Linked List). The HashTable object
 * holds an array of NodeList objects, which are linked lists of Node objects.
 * The HashTable constructor creates a HashTable object with said array being
 * size 293 (prime number approx. twice the size of the amount of Nodes we want
 * to store for this particular project. The HashTable class also has methods
 * for inserting Nodes, deleting Nodes, searching for a specific Node, printing
 * all Nodes, and printing the amount of empty and collided cells in the table.
 * 
 * 
 * @author Adam Abdulhalim
 * @version 12/10/22
 */
public class HashTable {

	private class Node {
		String name;
		long population;
		long cases;
		Node nextNode;

		public Node(String name, long population, long cases) {
			this.name = name;
			this.population = population;
			this.cases = cases;
		}

		public void printNode() {
			System.out.printf("%-30s %-20.3f\n", name, (double) cases / population * 100000);
		}
	}

	/**
	 * 
	 * This class was created in order to store Node objects in linked lists in each
	 * index of our HashTable array. NodeList objects store references to the first
	 * and last Nodes in the list (double-ended). The constructor for NodeList
	 * objects set both of these to null initially.
	 * 
	 * @author Adam Abdulhalim
	 * @version 12/10/22
	 */
	public class NodeList {
		Node first;
		Node last;

		public NodeList() {
			first = null;
			last = null;
		}
	}

	NodeList[] array;

	public HashTable() {
		array = new NodeList[293];
		for (int i = 0; i < 293; i++) {
			array[i] = new NodeList();
		}
	}

	/**
	 * This method takes the name of a country and returns said country's hash value
	 * so that it can be inserted into the proper array index in the hash table. The
	 * hash value is calculated by summing up the Unicode values of all the
	 * characters in the country’s name string (including spaces and other special
	 * characters), and then modulus the result with 293.
	 * 
	 * @param name This parameter passes along the value of a country's name String.
	 * @return The hash value correlated with the specific country name (int).
	 */
	public int hashVal(String name) {
		int hashVal = 0;

		for (int i = 0; i < name.length(); i++) {

			hashVal = hashVal + (int) name.charAt(i);
		}

		hashVal = hashVal % 293;
		return hashVal;
	}

	/**
	 * 
	 * This method inserts a new country Node object into the hash table at its
	 * respective index hash value (utilizing HashTable.hashVal to get the hash
	 * value).
	 * 
	 * @param country    This parameter passes along the value of a country's name
	 *                   String.
	 * @param population This parameter passes along the value of a country's
	 *                   population (long).
	 * @param cases      This parameter passes along the value of a country's covid
	 *                   cases (long).
	 */
	public void insert(String country, long population, long cases) {

		int hash = hashVal(country);

		Node newNode = new Node(country, population, cases);

		if (array[hash].first == null) {
			array[hash].first = newNode;
			array[hash].last = newNode;
		} else {
			array[hash].last.nextNode = newNode;
			array[hash].last = newNode;
		}

	}

	/**
	 * This method iterates through the entire hash table array and through each
	 * linked list in each index, printing the name and covid case rate of each
	 * country Node. "Empty" is printed whenever an array index is empty.
	 * (HashTable.Node.printNode utilized)
	 */
	public void display() {

		for (int i = 0; i < 293; i++) {
			Node current = array[i].first;
			System.out.print(i + ". ");
			if (current == null) {
				System.out.println("Empty");
			}
			int count = 0;
			while (current != null) {
				if (count != 0) {
					if (i < 100 && i > 9)
						System.out.print("    ");
					if (i > 99)
						System.out.print("     ");
				}
				current.printNode();
				current = current.nextNode;
				count++;
			}
		}

	}

	/**
	 * This method takes input for a country's name String, finds said country's
	 * hash value, and checks the NodeList at that hash index to see if the country
	 * exists within the hash table. If the country is found, its name and covid
	 * case rate is printed (utilizing HashTable.Node.printNode) and the array index
	 * value (int) is returned. If it is not found, -1 is returned.
	 * 
	 * @param country This parameter passes along the value of a country's name
	 *                String.
	 * @return The array index of the country if it exists within the hash table. -1
	 *         otherwise.
	 */
	public int find(String country) {
		int hash = hashVal(country);

		Node current = array[hash].first;

		while (current != null) {
			if (country.compareTo(current.name) == 0) {
				System.out.print(country + " is found at index " + hash);
				System.out.printf(" with case rate of %.3f\n", (double) current.cases / current.population * 100000);
				return hash;
			}
			current = current.nextNode;
		}
		return -1;

	}

	/**
	 * This method deletes a specified country Node from the hash table. Said
	 * country is searched for essentially the same way as in HashTable.find, and
	 * instead the references to said Node are rerouted (deletion) based on where it
	 * is in its specific linked list. If the country does not exist, nothing is
	 * deleted.
	 * 
	 * @param country This parameter passes along the value of a country's name
	 *                String.
	 */
	public void delete(String country) {
		int hash = hashVal(country);

		Node current = array[hash].first;
		Node previous = null;

		while (current != null) {
			if (country.compareTo(current.name) == 0) {

				if (current == array[hash].first) {
					if (current.nextNode == null) {
						array[hash].last = null;
					}
					array[hash].first = array[hash].first.nextNode;
					System.out.println(country + " was deleted from the table at index " + hash);
					return;
				}

				else if (current == array[hash].last) {
					array[hash].last = previous;
					array[hash].last.nextNode = null;
					System.out.println(country + " was deleted from the table at index " + hash);
					return;
				}

				else {
					previous.nextNode = current.nextNode;
					System.out.println(country + " was deleted from the table at index " + hash);
					return;
				}

			}
			previous = current;
			current = current.nextNode;
		}

		System.out.println("Country does not exist!");
	}

	/**
	 * This method iterates through the entire hash table and counts the amount of
	 * empty cells and the amount of collided cells in the table. These values are
	 * then printed once the end of the array is reached.
	 */
	public void printEmptyAndCollidedCells() {
		int empty = 0;
		int coll = 0;
		for (int i = 0; i < 293; i++) {
			Node current = array[i].first;

			if (current == null)
				empty++;
			else if (current.nextNode != null)
				coll++;

		}
		System.out.println("There are " + empty + " empty cells and " + coll + " collisions in the hash table");
	}

}
