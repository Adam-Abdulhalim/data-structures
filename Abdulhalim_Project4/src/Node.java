/**
 * This class was created in order for us to have an object to store the data
 * and child references for the leaves in our Binary Search Tree. These "Node"
 * objects store data for country name strings, country GDPPC doubles, and two
 * Node references for left and right children. It also contains a printNode()
 * method used to print the name and GDPPC values for the specified node.
 * 
 * @author Adam Abdulhalim
 * @version 11/18/22
 */
public class Node {

	public String cName;
	public double GDPPC;
	public Node leftChild;
	public Node rightChild;

	public Node(String name, double gdppc) {
		this.cName = name;
		this.GDPPC = gdppc;
	}

	/**
	 * The printNode method prints out the name and GDPPC for any given Node that
	 * it is called on.
	 */
	public void printNode() {
		System.out.printf("%-32s %10.3f\n", cName, GDPPC);
	}
}
