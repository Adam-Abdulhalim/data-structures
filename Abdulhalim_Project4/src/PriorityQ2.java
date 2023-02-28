/**
 * This class was created in order for us to have an object capable of storing
 * Node objects (see Node.java) within an array, replicating the behavior
 * of a priority queue, abstractly. This means being able to insert Node
 * objects onto a queue in an order of priority (in this case, through the GDPPC
 * of each Node object). A PriorityQ2
 * constructor must be given a size in order to provide the queue with a proper
 * maximum size. PriorityQ2 objects also contain a nItems value that keeps track
 * of how many items are in the queue.
 * 
 * @author Adam Abdulhalim
 * @version 11/18/22
 */
public class PriorityQ2 {

	private Node priQArr[];
	private int maxSize;
	private int nItems;

	public PriorityQ2(int size) {

		maxSize = size;
		priQArr = new Node[maxSize];
		nItems = 0;
	}

	/**
	 * The insertLow method takes in a Node object as a parameter and then inserts
	 * it into the priority queue in its proper place determined by the value of
	 * priority, going from lowest to highest. We iterate through the array, shifting over values that are less
	 * than the new value until we find where the new value must be placed.
	 * 
	 * @param item This parameter provides the method with the Node object that
	 *             needs to be inserted into the priority queue.
	 */
	public void insertHigh(Node item) {

		int j;

		if (nItems == 0) {

			priQArr[nItems++] = item;
		}

		else {

			for (j = nItems - 1; j >= 0; j--) {

				if (item.GDPPC > priQArr[j].GDPPC) {

					priQArr[j + 1] = priQArr[j];
				}

				else {

					break;

				}
			}
			priQArr[j + 1] = item;
			nItems++;
		}
	}
	/**
	 * The insertHigh method takes in a Node object as a parameter and then inserts
	 * it into the priority queue in its proper place determined by the value of
	 * priority, going from highest to lowest. We iterate through the array, shifting over values that are less
	 * than the new value until we find where the new value must be placed.
	 * 
	 * @param item This parameter provides the method with the Node object that
	 *             needs to be inserted into the priority queue.
	 */
	public void insertLow(Node item) {

		int j;

		if (nItems == 0) {

			priQArr[nItems++] = item;
		}

		else {

			for (j = nItems - 1; j >= 0; j--) {

				if (item.GDPPC < priQArr[j].GDPPC) {

					priQArr[j + 1] = priQArr[j];
				}

				else {

					break;

				}
			}
			priQArr[j + 1] = item;
			nItems++;
		}
	}

	/**
	 * The printPriQ method iterates through all objects in the priQ array up until a given index and
	 * prints out a Node report for each of them utilizing Node.printNode
	 * (see Node.java). This is done from the object with the most priority to
	 * the object with the least priority within the range given.
	 */
	public void printPriQ(int num) {

		System.out.println("Name                                 GDPPC");
		System.out.println("-------------------------------------------");
		for (int i = 0; i < num; i++) {

			if (priQArr[i] == null)
				break;
			priQArr[i].printNode();
		}

	}

}
