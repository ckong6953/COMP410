package assignment3_f20;

public class HashMap_imp implements HashMap { 
  HMCell[] tab;
  HMCell nextCell;
  int nelts;
  
  //-------------------------------------------------------------

  HashMap_imp (int num) { 
    this.tab = new HMCell[num];
    // for (int i=0; i<num; i++) { tab[i] = null; }
    // we can rely on the Java compiler to fill the table array with nulls
    // another way would be Array.fill()
    this.nelts = 0; 
  }

  //-------------------------------------------------------------
  
  public int hash (String key, int tabSize) {
    int hval = 7;
    for (int i=0; i<key.length(); i++) {
      hval = (hval*31) + key.charAt(i);
    }
    hval = hval % tabSize;
    if (hval<0) { hval += tabSize; }
    return hval;
  }
  
  //-------------------------------------------------------------

  // dont change 
  @Override
  public HMCell[] getTable() { return this.tab; }
  
  //-------------------------------------------------------------

    
  //-------------------------------------------------------------
  // here down... you fill in the implementations for
  // the other interface methods
  //-------------------------------------------------------------
  
  // Methods defined as part of the HashMap interface
  // *excludes hash() and getTable() methods as they are defined above.
  public String maxKey() {
	  if (tab.length == 0) {
		  return null;
	  }
	  String maxKey = null;
	  for (int i = 0; i < tab.length; i++) {
		  if (tab[i] != null) {
			  nextCell = tab[i];
			  if (maxKey == null) {
				  maxKey = nextCell.getKey();
			  }
			  while (nextCell != null) {
				  if (nextCell.getKey().compareTo(maxKey) > 0) {
					  maxKey = nextCell.getKey();
				  }
				  nextCell = nextCell.getNext();
			  }
		  }
	  }
	  return maxKey;
  }
  public String minKey() {
	  if (tab.length == 0) {
		  return null;
	  }
	  String minKey = null;
	  for (int i = 0; i < tab.length; i++) {
		  if (tab[i] != null) {
			  nextCell = tab[i];
			  if (minKey == null) {
				  minKey = nextCell.getKey();
			  }
			  while (nextCell != null) {
				  if (nextCell.getKey().compareTo(minKey) < 0) {
					  minKey = nextCell.getKey();
				  }
				  nextCell = nextCell.getNext();
			  }
		  }
	  }
	  return minKey;
  }
  public String[] getKeys() {
	  
	  String[] key_list = new String[nelts];
	  int list_counter = 0;
	  for (int i = 0; i < tab.length; i++) {
		  if (tab[i] != null) {
			  nextCell = tab[i];
			  while (nextCell != null) {
				  key_list[list_counter] = nextCell.getKey();
				  list_counter++;
				  nextCell = nextCell.getNext();
			  }
		  }
	  }
	  return key_list;
  }
  
  public double lambda() {
	  double calc_lambda = (double)((double)size() / (double)tab.length);
	  return calc_lambda;
  }
  
  public double extend() {
	  HMCell[] old_tab = tab.clone();
	  tab = new HMCell[old_tab.length * 2];
	  for (int i = 0; i < old_tab.length; i++) {
		  if (old_tab[i] != null) {
			  nextCell = old_tab[i];
			  while (nextCell != null) {
				 if (tab[hash(nextCell.getKey(), tab.length)] == null) {
					 tab[hash(nextCell.getKey(), tab.length)] = new HMCell_imp(nextCell.getKey(), nextCell.getValue());
				 }
				 else {
					 HMCell newCell = new HMCell_imp (nextCell.getKey(), nextCell.getValue());
					 newCell.setNext(tab[hash(nextCell.getKey(), tab.length)]);
					 tab[hash(nextCell.getKey(),tab.length)] = newCell;
				 }
				 nextCell = nextCell.getNext();
			  }
		  }
	  }
	  return this.lambda();

  }
  
  // Methods defined as part of the Map interface
  public Value put (String k, Value v) {
	  nextCell = tab[hash(k, tab.length)];
	  if (nextCell == null) {
		  HMCell newCell = new HMCell_imp(k,v);
		  tab[hash(k, tab.length)] = newCell;
		  nelts++;
		  if (lambda() >= 1.0) {
			  extend();
		  }
		  return null;
	  }
	  while (nextCell != null ) {
		  if (nextCell.getKey().compareTo(k) == 0) {
			  Value oldValue = nextCell.getValue();
			  nextCell.setValue(v);
			  return oldValue;
		  }
		  nextCell = nextCell.getNext();
	  }
	  
	  HMCell newCell = new HMCell_imp(k,v);
	  newCell.setNext(tab[hash(k,tab.length)]);
	  tab[hash(k,tab.length)] = newCell;
	  nelts++;
	  
	  if (lambda() >= 1.0) {
		  extend();
	  }
	  
	  return null;
  }
  public Value get (String k) {
	  nextCell = tab[hash(k,tab.length)];
	  if (nextCell == null) {
		  return null;
	  }
	  
	  while (nextCell != null) {
		  if (nextCell.getKey().compareTo(k) == 0) {
			  return nextCell.getValue();
		  }
		  nextCell = nextCell.getNext();
	  }
	  return null;
  }
  
  public void remove (String k) {
	  nextCell = tab[hash(k, tab.length)];
	  if (nextCell == null) {
		  return;
	  }
	  if (nextCell.getKey().compareTo(k) == 0) {
		  tab[hash(k, tab.length)] = nextCell.getNext();
		  nelts--;
		  return;
	  }
	  while (nextCell != null) {
		  if (nextCell.getNext().getKey().compareTo(k) == 0) {
			  nextCell.setNext(nextCell.getNext().getNext());
			  nelts--;
		  }
		  nextCell = nextCell.getNext();
	  }
  }
  
  public boolean hasKey(String k) {
	  nextCell = tab[hash(k, tab.length)];
	  if (nextCell == null) {
		  return false;
	  }
	  while (nextCell != null) {
		  if (nextCell.getKey().compareTo(k) == 0) {
			  return true;
		  }
		  nextCell = nextCell.getNext();
	  }
	  return false;
  }
  public int size() {
	  return nelts;
  }
}