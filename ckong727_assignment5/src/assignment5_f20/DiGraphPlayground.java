package assignment5_f20;

import java.util.Random;

public class DiGraphPlayground {

  public static void main (String[] args) {
  
      // thorough testing is your responsibility
      //
      // you may wish to create methods like 
      //    -- print
      //    -- sort
      //    -- random fill
      //    -- etc.
      // in order to convince yourself your code is producing
      // the correct behavior
      //exTest();
	  
	  //long startTime = System.currentTimeMillis();
	  //perfTest(100000);
	  //EdgeTest();
	  buildonce(10000, 1, 3);
	  //long endTime = System.currentTimeMillis();
	  //otherTest();
	  
	  
	  /*DiGraph p = new DiGraph();
	  p.addNode(1,"f");
	  p.addNode(3,"s");
	  p.delNode("f");
	  System.out.println("numEdges: "+p.numEdges());
      System.out.println("numNodes: "+p.numNodes());
	  */
	  //EdgeTest();
	  //otherTest();
	  //shortTest();
	  //CityTest();
  }
  
  	public static void shortTest() {
  		DiGraph shortPath = new DiGraph();
  		shortPath.addNode(0, "A");
  		shortPath.addNode(1, "B");
  		shortPath.addNode(2, "C");
  		/* Test Case 0
  		shortPath.addEdge(0, "A", "B", 2, "A to B");
  		shortPath.addEdge(1, "A", "C", 1, "A to C");
  		shortPath.addEdge(2, "B", "C", 5, "B to C");
  		shortPath.addEdge(3, "C", "B", 1, "C to B");
  		System.out.println("numEdges: "+shortPath.numEdges());
  	    System.out.println("numNodes: "+shortPath.numNodes());
  	    */
  		// Test Case 1
  		
  		shortPath.addEdge(0, "A", "B", 3, "A to B");
  		shortPath.addEdge(1, "A", "C", 10, "A to C");
  		shortPath.addEdge(2, "B", "C", 4, "B to C");
  		
  		/*
  		shortPath.addNode(0, "p");
  		shortPath.addNode(1, "a");
  		shortPath.addNode(2, "g");
  		shortPath.addNode(3, "e");
  		shortPath.addEdge(0, "p", "a", 10, "p to a");
  		shortPath.addEdge(1, "p", "g", 4, "p to g");
  		shortPath.addEdge(2, "p", "e", 8, "p to e");
  		shortPath.addEdge(3, "a", "p", 9, "a to p");
  		shortPath.addEdge(4, "a", "g", 12, "a to g");
  		shortPath.addEdge(5, "a", "e", 100, "a to e");
  		shortPath.addEdge(6, "g", "p", 2, "g to p");
  		shortPath.addEdge(7, "g", "a", 15, "g to a");
  		shortPath.addEdge(8, "g", "e", 1, "g to e");
  		shortPath.addEdge(9, "e", "p", 6, "e to p");
  		shortPath.addEdge(10, "e", "a", 3, "e to a");
  		*/
  		
  		/* Test Case 4
  		shortPath.addNode(0, "0");
  		shortPath.addNode(1, "1");
  		shortPath.addNode(2, "2");
  		shortPath.addNode(3, "3");
  		shortPath.addNode(4, "4");
  		shortPath.addNode(5, "5");
  		shortPath.addNode(6, "6");
  		shortPath.addEdge(0, "0", "5", 3, "0 to 5");
  		shortPath.addEdge(1, "3", "2", 6, "3 to 2");
  		shortPath.addEdge(2, "4", "0", 1, "4 to 0");
  		shortPath.addEdge(3, "4", "5", 2, "4 to 5");
  		shortPath.addEdge(4, "6", "1", 4, "6 to 1");
		*/
  		System.out.println("numEdges: "+shortPath.numEdges());
  	    System.out.println("numNodes: "+shortPath.numNodes());
  	    System.out.println("--------------------------------");
  	    shortPath.print();
  	    ShortestPathInfo[] printPath = shortPath.shortestPath("A");
  	    System.out.println("Destination: " + printPath[2].getDest());
  	    System.out.println("Weight: " + printPath[2].getTotalWeight());
  	    System.out.println("shortest path length: " + printPath.length);
  	    for (int i = 0; i < printPath.length; i++) {
  	    	System.out.println("Destination: " + printPath[i].getDest());
  	    	System.out.println("Weight: " + printPath[i].getTotalWeight());
  	    }
  	}
    public static void exTest(){
      DiGraph d = new DiGraph();
      d.addNode(1, "f");
      d.addNode(3, "s");
      d.addNode(7, "t");
      d.addNode(0, "fo");
      d.addNode(4, "fi");
      d.addNode(6, "si");
      d.addEdge(0, "f", "s", 0, null);
      d.addEdge(1, "f", "si", 0, null);
      d.addEdge(2, "s", "t", 0, null);
      d.addEdge(3, "fo", "fi", 0, null);
      d.addEdge(4, "fi", "si", 0, null);
      d.print();
      System.out.println("numEdges: "+d.numEdges());
      System.out.println("numNodes: "+d.numNodes());
    }
    
    
    public static void otherTest() {
    	DiGraph done = new DiGraph();
    	long s1 = System.currentTimeMillis();
    	for (int i = 0; i < 1000000; i++) {
    		done.addNode(i, Integer.toString(i));
    	}
    	long s2 = System.currentTimeMillis();
    	System.out.println("numEdges: "+done.numEdges());
        System.out.println("numNodes: "+done.numNodes());
        System.out.println("Elasped Time: " + (s2-s1)+ " ms");
        for (int i = 0; i < done.numNodes() - 2; i++) {
        	String temp = Integer.toString(i);
        	done.addEdge(i+1, temp, temp.concat(temp), 1, "nada");
        	done.addEdge(i+2, temp, temp.concat(temp).concat(temp), 1, "haha");
        }
        
    }
    
