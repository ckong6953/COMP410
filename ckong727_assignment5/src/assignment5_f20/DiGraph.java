package assignment5_f20;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;



public class DiGraph implements DiGraphInterface {

  // in here go all your data and methods for the graph
	/*
	LinkedList<Node> nodeList;
	HashMap<String, Node> nodeMap;
	*/
	HashMap<String, Node> nodeMap;
	//HashMap<Long, Node> idMap;
	int nodeCounter;
	int edgeCounter;

  public DiGraph ( ) { // default constructor
    // explicitly include this
    // we need to have the default constructor
    // if you then write others, this one will still be there
	  this.nodeMap = new HashMap<String, Node>();
	  this.nodeCounter = 0;
	  this.edgeCounter = 0;
  }
  
  public boolean addNode(long idNum, String label){
	  if (idNum < 0 || label == null) {
		  return false; 
	  }
	  if (nodeMap.containsKey(label)) {
		  return false;
	  }
	  Node newNode = new NodeImpl(idNum, label);
	  nodeMap.put(label, newNode);
	  nodeCounter++;
	  return true;
  }
  
  public boolean addEdge(long idNum, String sLabel, String dLabel, long weight, String eLabel) {
	  if (weight < 0) {
		  weight = 1;
	  }
	  if (idNum < 0) {
		  return false;
	  }
	  if (!nodeMap.containsKey(sLabel) || !nodeMap.containsKey(dLabel)) {
		  return false;
	  }
	  if(nodeMap.get(sLabel).getEdges().containsKey(dLabel)) {
		  return false;
	  }
	  Edge newEdge = new EdgeImpl(idNum, sLabel, dLabel, weight, eLabel);
	  nodeMap.get(sLabel).addEdges(dLabel, newEdge);
	  edgeCounter++;
	  return true;
  }
 
  public boolean delNode(String label) {
	  if(!nodeMap.containsKey(label)) {
		  return false;
	  }
	  
	  Iterator mapIterator = nodeMap.entrySet().iterator();
	  while (mapIterator.hasNext()) {
		  Map.Entry currNode = (Map.Entry)mapIterator.next();
		  if (((String) currNode.getKey()).compareTo(label) != 0) {
			  if (((Node) currNode.getValue()).getEdges().containsKey(label)) {
				  nodeMap.get(currNode.getKey()).getEdges().remove(label);
				  edgeCounter--;
			  }
		  }
	  }
	  edgeCounter = edgeCounter - nodeMap.get(label).getEdges().size();
	  nodeMap.remove(label);
	  nodeCounter--;
	  
	  return true;
  }
  
  public boolean delEdge(String sLabel, String dLabel) {
	
	  if (!nodeMap.containsKey(sLabel) || !nodeMap.containsKey(dLabel)) {
		  return false;
	  }
	 
	  if (!nodeMap.get(sLabel).getEdges().containsKey(dLabel)) {
		  return false;
	  }
	  nodeMap.get(sLabel).remEdges(dLabel);
	  edgeCounter--;
	  return true;
  }
  public long numNodes() {
	  return nodeCounter;
  }
  public long numEdges() {
	  return edgeCounter;
  }
  
  // rest of your code to implement the various operations
  public void print() {
	  PriorityQueue<Node> sortPath = new PriorityQueue<Node>();
	  Iterator mapIterator = nodeMap.entrySet().iterator();
	  while (mapIterator.hasNext()) {
		  Map.Entry currNode = (Map.Entry)mapIterator.next();
		  long nodeID = (long) ((Node) currNode.getValue()).getID();
		  String nodeLabel = (String) currNode.getKey();
		  System.out.println( "(" + nodeID + ")" + nodeLabel);
		  Iterator edgeIterator = nodeMap.get(currNode.getKey()).getEdges().entrySet().iterator();
		  while (edgeIterator.hasNext()) {
			  Map.Entry currEdge = (Map.Entry)edgeIterator.next();
			  
			  String edgeLabel = ((Edge) currEdge.getValue()).getLabel();
			  long edgeID = (long) ((Edge) currEdge.getValue()).getID();
			  
			  int edgeWeight = (int) ((Edge) currEdge.getValue()).getWeight();
			  String dest = (String) currEdge.getKey();
			  System.out.println("\t(" + edgeID + ")" + " -- " + edgeLabel + "," + edgeWeight + " --> " 
			  + dest);
		  }
		  
	  }
  }
  
  public ShortestPathInfo[] shortestPath(String label) {
	  
	  PriorityQueue<String> sortPath = new PriorityQueue<>((s1, s2) -> Long.compare(nodeMap.get(s1).getDist(), nodeMap.get(s2).getDist()));
	  ShortestPathInfo[] shortPath = new ShortestPathInfo[nodeMap.size()];
	  int addPos = 0;
	  
	  nodeMap.get(label).setDist(0);
	  
	  Iterator mapIterator = nodeMap.entrySet().iterator();
	  while(mapIterator.hasNext()) {
		  Map.Entry currNode = (Map.Entry)mapIterator.next();
		  sortPath.add((String) currNode.getKey());
	  }
	  
	  while (!sortPath.isEmpty()) {
		  String currentLabel = sortPath.remove();
		  
		  if (nodeMap.get(currentLabel).getDist() == (long) Double.POSITIVE_INFINITY) {
			  shortPath[addPos] = new ShortestPathInfo(currentLabel,-1);
			  addPos++;
		  }
		  
		  else {
			  nodeMap.get(currentLabel).setKnown();
			  shortPath[addPos] = new ShortestPathInfo(currentLabel, nodeMap.get(currentLabel).getDist());
			  addPos++;
			  Iterator adjIterator = nodeMap.get(currentLabel).getEdges().entrySet().iterator();
			  while (adjIterator.hasNext()) {
				  Map.Entry currAdj = (Map.Entry)adjIterator.next();
				  if (!nodeMap.get(currAdj.getKey()).getKnown()) {
					  long addPath  = ((Edge) currAdj.getValue()).getWeight();
		  			
					  if (addPath + nodeMap.get(currentLabel).getDist() < nodeMap.get(currAdj.getKey()).getDist()){
						  nodeMap.get(currAdj.getKey()).setDist(addPath + nodeMap.get(currentLabel).getDist());
						  nodeMap.get(currAdj.getKey()).setPath(nodeMap.get(currentLabel));
						  sortPath.remove(nodeMap.get(currAdj.getKey()).getLabel());
						  sortPath.add(nodeMap.get(currAdj.getKey()).getLabel());  
					  }
				  }
			  }
			  
		  }
	  }
	  
	  return shortPath;
  }
}