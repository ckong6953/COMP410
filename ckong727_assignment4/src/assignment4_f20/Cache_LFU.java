package assignment4_f20;

import java.util.HashMap;

public class Cache_LFU implements Cache {
  
  HashMap<String, CacheFrame> map; 
    // allocate from java collections lib
    // do it this way so we all start with default size and 
    // default lambda and default hash function for string keys
  MinBinHeap heap; // your own heap code above
  int limit;       // max num elts the cache can hold
  int size;        // current number elts in the cache
  
  Cache_LFU (int maxElts) {
    this.map = new HashMap<String, CacheFrame>();
    this.heap = new MinBinHeap(maxElts);
    this.limit = maxElts;
    this.size = 0;
  }
  
  // dont change this we need it for grading
  public MinBinHeap getHeap() { return this.heap; }
  public HashMap getHashMap() { return this.map; }
  
  // =========================================================
  //
  // you fill in code for the other ops in the interface
  //
  //==========================================================
  public int size() {
	  return limit;
  }
  public int numElts() {
	  return size;
  }
  public boolean isFull() {
	  if (limit == size) {
		  return true;
	  }
	  return false;
  }
  public boolean refer( String address ) {
	  if(map.containsKey(address)){
		  heap.incElt(map.get(address));
		  return true;
	  }
	  
	  if (heap.size() < limit) {
		  CacheFrame newFrame = new CacheFrame(address, 1);
		  heap.insert(newFrame);
		  map.put(address, newFrame);
		  size++;
		  return false;
	  }
	  
	  CacheFrame minStore = heap.getMin();
	  heap.delMin();
	  map.remove(minStore.getValue());
	  CacheFrame newFrame = new CacheFrame(address, 1);
	  heap.insert(newFrame);
	  map.put(address,newFrame);
	  return false;
  }
}
	  
