/**
 * This class was made in order for us to be able to create a priority queue utilizing a linked list. Since we only need the
 * priority queue to be singly-ended, we only have a first variable link, unlike Stack.java. The constructor for the PriorityQ
 * simply sets this first value to null. We have many other methods that do other priority queue related operations, such as
 * inserting links based on priority order, removing/deleting links from the beginning or end of the queue, removing links that
 * are not at the beginning or end, removing a link that is the only link left, accepting an interval of links to delete based
 * on country death rate, printing the PriorityQ, checking if the queue is empty, and getting/setting the first value reference.
 * 
 * @author Adam Abdulhalim
 * @version 10/27/22
 */
public class PriorityQ {
	private Link first;
	
	public PriorityQ() {
		first = null;
	}
	
	/**
	 * The isEmpty method checks whether or not the priority queue is empty by checking if the first value is null or not.
	 * A boolean value is returned; true if empty, false if not. 
	 * 
	 * @return The boolean value corresponding to whether or not the priority queue is empty is returned.
	 */
	public boolean isEmpty() { 
		
		return first==null; 
	}
	
	/**
	 * The insert method takes in a Country object value and inserts it into the priority queue linked list. It does this by
	 * putting said country value into a link and linking this object with the rest of the priority queue based on how much
	 * priority it should have given its death rate value. 
	 * 
	 * @param key The Country object that is being inserted into the priority queue. 
	 */
	public void insert(Country key) {
	
		Link newLink = new Link(key); 
		Link previous = null; 
		Link current = first;

		while(current != null && key.getDeathRate() > current.country.getDeathRate()) {
	
			previous = current;
			current = current.next;
		}
	
		if(previous==null)
			first = newLink; 
	
		else 
			previous.next = newLink; 
		newLink.next = current; 
		newLink.prev = previous;
		if(current != null)
			current.prev = newLink;
	} 
	
	/**
	 * The remove method simply removes the link containing the country value with the highest priority (the first link).
	 * We then return this country value.
	 * 
	 * @return The country value with the highest priority that is being removed. 
	 */
	public Country remove() { 
		Country temp = first.country;
	
		first = first.next;
		first.prev = null;
		return temp;
	}
	
	/**
	 * The removeMid method removes a link from somewhere in the middle of the queue (just not the first or last link).
	 * We must specify which link to remove by passing said link as a parameter. 
	 * 
	 * @param current The current link that is to be removed.
	 * @return The country value of the removed link. 
	 */
	public Country removeMid(Link current) {
		Country temp = current.country;
		
		current.prev.next=current.next; 
		current.next.prev=current.prev;
		
		return temp;
	}
	
	/**
	 * The removeFinal method is used to remove a link when it is the only link left in the priority queue. This was necessary
	 * due to null pointer restraints caused by the other removal methods in this specific scenario. 
	 * 
	 * @param last The last link in the queue that is to be removed. 
	 * @return The country value of the removed link.
	 */
	public Country removeFinal(Link last) {
		Country temp = last.country;
		
		first = null;
		
		return temp;
	}
	
	/**
	 * The removeLastNotFinal method is used to remove the last link in a priority queue when there are still other links within
	 * the queue left. 
	 * 
	 * @param last The last link in the queue that is to be removed. 
	 * @return The country value of the removed link.
	 */
	public Country removeLastNotFinal(Link last) {
		Country temp = last.country;
		
		last.prev.next = null;
		
		return temp;
	}
	
	/**
	 * The intervalDelete method is used to go about deleting any links within the priority queue that contains countries with 
	 * death rates within a specified range given by user input. intervalDelete utilizes the four removal methods in order
	 * to delete said links if it is deemed necessary by the user's inputed deletion range. A boolean value is returned from 
	 * this method, true if something was deleted, false if not. 
	 * 
	 * @param pq The PriorityQ object that we want to check for deletion.
	 * @param lower The lower bound of the deletion range.
	 * @param upper The upper bound of the deletion range. 
	 * @return A boolean value, true if a link or multiple links were deleted, false if not. 
	 */
	public boolean intervalDelete(PriorityQ pq, double lower, double upper) {
		boolean deleted = false;
		
		Link current = first;
		
		while(current != null) {
			
			if(current.country.getDeathRate() >= lower && current.country.getDeathRate() <= upper) {
				
				if(current.prev == null && current.next != null) {
					pq.remove();
					deleted = true;
				}
				
				else if(current.next == null && current.prev != null) {
					pq.removeLastNotFinal(current);
					deleted = true;
				}
				
				else if(current.next == null) {
					pq.removeFinal(current);
					deleted = true;
				}
				
				else {
					pq.removeMid(current);
					deleted = true;
				}
				
			}
			
			current = current.next;
		}
		
		
		return deleted;
	}
	
	/**
	 * The printPriorityQ method utilizes Country.printCountryLn in order to recursively print all of the Country objects within
	 * the queue. First we must pass the first link of the queue into the printPriorityQ method. Then we print the current link's
	 * Country and call the next link using a printPriorityQ recursive case. Once we reach the final link where there is no next 
	 * value (base case) we print the final country and return.
	 * 
	 * @param link This parameter provides the first link in the PriorityQ so that we can start printing from the beginning. 
	 */
	public void printPriorityQ(Link link) {
		
		if(link.next == null) {
			link.country.printCountryLn();
			return;
		}
		
		else {
			link.country.printCountryLn();
			printPriorityQ(link.next);
		}
	}

	/**
	 * The getFirst method was made for us to be able to utilize the first link reference value in other classes (like main).
	 * This method was mainly necessary so that we could access the first link and pass it to the printPriorityQ method without 
	 * changing the access modifier of first to public. 
	 * 
	 * @return The first link in the priority queue.
	 */
	public Link getFirst() {
		return first;
	}

	/**
	 * The setFirst method allows us to manually set the first value of the priority queue if ever need be. 
	 * 
	 * @param first This parameter provides the link with which we want to set the first queue value to. 
	 */
	public void setFirst(Link first) {
		this.first = first;
	}
	
}
