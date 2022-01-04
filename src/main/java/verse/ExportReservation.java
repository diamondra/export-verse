package verse;

import java.util.ArrayList;

public class ExportReservation {
	private Appartement rental;
	private ArrayList<LigneExport> lignes;
	
	public Appartement getRental() {
		return rental;
	}
	public void setRental(Appartement rental) {
		this.rental = rental;
	}
	public ArrayList<LigneExport> getLignes() {
		return lignes;
	}
	public void setLignes(ArrayList<LigneExport> lignes) {
		this.lignes = lignes;
	}
	
}
