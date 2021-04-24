/**
 * COMP 410
 * See in line comment descriptions for methods we need that are
 * not described in the interface.
 *
*/
package assignment1;

public class StackSet implements StackSet_Interface {
  private Node head;  		//this will be the entry point to your linked list 
                      		//( it points to the first data cell, the top for a stack )
  private final int limit;  //defines the maximum size the stack set may contain
  private int size;  		//current count of elements in the stack set 
  
  public StackSet( int maxNumElts ){ //this constructor is needed for testing purposes. 
    head = null;                 //Please don't modify what you see here!
    limit = maxNumElts;          //you may add fields if you need to
    size = 0;
  }
  
  public boolean push(double elt) {
	  // Checks if the stack is full and does not contain
	  // the value elt within the stack.
	  if (isFull() && !contains(elt)) {
		  return false;
	  }
	  
	  // Tests for if the stack contains the value elt
	  // Reorganizes stack by removing Node containing elt value
	  // and moving it to the front.
	  if (contains(elt)) {
		  // Check condition if the head value is equal to elt.
		  if (head.getValue() == elt) {
			  return true;
		  }
		  
		  Node prevNode = head;
		  Node replaceNode = null;
		  int counterPrev = 0;
		  boolean replacedCon = false;
		  
		  // While loop will run until the counter reaches the second to last Node OR
		  // if the delinking has occurred resulting in a switch turned on. 
		  while (counterPrev < size()-1 && !replacedCon) {
			  // Checks if the next Node has the desired value. 
			  // Sets a pointer to the this Node.
			  if (prevNode.getNext().getValue() == elt) {
				  replaceNode = prevNode.getNext();
				  // These statements will look to see if the next 
				  // Node in the stack from the target is null or not.
				  if (prevNode.getNext().getNext() == null) {
					  prevNode.setNext(null);
				  }
				  else {
					  prevNode.setNext(prevNode.getNext().getNext());
				  }
				  // Pointer is linked to the head.
				  // Switch turned on, while loop stops.
				  replaceNode.setNext(head);
				  replacedCon = true;
			  }
			  prevNode = prevNode.getNext();
			  counterPrev++;
		  }
		  head = replaceNode;
		  return true;
	  }
	  
	  // Last condition, does not contain the value within the stack;
	  // Will create a new Node and append it to the stack. 
	  Node newHead = new NodeImpl (elt, head);
	  head = newHead;
	  size++;
	  return true;
  }
  
  
  // This method will set the stack to where the
  // head of the stack is removed. If the stack contains
  // zero elements than it will return false.
  public boolean pop() {
	  if (isEmpty()) {
		  return false;
	  }
	  head = head.getNext();
	  size--;
	  return true;
  }
  
  // Will return the value of the top of the stack. 
  public double peek() {
	  if (size() >= 1) {
		  return head.getValue();
	  }
	  return Double.NaN;
  }
  
  // Will return true or not if the stack contains the value.
  // Utilizes the findNode method.
  public boolean contains(double elt) {
	  Node parseNode = head;
	  int sizeCounter = 0;
	  while (sizeCounter < size()) {
		  if (parseNode.getValue() == elt) {
			  return true;
		  }
		  sizeCounter++;
		  parseNode = parseNode.getNext();
	  }
	  return false;
  }
  
  // Getter method for the size of the stack.
  public int size() {
	 return size;
  }
  
  // Getter method for the limit of the stack.
  public int limit() {
	 return limit;
  }
  
  // Getter methods that checks the state of the stack.
  public boolean isEmpty() {
	  if (size() == 0) {
		  return true;
	  }
	  return false;
  }
  
  // Returns the condition if the stack has reached the limit of cells.
  public boolean isFull() {
	  if (size() == limit()) {
		  return true;
	  }
	  return false;
  }

  
  public Node getRoot(){ //leave this method as is, used by the grader to grab 
    return head;         //your data list easily.
  }

}