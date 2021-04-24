package assignment4_f20;

public class Playground {
  public static void main(String[] args) {
    // Add more tests as methods and call them here!!
	/*MinBinHeap heap = new MinBinHeap(10);
	CacheFrame a = new CacheFrame("a", 3);
	CacheFrame b = new CacheFrame("b", 5);
	CacheFrame c = new CacheFrame("c", 9);
	CacheFrame d = new CacheFrame("d", 7);
	CacheFrame e = new CacheFrame("e", 4);
	CacheFrame f = new CacheFrame("f", 6);
	CacheFrame g = new CacheFrame("g", 2);
	heap.insert(a);
	heap.insert(b);
	heap.insert(c);
	heap.insert(d);
	heap.insert(e);
	heap.insert(f);
	heap.insert(g);
	for (int i = 0; i < 10; i++) {
		heap.decElt(d);
	}
	for (int i = 0; i < 100; i++) {
		heap.incElt(d);
	}
	
	System.out.println(heap.getMin().getValue());
	System.out.println(heap.getMin().getPriority());
	heap.delMin();
	System.out.println(heap.getMin().getValue());
	System.out.println(heap.getMin().getPriority());
	heap.delMin();
	System.out.println(heap.getMin().getValue());
	System.out.println(heap.getMin().getPriority());
	heap.delMin();
	
	System.out.println(heap.getMin().getValue());
	System.out.println(heap.getMin().getPriority());
	heap.delMin();
	
	System.out.println(heap.getMin().getValue());
	System.out.println(heap.getMin().getPriority());
	heap.delMin();
	System.out.println(heap.getMin().getValue());
	System.out.println(heap.getMin().getPriority());
	heap.delMin();
	
    RunMyTests();
    */
    // etc.
	  testCase();
  }

  public static void RunMyTests() {
   Cache_LFU lfc = new Cache_LFU(3);
    lfc.refer("AA8C");
    lfc.refer("AA8C");
    lfc.refer("1234");
    lfc.refer("234A");
    lfc.refer("AA8C");
    lfc.refer("234A");
    lfc.refer("ABCD");
    lfc.refer("234A");
    lfc.refer("ABCD");
    lfc.refer("1101");
    lfc.refer("2202"); lfc.refer("2202");
    lfc.refer("2202"); lfc.refer("2202");

    System.out.println(lfc.size());
    System.out.println(lfc.numElts());
    printHeap(lfc.getHeap().getHeap(), lfc.getHeap().size());
 
    // etc.

  }

  public static void printHeap(CacheFrame[] e,int len) { 
    // this method skips over unused 0th index....
    System.out.println("Printing Heap");
    for(int i=1; i< len+1; i++) {
      System.out.print("(p."+e[i].value+",f"+e[i].priority+",s"+e[i].getSlot()+")\t");
    }
    System.out.print("\n");
  }
  
  public static void testCase() {
	  MinBinHeap delMin = new MinBinHeap(10);
	  CacheFrame a = new CacheFrame ("AAAA",1);
	  CacheFrame b = new CacheFrame ("BBBB",1);
	  CacheFrame c = new CacheFrame ("",2);
	  delMin.insert(a);
	  delMin.insert(b);
	  System.out.println(delMin.getMin().getValue());
	  System.out.println(delMin.getMin().getPriority());
	  delMin.incElt(a);
	  System.out.println(delMin.getMin().getValue());
	  System.out.println(delMin.getMin().getPriority());
	  
  }
}
