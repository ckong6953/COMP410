package assignment5_f20;

public class EdgeImpl implements Edge{
	public long ID;
	public String source;
	public String dest;
	public long weight;
	public String label;
	
	public EdgeImpl(long ID, String source, String dest, long weight, String label) {
		this.ID = ID;
		this.source = source;
		this.dest = dest;
		this.weight = weight;
		this.label = label;
	}
	
	public long getID() {
		return this.ID;
	}
	public String getSource() {
		return this.source;
	}
	public String getDest() {
		return this.dest;
	}
	public long getWeight() {
		return this.weight;
	}
	public String getLabel() {
		return this.label;
	}
	public void setID(long newID) {
		this.ID = newID;
	}
	public void setSource(String newSource) {
		this.source = newSource;
	}
	public void setDest(String newDest) {
		this.dest = newDest;
	}
	public void setWeight (long newWeight) {
		this.weight = newWeight;
	}
	public void setLabel(String newLabel) {
		this.label = newLabel;
	}
}
