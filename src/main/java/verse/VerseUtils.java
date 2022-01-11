package verse;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class VerseUtils {
	public void getMonth(Date dateFrom, Date dateTo){
	    String OLD_FORMAT = "MM/dd/yyyy";
	    String NEW_FORMAT = "MM/yyyy";
	    SimpleDateFormat newFormat = new SimpleDateFormat(OLD_FORMAT);
	    DateFormat formatter = new SimpleDateFormat(NEW_FORMAT);
	    Date fromDate = new Date();
	    Date toDate = new Date();
	    
		try {
			fromDate = newFormat.parse(dateFrom.toString());
			toDate = newFormat.parse(dateTo.toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    

	    Calendar beginCalendar = Calendar.getInstance();
	    Calendar finishCalendar = Calendar.getInstance();

	    beginCalendar.setTimeInMillis(fromDate.getTime());
	    finishCalendar.setTimeInMillis(toDate.getTime());

	    String date;
	    while (beginCalendar.before(finishCalendar)) {
	        // add one month to date per loop
	        date = formatter.format(beginCalendar.getTime());
	        System.out.println(date);
	        beginCalendar.add(Calendar.MONTH, 1);
	    }
	}
}
