package assignment5_f20;

import java.util.HashMap;

public interface Node{
	
	long getID();
	void setID(long newID);
	String getLabel();
	void setLabel(String newLabel);
	HashMap<String, Edge> getEdges();
	void addEdges(String dest, Edge newEdge);
	void remEdges(String dest);
	//Dijkstra's Algorithm Stuff
	long getDist();
	void setDist(long newDist);
	boolean getKnown();
	void setKnown();
	void setPath(Node prevNode);
	Node getPath();
}
