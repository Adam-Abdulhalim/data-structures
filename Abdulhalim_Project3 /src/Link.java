/**
 * This class was made in order for us to create link objects in order to store data within our stack and priority queue
 * linked lists. Using these objects we are able to store Country object data while also linking these objects with each other
 * in order to store data in linked list fashion. Each link is capable of linking both backwards and forwards using the next
 * and prev pointers, though for singly-linked lists only one of these is necessary for linking. The Link constructor requires
 * input of a Country object so that said country data can be stored within the link. 
 * 
 * @author Adam Abdulhalim
 * @version 10/27/22
 */
public class Link {
	
	public Country country; 
	public Link next; 
	public Link prev;
	
	public Link(Country d) 
	{ 
		country = d; 
	}
	
}
