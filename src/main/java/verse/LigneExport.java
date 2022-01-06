package verse;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LigneExport implements Comparable<LigneExport>{
	private String id;
	private Date dateFrom;
	private Date dateTo;
	private Double price;
	private Tenant tenant;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getDateFrom() {
		return dateFrom;
	}
	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}
	public Date getDateTo() {
		return dateTo;
	}
	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Tenant getTenant() {
		return tenant;
	}
	public void setTenant(Tenant tenant) {
		this.tenant = tenant;
	}
	
	public String formateDateFrom() throws ParseException {
		DateFormat outputFormat = new SimpleDateFormat("MM/yyyy");

		String outputText = outputFormat.format(this.getDateFrom());
		
		 return outputText;
	}
	
	public String formateDateTo() throws ParseException {
		DateFormat outputFormat = new SimpleDateFormat("MM/yyyy");

		String outputText = outputFormat.format(this.getDateTo());
		
		 return outputText;
	}
	public String getMonth() {
	    DateFormatSymbols dfs = new DateFormatSymbols();
	    String[] months = dfs.getMonths();
	    return months[this.getDateTo().getMonth()];
	}
	
	@Override
	  public int compareTo(LigneExport o) {
		System.out.println(getDateTo()+"VS" +o.getDateTo()+"========>"+getDateTo().compareTo(o.getDateTo()));
	    return getDateTo().compareTo(o.getDateTo());
	  }
}
