package assignment2_f20;

public class TreeMap_Playground {
/*
 * you will test your own TreeMap implementation in here
 *
 * we will replace this with our own when grading, and will
 * do what you should do in here... create TreeMap objects,
 * put data into them, take data out, look for values stored
 * in it, checking size, and looking at the TMCells to see if they 
 * are all linked up correctly as a BST
 * 
*/
  
  public static void main(String[] args) {
    // you should test your TreeMap implementation in here
    // sample tests are shown

    // it is up to you to test it thoroughly and make sure
    // the methods behave as requested above in the interface
  
    // do not simple depend on the oracle test we will give
    // use the oracle tests as a way of checking AFTER you have done
    // your own testing

    // one thing you might find useful for debugging is a print tree method
    // feel free to use the one we have here ... basic and quick, or write one 
    // you like better, one that shows you the tree structure more clearly
    // the one we have here only shows keys, you may wish to add 
    // value fields to the printing

    TreeMap tm = new TreeMap_imp();
    Value v1 = new Value_imp(1, 100, 20);
    Value v2 = new Value_imp(2, 100, 20);
    Value v3 = new Value_imp(3, 100, 20);
    Value v4 = new Value_imp(45678, 55.70, 35);
    Value v5 = new Value_imp(5, 100, 20);
    Value v6 = new Value_imp(6, 100, 20);
    Value v7 = new Value_imp(7, 100, 20);
    Value v8 = new Value_imp(8, 100, 20);
    Value v9 = new Value_imp(9, 100, 20);
    Value v10 = new Value_imp(10, 100, 20);
    Value v11 = new Value_imp(11, 100, 20);
    Value v12 = new Value_imp(12, 100, 20);
    Value v13 = new Value_imp(13, 100, 20);
    Value v14 = new Value_imp(14, 100, 20);
    Value v15 = new Value_imp(15, 100, 20);
    Value v16 = new Value_imp(16, 100, 20);
    Value v17 = new Value_imp(17, 100, 20);
    
    tm.put("21", v1); tm.put("14",v2); 
    tm.put("28",v3); tm.put("11",v4);
    tm.put("18", v5); tm.put("25", v6);
    tm.put("32", v7); tm.put("05", v8);
    tm.put("12", v9); tm.put("15", v10);
    tm.put("19", v11); tm.put("23", v12);
    tm.put("27", v13); tm.put("30", v14);
    tm.put("37", v15); 
    
    tm.remove("21");
    System.out.println(tm.get("sigma")); // assumes a toString for a Value onject
    System.out.println(tm.hasKey("gamma"));
    prTree(tm.getRoot(),0); 
    // etc...
    
    for (int i = 0; i < tm.getKeys().length; i++) {
    	System.out.println(tm.getKeys()[i] + " ");
    }

  }

  public static void prTree (TMCell c, int off) {
    if (c==null) return;
        
    prTree(c.getRight(), off+2);
    
    for (int i=0; i<off; i++) System.out.print(" ");
    System.out.println(c.getKey());
    
    prTree(c.getLeft(), off+2);
  }

}