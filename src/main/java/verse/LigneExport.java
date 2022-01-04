package verse;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LigneExport {
	private String id;
	private String dateFrom;
	private String dateTo;
	private Double price;
	private Tenant tenant;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDateFrom() {
		return dateFrom;
	}
	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}
	public String getDateTo() {
		return dateTo;
	}
	public void setDateTo(String dateTo) {
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
		DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");

		Date date = inputFormat.parse(this.getDateFrom());
		String outputText = outputFormat.format(date);
		
		 return outputText;
	}
	
	public String formateDateTo() throws ParseException {
		DateFormat outputFormat = new SimpleDateFormat("MM/yyyy");
		DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");

		Date date = inputFormat.parse(this.getDateTo());
		String outputText = outputFormat.format(date);
		
		 return outputText;
	}
	public String getMonthForInt() {
	    String month = "";
	    SimpleDateFormat dateFormat = new SimpleDateFormat("MM");
	    int num = Integer.parseInt(dateFormat.format(this.getDateTo()));
	    DateFormatSymbols dfs = new DateFormatSymbols();
	    String[] months = dfs.getMonths();
	    if (num >= 1 && num <= 12) {
	        month = months[num-1];
	    }
	    return month;
	}
}
