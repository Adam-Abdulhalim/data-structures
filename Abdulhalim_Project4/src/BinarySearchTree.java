/**
 * This class was created in order for us to be able to store Node objects (see Node.java) in a Binary Search Tree format.
 * The BinarySearchTree class contains methods to insert and delete nodes, find and print a path to a node, get a node's 
 * successor, print the nodes in inOrder, in preOrder, in postOrder, print the bottom X nodes in relation to their GDPPC value,
 * and print the top X nodes in relation to their GDPPC value. All of these operations allow us to properly utilize the Binary
 * Search Tree as well as do what is necessary in the Project4.java main class.
 * 
 * 
 * @author Adam Abdulhalim
 * @version 11/18/22
 */
public class BinarySearchTree {

	public Node root;

	public BinarySearchTree() {
		root = null;
	}

	/**
	 * The insert method allows us to insert a node object into our Binary Search Tree. We do this through utilizing
	 * the Comparable interface's compareTo method, comparing the name values of each node so that we can store them
	 * alphabetically (inOrder). If a node's name is a lesser value, lexicographically, compared to its parent, then 
	 * it is stored in the left child. If it is a greater value, then the right child.
	 * 
	 * @param name This parameter passes along the String value of the name of the country node.
	 * @param GDPPC This parameter passes along the double value of the GDPPC of the country node.
	 */
	public void insert(String name, double GDPPC) {
		Node newNode = new Node(name, GDPPC); 

		if (root == null) 
			root = newNode;
		else 
		{
			Node current = root; 
			Node parent;
			while (true) 
			{
				parent = current;
				if (name.compareTo(current.cName) < 0) 
				{
					current = current.leftChild;
					if (current == null) 
					{ 
						parent.leftChild = newNode;
						return;
					}
				} 
				else 
				{
					current = current.rightChild;
					if (current == null) 
					{ 
						parent.rightChild = newNode;
						return;
					}
				} 
			} 
		} 
	} 

	/**
	 * The find method is used in order to traverse the Binary Search Tree until we find the specific country node
	 * that we are looking for, which is specified through a string input parameter of the name of the Node. We store
	 * the path to the node in a string and we print it if the node is found. We also return the GDPPC value of the node if 
	 * it is found. If it is not found we don't do either of these, and instead print that the country was not found, and the
	 * value -1 is returned.
	 * 
	 * @param name This parameter passed along the name of the country node that we would like to find.
	 * @return The double value of the GDPPC of the node that we find is returned.
	 */
	public double find(String name) {
		Node current = root; 
		String out = "";
		while (current != null && name.compareTo(current.cName) != 0) 
		{
			if (name.compareTo(current.cName) < 0) {
				out = out.concat(current.cName + " -> ");
				current = current.leftChild; 
			} else {
				out = out.concat(current.cName + " -> ");
				current = current.rightChild; 
			}
			if (current == null) {
				System.out.println("\nCountry not found!\n");
				return -1; 
			}
		} 
		out = out.concat(current.cName);
		System.out.println(out);
		return current.GDPPC; 
	} 

	/**
	 * This delete method allows us to traverse the Binary Search Tree until we find the specified node that we would like to
	 * delete. Once said node is found, we decide between the different cases of said node having no children, having a left
	 * child, having a right child, or both, and cut off the node using the proper method in order to salvage these children.
	 * We also utilize the getSuccessor() method in order to find the successor if a successor needs to take its place in order
	 * to withstand the integrity of the tree.
	 * 
	 * @param key This parameter passes along the name of the country node that we would like to delete.
	 */
	public void delete(String key) 
	{ 
		Node current = root;
		Node parent = root;
		boolean isLeftChild = true;
		while (key.compareTo(current.cName) != 0) 
		{
			parent = current;
			if (key.compareTo(current.cName) < 0) 
			{
				isLeftChild = true;
				current = current.leftChild;
			} else 
			{
				isLeftChild = false;
				current = current.rightChild;
			}
			if (current == null) { 
				System.out.println(key + " not found!");
				return; 
			}
		} 
			
		if (current.leftChild == null && current.rightChild == null) {
			if (current == root) 
				root = null; 
			else if (isLeftChild)
				parent.leftChild = null; 
			else 
				parent.rightChild = null;
		}
		
		else if (current.rightChild == null)
			if (current == root)
				root = current.leftChild;
			else if (isLeftChild)
				parent.leftChild = current.leftChild;
			else
				parent.rightChild = current.leftChild;
		
		else if (current.leftChild == null)
			if (current == root)
				root = current.rightChild;
			else if (isLeftChild)
				parent.leftChild = current.rightChild;
			else
				parent.rightChild = current.rightChild;
		else 
		{
			
			Node successor = getSuccessor(current);
			
			if (current == root)
				root = successor;
			else if (isLeftChild)
				parent.leftChild = successor;
			else
				parent.rightChild = successor;
			
			successor.leftChild = current.leftChild;
		} 
			
		System.out.println(key + " deleted!");
		return; 
	} 

