/**
 * This class was made in order for us to create a stack using Link objects, and thus utilizing a linked list. This stack would
 * hold Link objects that contain Country object data within them as well, thus storing a plethora of different country data. The
 * Stack object itself contains two specific reference variables, the first and last Links. This allows us to create a double-ended
 * linked list stack. When calling the Stack constructor, both the first and last are set to null to start. We have different
 * methods to push new Country object data onto the stack, pop said data out and return it, print all the country objects within,
 * check if the stack is empty, as well as getters/setters for both first and last. 
 * 
 * 
 * @author Adam Abdulhalim
 * @version 10/27/22
 */
public class Stack {
	private Link first; 
	private Link last; 
	
	public Stack() {
		first = null;
		last = null;
	}
	
	/**
	 * The isEmpty method checks whether or not the stack is empty by checking if the first value is null or not. A boolean value
	 * is returned; true if empty, false if not. 
	 * 
	 * @return The boolean value corresponding to whether or not the stack is empty is returned.
	 */
	public boolean isEmpty() { 
		
		return first==null; 
	}
	
	/**
	 * The push method takes in a Country object as input, creates a new Link object containing this country data, and pushes
	 * this link onto the top of the stack linked list. We check if the stack was previously empty, and if so, we set the last
	 * value to the new link as well. We set the new link's next value to the previous first value, whether it was null or not. 
	 * 
	 * @param dd This parameter provides the Country object that we want to push onto the stack. 
	 */
	public void push(Country dd) {
	
		Link newLink = new Link(dd);
	
		if( isEmpty() ) { 
			last = newLink;
		}
	
		newLink.next = first; 
		first = newLink; 
	}
	
	/**
	 * The pop method pops off the first value of the stack, or in other words, the link that is at the top of the stack. We
	 * check if this is the last link left, and if it is we set the last value to null, in addition. We then set the new first 
	 * value to the popped off link's next value. Finally we return the popped off Country value (utilizing a temporary variable
	 * we create at the beginning of the process).
	 * 
	 * @return The Country object that is getting popped off the stack.
	 */
	public Country pop() { 
		Country temp = first.country;
	
		if(first.next == null) { 
	
		last = null; 
		}
		first = first.next;
		return temp;
	}
	
	/**
	 * The printStack method utilizes Country.printCountryLn in order to recursively print all of the Country objects within the 
	 * stack. First we must pass the first link of the stack into the printStack method. Then we print the current link's Country
	 * and call the next link using a printStack recursive case. Once we reach the final link where there is no next value (base
	 * case) we print the final country and return.
	 * 
	 * @param link This parameter provides the first link in the Stack so that we can start printing from the beginning. 
	 */
	public void printStack(Link link) {
		
		if(link.next == null) {
			link.country.printCountryLn();
			return;
		}
		
		else {
			link.country.printCountryLn();
			printStack(link.next);
		}
	}
	
	/**
	 * The getFirst method was made for us to be able to utilize the first link reference value in other classes (like main).
	 * This method was mainly necessary so that we could access the first link and pass it to the printStack method without 
	 * changing the access modifier of first to public. 
	 * 
	 * @return The first link in the stack.
	 */
	public Link getFirst() {
		return first;
	}

	/**
	 * The setFirst method allows us to manually set the first value of the stack if ever need be. 
	 * 
	 * @param first This parameter provides the link with which we want to set the first stack value to. 
	 */
	public void setFirst(Link first) {
		this.first = first;
	}

	/**
	 * The getLast method returns the last value in the stack.
	 * 
	 * @return The last link in the stack.
	 */
	public Link getLast() {
		return last;
	}

	/**
	 * The setLast method allows us to manually set the last value of the stack if ever need be. 
	 * 
	 * @param last This parameter provides the link with which we want to set the last stack value to. 
	 */
	public void setLast(Link last) {
		this.last = last;
	}

}
