package assignment5_f20;

import java.util.HashMap;

public class NodeImpl implements Node{
	long idNum;
	String label;

	HashMap<String, Edge> edgeMap;
	long dist;
	boolean known;
	Node prevNode;
	
	public NodeImpl(long idNum, String label) {
		this.idNum = idNum;
		this.label = label;
		edgeMap = new HashMap<String,Edge>();
		//Dijkstra's
		this.dist = (long) Double.POSITIVE_INFINITY;
		this.known = false;
		this.prevNode = null;
	}
	
	public long getID() {
		return this.idNum;
	}
	
	public void setID(long newID) {
		this.idNum = newID;
	}
	
	public String getLabel() {
		return this.label;
	}
	
	public void setLabel(String newLabel) {
		label = newLabel;
	}
	
	public HashMap<String, Edge> getEdges(){
		return edgeMap;
	}
	
	public void addEdges(String dest, Edge newEdge) {
		edgeMap.put(dest, newEdge);
	}
	
	public void remEdges(String dest) {
		edgeMap.remove(dest);
	}
	public long getDist() {
		return dist;
	}
	public void setDist(long newDist) {
		dist = newDist;
	}
	public boolean getKnown() {
		return known;
	}
	public void setKnown() {
		known = !known;
	}
	public void setPath(Node prevNode) {
		this.prevNode = prevNode;
	}
	public Node getPath() {
		return prevNode;
	}
	
}