    public static void perfTest(int n) {
    	Random rng = new Random(3);
        String[] s = new String[n];
        for (int i = 0; i < n; i++) {
            s[i] = Integer.toString(i);
        }
 
        long s1 = System.currentTimeMillis();
        DiGraph g = new DiGraph();
        for (int i = 0; i < n; i++) {
            g.addNode(i, s[i]);
        }
        
        int eid = 0;
        for (int i = 0; i < n; i++) {
            int v1 = rng.nextInt(n);
            int v2 = rng.nextInt(n);
            g.addEdge(eid++, s[i], s[v1], rng.nextInt(), "");
            g.addEdge(eid++, s[i], s[v2], rng.nextInt(), "");
        }
        System.out.println("numEdges: "+g.numEdges());
        System.out.println("numNodes: "+g.numNodes());
        long e1 = System.currentTimeMillis();
        System.out.println("Building took " + (e1 - s1) + " ms");
 
        /*long s2 = System.currentTimeMillis();
        ShortestPathInfo[] sp = g.shortestPath(s[0]);
        long e2 = System.currentTimeMillis();
        System.out.println("Shortest paths took " + (e2 - s2) + " ms");
 
        long s3 = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            g.delNode(s[i]);
        }
        long e3 = System.currentTimeMillis();
        System.out.println("Destruction took " + (e3 - s3) + " ms");
 
        System.out.println("Total time is " + (e1 + e2 + e3 - (s1 + s2 + s3)) + " ms");
        */
    }
    
    public static void buildonce(int num_nodes, int num_edge_per_node, int seed) {;
	Random r = new Random(seed);
	DiGraph d = new DiGraph();
	long start = System.currentTimeMillis();
	d.addNode(0, "0");
	d.addNode(1, "1");
	int eid = 0;
	for(int i = 2; i < num_nodes; i ++) {
		d.addNode(i, Integer.toString(i));
		for(int j =0; j < num_edge_per_node; j ++)
		d.addEdge(eid++, Integer.toString(i), Integer.toString(r.nextInt(i)), r.nextInt(20), null);
	}
	long built = System.currentTimeMillis();
	System.out.println("numNodes: "+d.numNodes());
	System.out.println("numEdges: "+d.numEdges());
	System.out.println("Building took: " + (built - start));
	long startPath = System.currentTimeMillis();
	d.shortestPath(Integer.toString(r.nextInt(num_nodes)));
	long endPath = System.currentTimeMillis();
	System.out.println("Found paths in: " + (endPath-startPath));
	
	long sdel = System.currentTimeMillis();
	for (int i = 0; i < num_nodes; i++) {
		d.delNode(Integer.toString(i));
	}
	
	long edel = System.currentTimeMillis();
	System.out.println("Destroying took: " + (edel-sdel));
	
	System.out.println("numNodes: "+d.numNodes());
	System.out.println("numEdges: "+d.numEdges());

}
    
