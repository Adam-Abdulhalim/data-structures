/**
 * This class was created in order for us to have an object capable of storing Country objects (see Country.java)
 * within an array, replicating the behavior of a priority queue, abstractly. This means being able to insert Country
 * objects onto a queue in an order of priority (in this case, through the CFR of each Country object, 
 * remove them off the queue in said order of priority, as well as checking whether the queue is empty or full.
 * A PriorityQ constructor must be given a size in order to provide the queue with a proper maximum size. 
 * PriorityQ objects also contain a nItems value that keeps track of how many items are in the queue.
 * 
 * @author Adam Abdulhalim
 * @version 10/3/22
 */
public class PriorityQ {
	
	private Country priQArr[];
	private int maxSize;
	private int nItems;
	
	public PriorityQ(int size) {
		
		maxSize = size;
		priQArr = new Country[maxSize];
		nItems = 0;
	}
	
	/**
	 * The insert method takes in a Country object as a parameter and then inserts it into the priority
	 * queue in its proper place determined by the value of priority. We iterate through the array,
	 * shifting over values that are less than the new value until we find where the new value must be placed.
	 * 
	 * @param item This parameter provides the method with the Country object that needs to be inserted
	 * into the priority queue.
	 */
	public void insert(Country item) {

		int j;
		
		if(nItems==0) {
			
			priQArr[nItems++] = item;
		}
		
		else {
			
			for(j=nItems-1; j>=0; j--) {
				
				if(item.getCFR() > priQArr[j].getCFR()) {
					
					priQArr[j+1] = priQArr[j];
				}
				
				else {
					
					break;
				
				}
			}
			priQArr[j+1] = item;
			nItems++;
		}
	}
	
	/**
	 * The remove method lowers the nItems variable of the PriorityQ by 1, thus rendering the previous
	 * object with the most priority unreachable and "removed." We then return said object in case we 
	 * need to use it before completely getting rid of it. 
	 * 
	 * @return The Country value of the object previously at the front of the queue is returned.
	 */
	public Country remove() {

		System.out.println("\nOne country has been removed from the priority queue.\n");
		
		return priQArr[--nItems];
	}
	
	/**
	 * The printPriQ method iterates through all objects in the stack array and prints out a country report
	 * for each of them utilizing Country.printCountryLn (see Country.java). This is done from the object
	 * with the most priority to the object with the least priority.
	 */
	public void printPriQ() {
		
		System.out.println("Name                             Capitol           GDPPC      CFR         CaseRate    DeathRate  PopDensity");
		System.out.println("-----------------------------------------------------------------------------------------------------------");
		for(int i = nItems - 1; i >= 0; i--) {
			
			priQArr[i].printCountryLn();
		}
		
	}
	
	/**
	 * The isEmpty method checks whether or not the number of items in the queue is equal to 0, which would
	 * mean it is empty. We then return a boolean value, true if empty, false if not. 
	 * 
	 * @return Return a boolean value, true if stack is empty, false if not. 
	 */
	public boolean isEmpty() {
		
		return (nItems == 0);
		
	}
	
	/**
	 * The isFull method checks whether or not the number of items in the queue is equal to maxSize, 
	 * which would mean it is full. We then return a boolean value, true if full, false if not. 
	 * 
	 * @return Return a boolean value, true if stack is full, false if not. 
	 */
	public boolean isFull() {
	
		return (nItems == maxSize);
		
	}
}
