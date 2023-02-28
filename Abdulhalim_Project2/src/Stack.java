/**
 * This class was created in order for us to have an object capable of storing Country objects (see Country.java)
 * within an array, replicating the behavior of a stack, abstractly. This means being able to push Country
 * objects onto a stack, pop them off said stack, as well as checking whether the stack is empty or full.
 * A Stack constructor must be given a size in order to provide the stack with a proper maximum size. 
 * Stack objects also contain a top value that keeps track of where within the array the top of the stack
 * is located.
 * 
 * @author Adam Abdulhalim
 * @version 10/3/22
 */
public class Stack {
	
	private Country stackArr[];
	private int maxSize;
	private int top;
	
	public Stack(int size) {
		
		maxSize = size;
		stackArr = new Country[maxSize];
		top = -1;
	}
	
	
	/**
	 * The push method takes in a Country object parameter and then pushes this object onto the 
	 * top of the stack array. The parameter name is pushedCountry.
	 * 
	 * @param pushedCountry This parameter provides the method with the Country object that is to be 
	 * pushed onto the top of the stack array.
	 */
	public void push(Country pushedCountry) {

		stackArr[++top] = pushedCountry;
		
	}
	
	
	/**
	 * The pop method pops out the Country object at the top of the stack array. This is done by lowering
	 * the "top" value by 1, therefore rendering the previous top value unreachable. We then return the
	 * popped value, in case we require it for some reason before popping it.
	 * 
	 * @return The Country value of the object previously at the top of the stack is returned.
	 */
	public Country pop() {
		
		System.out.println("\nOne country has been popped from the stack.\n");
		
		return stackArr[top--];
		
	}
	
	
	/**
	 * The printStack method iterates through all objects in the stack array and prints out a country report
	 * for each of them utilizing Country.printCountryLn (see Country.java). This is done from the top of the 
	 * stack to the bottom of the stack.
	 */
	public void printStack() {
		
		System.out.println("Name                             Capitol           GDPPC      CFR         CaseRate    DeathRate  PopDensity");
		System.out.println("-----------------------------------------------------------------------------------------------------------");
		for(int i = top; i >= 0; i--) {
			
			stackArr[i].printCountryLn();
		}
		
	}
	
	
	/**
	 * The isEmpty method checks whether or not the top value of the stack is equal to -1, which would
	 * mean it is empty. We then return a boolean value, true if empty, false if not. 
	 * 
	 * @return Return a boolean value, true if stack is empty, false if not. 
	 */
	public boolean isEmpty() {
	
		return (top == -1);
		
	}
	
	
	/**
	 * The isFull method checks whether or not the top value of the stack is equal to maxSize - 1, 
	 * which would mean it is full. We then return a boolean value, true if full, false if not. 
	 * 
	 * @return Return a boolean value, true if stack is full, false if not. 
	 */
	public boolean isFull() {
	
		return (top == maxSize - 1);
		
	}
}
