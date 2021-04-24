package assignment4_f20;

public class MinBinHeap implements Heap {
  private CacheFrame[] array; // load this array
  private int size;      // how many items currently in the heap
  private int arraySize; // Everything in the array will initially
                         // be null. This is ok! Just build out
                         // from array[1]

  public MinBinHeap(int nelts) {
    this.array = new CacheFrame[nelts+1];  // remember we dont use slot 0
    this.arraySize = nelts+1;
    this.size = 0;
    this.array[0] = new CacheFrame(null, 0); // 0 not used, so this is arbitrary
  }

  // Please do not remove or modify this method! Used to test your entire Heap.
  @Override
  public CacheFrame[] getHeap() { return this.array; }

  //===============================================================
  //
  // here down you implement the ops in the interface
  //
  //===============================================================
  public void insert(CacheFrame elt) {
	  size++;
	  if (size == 1) {
		  array[1] = elt;
		  array[1].setSlot(1);
		  return;
	  }
	  int newPos = size;
	  array[newPos] = elt;
	  array[newPos].setSlot(newPos);
	  
	  int parentPos = 0;
	  if (newPos % 2 == 1) {
		  parentPos = newPos - 1;
		  parentPos = parentPos / 2;
	  }
	  else {
		  parentPos = newPos / 2;
	  }
	  
	  while (parentPos >= 1 && array[parentPos].getPriority() > array[newPos].getPriority()) {
		  CacheFrame tempCache = array[parentPos];
		  array[parentPos] = array[newPos];
		  array[newPos] = tempCache;
		  array[parentPos].setSlot(parentPos);
		  array[newPos].setSlot(newPos);
		  
		  newPos = parentPos;
		  if (newPos % 2 == 1) {
			  parentPos = newPos - 1;
			  parentPos = parentPos / 2;
		  }
		  else {
			  parentPos = newPos / 2;
		  }
	  }
  }
  
  public void delMin() {
	  if (size == 0) {
		  return;
	  }
	  if (size == 1) {
		  array[0] = null;
		  size--;
		  return;
	  }
	  array[1] = array[size];
	  array[size] = null;
	  array[1].setSlot(1);
	  
	  int newPos = 1;
	  int leftPos = newPos * 2;
	  int rightPos = leftPos +1;
	  
	  size--;
	  
	  while ((leftPos <= size && rightPos <= size) &&
			  (array[leftPos].getPriority() < array[newPos].getPriority() ||
					  array[rightPos].getPriority() < array[newPos].getPriority())) {
		  if (array[leftPos].getPriority() < array[newPos].getPriority() &&
					  array[rightPos].getPriority() < array[newPos].getPriority()) {
			  if (array[leftPos].getPriority() <= array[rightPos].getPriority()) {
				  // swap with left child
				  CacheFrame tempCache = array[leftPos];
				  array[leftPos] = array[newPos];
				  array[newPos] = tempCache;
				  array[leftPos].setSlot(leftPos);
				  array[newPos].setSlot(newPos);
				  
				  newPos = leftPos;
			  }
			  else {
				  // swap with right child
				  CacheFrame tempCache = array[rightPos];
				  array[rightPos] = array[newPos];
				  array[newPos] = tempCache;
				  array[rightPos].setSlot(rightPos);
				  array[newPos].setSlot(newPos);
				  
				  newPos = rightPos;
			  }
		  }
		  else if (array[leftPos].getPriority() < array[newPos].getPriority()) {
			  // swap with left child
			  CacheFrame tempCache = array[leftPos];
			  array[leftPos] = array[newPos];
			  array[newPos] = tempCache;
			  array[leftPos].setSlot(leftPos);
			  array[newPos].setSlot(newPos);
			  
			  newPos = leftPos;
		  }
		  else {
			  // swap with right child
			  CacheFrame tempCache = array[rightPos];
			  array[rightPos] = array[newPos];
			  array[newPos] = tempCache;
			  array[rightPos].setSlot(rightPos);
			  array[newPos].setSlot(newPos);
			  
			  newPos = rightPos;
		  }
		  leftPos = newPos * 2;
		  rightPos = leftPos +1;
	  } 
  }
  
  public CacheFrame getMin() {
  	if (size == 0) {
  		return null;
  	}
  		return array[1];
  } 
  
  public int size() {
  	return size;
  }
  
  public void incElt( CacheFrame elt ) {  
	  int newPos = elt.getSlot();
	  
	  array[newPos].setPriority(array[newPos].getPriority() + 1);
	  
	  int leftPos = newPos * 2;
	  int rightPos = leftPos + 1;
	  
	  while ((leftPos <= size && rightPos <= size) &&
			  (array[leftPos].getPriority() < array[newPos].getPriority() ||
					  array[rightPos].getPriority() < array[newPos].getPriority())) {
		  if (array[leftPos].getPriority() < array[newPos].getPriority() &&
					  array[rightPos].getPriority() < array[newPos].getPriority()) {
			  if (array[leftPos].getPriority() <= array[rightPos].getPriority()) {
				  // swap with left child
				  CacheFrame tempCache = array[leftPos];
				  array[leftPos] = array[newPos];
				  array[newPos] = tempCache;
				  array[leftPos].setSlot(leftPos);
				  array[newPos].setSlot(newPos);
				  
				  newPos = leftPos;
			  }
			  else {
				  // swap with right child
				  CacheFrame tempCache = array[rightPos];
				  array[rightPos] = array[newPos];
				  array[newPos] = tempCache;
				  array[rightPos].setSlot(rightPos);
				  array[newPos].setSlot(newPos);
				  
				  newPos = rightPos;
			  }
		  }
		  else if (array[leftPos].getPriority() < array[newPos].getPriority()) {
			  // swap with left child
			  CacheFrame tempCache = array[leftPos];
			  array[leftPos] = array[newPos];
			  array[newPos] = tempCache;
			  array[leftPos].setSlot(leftPos);
			  array[newPos].setSlot(newPos);
			  
			  newPos = leftPos;
		  }
		  else {
			  // swap with right child
			  CacheFrame tempCache = array[rightPos];
			  array[rightPos] = array[newPos];
			  array[newPos] = tempCache;
			  array[rightPos].setSlot(rightPos);
			  array[newPos].setSlot(newPos);
			  
			  newPos = rightPos;
		  }
		  leftPos = newPos * 2;
		  rightPos = leftPos +1;
	  } 
  }
  	
  public void decElt( CacheFrame elt ) {
	  int newPos = elt.getSlot();
	  
	  if (elt.getPriority() == 1) {
		  return;
	  }
	  
	  array[newPos].setPriority(array[newPos].getPriority() - 1);
	  
	  int parentPos = 0;
	  if (newPos % 2 == 1) {
		  parentPos = newPos - 1;
		  parentPos = parentPos / 2;
	  }
	  else {
		  parentPos = newPos / 2;
	  }
	  
	  while (parentPos >= 1 && array[parentPos].getPriority() > array[newPos].getPriority()) {
		  CacheFrame tempCache = array[parentPos];
		  array[parentPos] = array[newPos];
		  array[newPos] = tempCache;
		  array[parentPos].setSlot(parentPos);
		  array[newPos].setSlot(newPos);
		  
		  newPos = parentPos;
		  if (newPos % 2 == 1) {
			  parentPos = newPos - 1;
			  parentPos = parentPos / 2;
		  }
		  else {
			  parentPos = newPos / 2;
		  }
	  }
  }
}