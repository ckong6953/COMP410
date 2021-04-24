package assignment2_f20;

//--------------------------------------------------------------

public class TreeMap_imp implements TreeMap { 
  TMCell root;
  String[] inOrderKeys;
  int treeSize;
  int getCount;
  // add fields as you need to in order to provide the required behavior
  // also you may add private methods to assist you as needed
  // in implementing
  
  //-------------------------------------------------------------

  TreeMap_imp () { 
    root = null; 
    inOrderKeys = null;
    treeSize = 0;
    getCount = 0;
    
    // for added fields you can add appropriate initialization code here
  }

  //-------------------------------------------------------------

  // dont change, we need this for grading
  @Override
  public TMCell getRoot() { return this.root; }
  
  //-------------------------------------------------------------
  // "height" is a complete implementation 
  // of the public interface method height
  // it is here to illustrate fr you how the textbook sets up 
  // the method implementation as recursion
  // you may include this in your project directly

  public int height() { // public interface method signature
  // this method is the public interface method
  // it is not recursive, but it calls a recursive
  // private method and passes it access to the tree cells
    return height_r(this.getRoot());
  }
  private int height_r(TMCell c) { 
  // inner method with different signature
  // this helper method uses recursion to traverse
  // and process the recursive structure of the tree of cells
    if (c==null) return -1;
    int lht = height_r(c.getLeft());
    int rht = height_r(c.getRight());
    return Math.max(lht,rht) + 1;
  }
  
  //-------------------------------------------------------------
  // here down... you fill in the implementations for
  // the other interface methods
  //-------------------------------------------------------------
  //
  // remember to implement the required recursion as noted
  // in the interface definition
  //
  //-------------------------------------------------------------
  
  // Provides function to find the maximum key
  // has helper method maxKey_r
  public String maxKey() {
	  return maxKey_r (root);
  }
  
  private String maxKey_r (TMCell node) {
	  if (size() == 0) {
		  return null;
	  }
	  if (node.getRight() == null) {
		  return node.getKey();
	  }
	  return maxKey_r(node.getRight());
  }
  
  // Provides function to find the minimum key
  // has helper method minKey_r
  public String minKey() {
	  return minKey_r (root);
  }
  
  private String minKey_r (TMCell node) {
	  if (size() == 0) {
		  return null;
	  }
	  if (node.getLeft() == null) {
		  return node.getKey();
	  }
	  return minKey_r(node.getLeft());
  }
  
  // Outputs an array of the in-order transversal of BST
  // has helper method getKeys_r
  public String[] getKeys() {
	  inOrderKeys = new String[size()];
	  getKeys_r(root);
	  getCount = 0;
	  return inOrderKeys;
  	
  }
  
  private void getKeys_r(TMCell node) {
	if (node == null) {
		return;
	}  
	getKeys_r(node.getLeft());
	inOrderKeys[getCount] = node.getKey();
	getCount++;
	getKeys_r(node.getRight());
	
  }
  
  // Simply returns the current size of the BST
  public int size() {
	  return treeSize;
  }
  
  // Returns whether or not BST has the desired key
  // utilizes helper method hasKey_r
  public boolean hasKey(String k) {
	  if (k == null) {
		  return false;
	  }
	  return hasKey_r(root, k);
  }
  
  private boolean hasKey_r(TMCell node, String k) {
	  if (size() == 0) {
		  return false;
	  }
	  if(node.getKey().compareTo(k) == 0) {
		  return true;
	  }
	  if (node.getKey().compareTo(k) < 0) {
		  if (node.getRight() == null) {
			  return false;
		  }
		  else {
			  return hasKey_r(node.getRight(), k);
		  }
	  }
	  else if (node.getKey().compareTo(k) > 0) {
		  if (node.getLeft() == null) {
			  return false;
		  }
		  else {
			  return hasKey_r(node.getLeft(),k);
		  }
	  }
	  return hasKey_r(node,k);
  }
  
  // A getter method for the BST simply given the desired key
  // has helper method get_r
  public Value get(String k) {
	  if (k == null) {
		  return null;
	  }
	  return get_r(root, k);
  }
  
  private Value get_r(TMCell node, String k) {
	  if (size() == 0){
		  return null;
	  }
	  if (node.getKey().compareTo(k) == 0){
		  return node.getValue();
	  }
	  else if (node.getKey().compareTo(k) < 0) {
		  if (node.getRight() == null) {
			  return null;
		  }
		  else {
			  return get_r (node.getRight(), k);
		  }
	  }
	  else if (node.getKey().compareTo(k) > 0) {
		  if (node.getLeft() == null) {
			  return null;
		  }
		  else {
			  return get_r (node.getLeft(), k);
		  }
	  }
	  return null;
  }
  
