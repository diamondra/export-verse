package verse;

import java.text.DateFormatSymbols;

public class Months {
	private String[] liste;

	
	public Months() {
		DateFormatSymbols dfs = new DateFormatSymbols();
		this.setListe(dfs.getMonths());
	}


	public String[] getListe() {
		return liste;
	}


	public void setListe(String[] liste) {
		this.liste = liste;
	}

	
	
}