    public static void EdgeTest() {
    	DiGraph test = new DiGraph();
    	test.addNode(0, "b");
    	test.addNode(1, "e");
    	test.addNode(2, "f");
    	test.addNode(3, "h");
    	test.addEdge(0, "b", "h", 1, "bh");
    	test.addEdge(1, "b", "e", 2, "be");
    	test.addEdge(2, "b", "f", 3, "bf");
    	test.addEdge(3, "f", "h", 4, "fh");
    	test.addEdge(4, "f", "e", 5, "fe");
    	test.addEdge(5, "e", "h", 6, "eh");
    	test.addEdge(0, "b", "h", 7, "id test"); // test unique idNum
    	test.addEdge(6, "1", "h", 8, "source test"); // test source node
    	test.addEdge(7, "b", "1", 9, "dest test"); // test dest node
    	test.addEdge(8, "b", "b", 0, "weight test"); // test wieght node
    	test.addEdge(9, "b", "b", 2, "same label");
    	test.print();
    	System.out.println("------------------------------");
    	System.out.println("numEdges: "+test.numEdges());
        System.out.println("numNodes: "+ test.numNodes());
        System.out.println("------------------------------");
  	    /*ShortestPathInfo[] printPath = test.shortestPath("e");
  	    for (int i = 0; i < printPath.length; i++) {
  	    	System.out.println("Destination: " + printPath[i].getDest());
  	    	System.out.println("Weight: " + printPath[i].getTotalWeight());
  	    }
  	    */
  	    test.delEdge("b", "e");
  	    test.print();
  	    System.out.println("------------------------------");
  	    System.out.println("numEdges: "+test.numEdges());
  	    System.out.println("numNodes: "+ test.numNodes());
  	    System.out.println("------------------------------");
    	test.delNode("b");
    	System.out.println("After Deletion:");
    	test.print();
    	System.out.println("------------------------------");
    	System.out.println("numEdges: "+test.numEdges());
        System.out.println("numNodes: "+ test.numNodes());
        System.out.println("------------------------------");
    	test.delNode("e");
    	System.out.println("Second Deletion:");
    	test.print();
    	System.out.println("------------------------------");
    	System.out.println("numEdges: "+test.numEdges());
        System.out.println("numNodes: "+ test.numNodes());
        System.out.println("------------------------------");

    }
    
    public static void CityTest() {
    	DiGraph city = new DiGraph();
    	city.addNode(0, "Raleigh");
    	city.addNode(1, "Durham");
    	city.addNode(2, "Pittsboro");
    	city.addNode(3, "Los_angeles");
    	city.addNode(4, "Graham");
    	city.addNode(5, "Cary");
    	city.addNode(6, "Chapel_hill");
    	city.addNode(7, "Hillsborough");
    	city.addNode(8, "Carrboro");
    	city.addNode(9, "Sanford");
    	city.addEdge(0, "Raleigh", "Durham", 14, "Raleigh -> Durham");
    	city.addEdge(1, "Durham", "Hillsborough", 9, "Durham -> Hillsborough");
    	city.addEdge(2, "Chapel_hill", "Graham", 25, "Chapel_hill -> Graham");
    	city.addEdge(3, "Chapel_hill", "Carrboro", 1, "Chapel_hill -> Carrboro");
    	city.addEdge(4, "Carrboro", "Cary", 32, "Carrboro -> Cary");
    	city.addEdge(5, "Cary", "Raleigh", 3, "Cary -> Raleigh");
    	city.addEdge(6, "Pittsboro", "Cary", 17, "Pittsboro -> Cary");
    	city.addEdge(7, "Pittsboro", "Sanford", 15, "Pittsboro -> Sanford");
    	city.addEdge(0, "Sanford", "Los_angeles", 3012, "Sanford -> Los_angeles");
    	city.print();
    	
    	ShortestPathInfo[] cityPath = city.shortestPath("Pittsboro");
    	for (int i = 0; i < cityPath.length; i++) {
    		System.out.println("Destination: " + cityPath[i].getDest());
  	    	System.out.println("Weight: " + cityPath[i].getTotalWeight());
    	}
    	System.out.println("numNodes: "+city.numNodes());
    	System.out.println("numEdges: "+city.numEdges());
    }
}