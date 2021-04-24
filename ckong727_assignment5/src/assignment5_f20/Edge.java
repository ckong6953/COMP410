package assignment5_f20;

public interface Edge {
	long getID();
	String getSource();
	String getDest();
	long getWeight();
	String getLabel();
	void setID(long newID);
	void setSource(String newSource);
	void setDest(String newDest);
	void setWeight (long newWeight);
	void setLabel(String newLabel);
}
