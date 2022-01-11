package verse;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import ch.qos.logback.core.joran.conditional.IfAction;

public class LigneExport{
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

	public int getMonth2() {
	    return this.getDateTo().getMonth();
	}
	
	public ArrayList<Date> listMonthsBetween() {
		Calendar beginCalendar = Calendar.getInstance();
	    Calendar finishCalendar = Calendar.getInstance();
	
	    beginCalendar.setTimeInMillis(this.getDateFrom().getTime());
	    finishCalendar.setTimeInMillis(this.getDateTo().getTime());
	
	    Date date;
	    ArrayList<Date> listMonths = new ArrayList<Date>();
	    
	    while (beginCalendar.before(finishCalendar)) {
			date = beginCalendar.getTime();
			listMonths.add(date);
			beginCalendar.add(Calendar.MONTH, 1);
	    }
	    if(this.getDateFrom().getMonth() != this.getDateTo().getMonth()) {
			date = finishCalendar.getTime();
			listMonths.add(date);
	    }
        
	   return listMonths;
	}
	
	public int getMonth() {
		ArrayList<Date> months = this.listMonthsBetween();
		ArrayList<Integer> listDiffDays = new ArrayList<Integer>();
		
		int index = 0;

		if(months.size() > 1) {
			for (Date month : months) { 
		    	if(index == 0) {
		    		Date lastDayDate = getLastDayOfMonth(month);
		    		listDiffDays.add(calculateDaysBetweenTwoDates(lastDayDate, month));
		    	}else {
		    		Date firstDayDate = getFirstDayOfMonth(month);
		    		listDiffDays.add(calculateDaysBetweenTwoDates(month, firstDayDate));
		    	}
		    	
		    	index++;
		    }
			return months.get(maxIndex(listDiffDays)).getMonth();
		}else {
	    	return months.get(0).getMonth();
	    }
	}
	
	public Date getLastDayOfMonth(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
		return c.getTime();
	}
	
	public Date getFirstDayOfMonth(Date date) {
		 Calendar c = Calendar.getInstance();
		 c.setTime(date);
		 c.set(Calendar.DAY_OF_MONTH, 1);
		 return c.getTime(); 
	}
	
	public int calculateDaysBetweenTwoDates(Date date1, Date date2) {
		 if(date1.getTime() > date2.getTime()) {
			 return (int)( (date1.getTime() - date2.getTime()) / (1000 * 60 * 60 * 24));
		 }else {
			 return (int)( (date2.getTime() - date1.getTime()) / (1000 * 60 * 60 * 24));
		 }
	}
	
	public int maxIndex(ArrayList<Integer> list) {
	  Integer i=0, 
	  maxIndex=-1, max=null;
	  for (Integer x : list) {
	    if ((x!=null) && ((max==null) || (x>=max))) {
	      max = x;
	      maxIndex = i;
	    }
	    i++;
	  }
	  if(maxIndex == -1) {
		  return list.size() - 1;
	  }
	  return maxIndex;
	}
}
