package verse;

import java.util.ArrayList;

public class Export {
	private Contrat contract;
	private Location location;
	private ArrayList<Operation> operations;
	
	public Contrat getContract() {
		return contract;
	}
	public void setContract(Contrat contract) {
		this.contract = contract;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public ArrayList<Operation> getOperations() {
		return operations;
	}
	public void setOperations(ArrayList<Operation> operations) {
		this.operations = operations;
	}
	
	
}