	/**
	 * This method returns the successor of a given node that is to be deleted. It does this
	 * by checking which children are surrounding the deleted node, and chooses accordingly
	 * given the successor rules.
	 * 
	 * @param delNode This gives the method the reference of the node that is to be deleted.
	 * @return The method returns the reference to the proper successor of the deleted node.
	 */
	private Node getSuccessor(Node delNode) {
		Node successorParent = delNode;
		Node successor = delNode;
		Node current = delNode.rightChild; 
		while (current != null) 
		{ 
			successorParent = successor;
			successor = current;
			current = current.leftChild; 
		}
		
		if (successor != delNode.rightChild) 
		{ 
			successorParent.leftChild = successor.rightChild;
			successor.rightChild = delNode.rightChild;
		}
		return successor;
	}

	/**
	 * The printInorder method traverses throughout the entire Binary Search Tree recursively utilizing the inOrder
	 * pathing (LNR) and prints all the nodes within in said order. The first call of this method must pass the root of 
	 * the Binary Search Tree as a parameter in order for the entire tree to be printed correctly.
	 * 
	 * @param localRoot The localRoot value passes where we need the method to begin traversing/printing.
	 */
	public void printInorder(Node localRoot) {
		if (localRoot == null)
			return;
		printInorder(localRoot.leftChild);
		localRoot.printNode();
		printInorder(localRoot.rightChild);
	}

	/**
	 * The printPreorder method traverses throughout the entire Binary Search Tree recursively utilizing the preOrder
	 * pathing (NLR) and prints all the nodes within in said order. The first call of this method must pass the root of 
	 * the Binary Search Tree as a parameter in order for the entire tree to be printed correctly.
	 * 
	 * @param localRoot The localRoot value passes where we need the method to begin traversing/printing.
	 */
	public void printPreorder(Node localRoot) {
		if (localRoot == null)
			return;
		localRoot.printNode();
		printPreorder(localRoot.leftChild);
		printPreorder(localRoot.rightChild);
	}

	/**
	 * The printPostorder method traverses throughout the entire Binary Search Tree recursively utilizing the postOrder
	 * pathing (LRN) and prints all the nodes within in said order. The first call of this method must pass the root of 
	 * the Binary Search Tree as a parameter in order for the entire tree to be printed correctly.
	 * 
	 * @param localRoot The localRoot value passes where we need the method to begin traversing/printing.
	 */
	public void printPostorder(Node localRoot) {
		if (localRoot == null)
			return;
		printPostorder(localRoot.leftChild);
		printPostorder(localRoot.rightChild);
		localRoot.printNode();
	}

	/**
	 * The inOrderTransferHigh method traverses through the Binary Search Tree in an inOrder pathing, and along
	 * said path it inserts each Node into a priority queue with the highest GDPPC values having the most priority.
	 * 
	 * @param localRoot The localRoot value passes where we need the method to begin traversing/printing.
	 * @param pq This passes the PriorityQ2 object that we are using to order the BST values by GDPPC.
	 */
	public void inOrderTransferHigh(Node localRoot, PriorityQ2 pq) {
		if (localRoot == null)
			return;
		inOrderTransferHigh(localRoot.leftChild, pq);
		pq.insertHigh(localRoot);
		inOrderTransferHigh(localRoot.rightChild, pq);
	}

	/**
	 * The printTopCountries method takes in user input for the number of countries with the highest GDPPC
	 * in the Binary Search Tree that they would like to print out, and then it prints them out. It utilizes
	 * the inOrderTransferHigh method to order the nodes accordingly from highest to lowest, and the the printPriQ
	 * method to print out X amount of nodes from the top of the queue.
	 * 
	 * @param localRoot The localRoot value passes where we need the method to begin traversing/printing.
	 * @param pq This passes the PriorityQ2 object that we are using to order the BST values by GDPPC.
	 * @param num This is the number of countries that the user would like to print out. 
	 */
	public void printTopCountries(Node localRoot, PriorityQ2 pq, int num) {
		inOrderTransferHigh(localRoot, pq);

		pq.printPriQ(num);
	}

	/**
	 * The inOrderTransferHigh method traverses through the Binary Search Tree in an inOrder pathing, and along
	 * said path it inserts each Node into a priority queue with the lowest GDPPC values having the most priority.
	 * 
	 * @param localRoot The localRoot value passes where we need the method to begin traversing/printing.
	 * @param pq This passes the PriorityQ2 object that we are using to order the BST values by GDPPC.
	 */
	public void inOrderTransferLow(Node localRoot, PriorityQ2 pq) {
		if (localRoot == null)
			return;
		inOrderTransferLow(localRoot.leftChild, pq);
		pq.insertLow(localRoot);
		inOrderTransferLow(localRoot.rightChild, pq);
	}

	/**
	 * The printTopCountries method takes in user input for the number of countries with the lowest GDPPC
	 * in the Binary Search Tree that they would like to print out, and then it prints them out. It utilizes
	 * the inOrderTransferLow method to order the nodes accordingly from lowest to highest, and the the printPriQ
	 * method to print out X amount of nodes from the top of the queue.
	 * 
	 * @param localRoot The localRoot value passes where we need the method to begin traversing/printing.
	 * @param pq This passes the PriorityQ2 object that we are using to order the BST values by GDPPC.
	 * @param num This is the number of countries that the user would like to print out. 
	 */
	public void printBottomCountries(Node localRoot, PriorityQ2 pq, int num) {
		inOrderTransferLow(localRoot, pq);

		pq.printPriQ(num);
	}

}