  // Setter method for the BST utilizing a k and v.
  // Utilizes helper method put_r
  public Value put(String k, Value v) {
	  if (k == null || v == null) {
		  return null;
	  }
	  return put_r(root, k, v);
  }
  
  public Value put_r(TMCell node, String k, Value v) {
	  if (size() == 0) {
		  root = new TMCell_imp(k,v);
		  treeSize++;
		  return null;
	  }
	  
	  if (node.getKey().compareTo(k) == 0) {
		  Value oldValue = node.getValue();
		  node.setValue(v);
		  return oldValue;
	  }
	  else if (node.getKey().compareTo(k) < 0) {
		  if (node.getRight() == null) {
			  node.setRight(new TMCell_imp(k,v));
			  treeSize++;
			  return null;
		  }
		  else{
			  return put_r(node.getRight(),k,v);
		  }
	  }
	  else if (node.getKey().compareTo(k) > 0) {
		  if (node.getLeft() == null) {
			  node.setLeft(new TMCell_imp(k,v));
			  treeSize++;
			  return null;
		  }
		  else {
			  return put_r(node.getLeft(),k,v);
		  }
	  }
	  return null;
  }
  
  //Setter method that removes the desired key
  //Utilizes helper method remove_r
  // Also has additional helper methods that 
  // find the new node to be inserted after removal (case for 2 children)
  public void remove(String k) {
	if(k == null) {
		return;
	}
	remove_r(root, k);
  }
  
  private void remove_r(TMCell parent_node, String k) {
	  if (parent_node == null) {
		  return;
	  }
	  
	  TMCell left_child = parent_node.getLeft();
	  TMCell right_child = parent_node.getRight();
	  
	  if (parent_node.getKey().compareTo(k) < 0) {
		  if (right_child == null) {
			  return;
		  }
		  else {
			  if(right_child.getKey().compareTo(k) == 0) {
				  if(right_child.getRight() == null && right_child.getLeft() == null) {
					  parent_node.setRight(null);
					  treeSize--;
				  }
				  else if (right_child.getRight() == null) {
					  parent_node.setRight(right_child.getLeft());
					  treeSize--;
				  }
				  else if (right_child.getLeft() == null) {
					  parent_node.setRight(right_child.getRight());
					  treeSize--;
				  }
				  else {
					  TMCell newNode = localMaxL(right_child.getLeft());
					  remove_r (right_child, newNode.getKey());
					  right_child.setKey(newNode.getKey());
					  right_child.setValue(newNode.getValue());
				  }
			  }
			  else {
				  remove_r (right_child, k);
			  }
		  }
	  }
	  else if (parent_node.getKey().compareTo(k) > 0) {
		  if (left_child == null) {
			  return;
		  }
		  else {
			  if (left_child.getKey().compareTo(k) == 0) {
				  if (left_child.getRight() == null && left_child.getLeft() == null) {
					  parent_node.setLeft(null);
					  treeSize--;
				  }
				  else if (left_child.getRight() == null) {
					  parent_node.setLeft(left_child.getLeft());
					  treeSize--;
				  }
				  else if (left_child.getLeft() == null) {
					  parent_node.setLeft(left_child.getRight());
					  treeSize--;
				  }
				  else {
					  TMCell newNode = localMaxL(left_child.getLeft());
					  remove_r (left_child, newNode.getKey());
					  left_child.setKey(newNode.getKey());
					  left_child.setValue(newNode.getValue());
				  }
			  }
			  else {
				  remove_r (left_child, k);
			  }
		  }
	  }
	  else if (parent_node.getKey().compareTo(k) == 0 && parent_node == root) {
		  if (right_child == null && left_child == null) {
			  root = null;
			  treeSize--;
		  }
		  else if (right_child == null) {
			  root = left_child;
			  treeSize--;
		  }
		  else if (left_child == null) {
			  root = right_child;
			  treeSize--;
		  }
		  else {
			  TMCell newNode = localMaxL(left_child);
			  remove_r (parent_node, newNode.getKey());
			  parent_node.setKey(newNode.getKey());
			  parent_node.setValue(newNode.getValue());
		  }
	  }
  }
  
  // These are private helper methods that help
  // the helper method remove_r; L is to look for the
  // max in the left child and R is to look for the min
  // in the right child.
  private TMCell localMaxL (TMCell node) {
	  if (node.getRight() == null) {
		  return node;
	  }
	  return localMaxL(node.getRight());  
  }
  
  /*
  private TMCell localMaxR (TMCell node) {
	  if (node.getLeft() == null) {
		  return node;
	  }
	  return localMaxR(node.getLeft());
  }
  */
  
}