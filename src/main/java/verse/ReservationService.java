package verse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormatSymbols;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.thymeleaf.context.Context;

import com.lowagie.text.DocumentException;
/**
 *
 * @author OPTIMANIA
 */
public class ReservationService {
	private VerseService verse_service = new VerseService();
	
	public ResponseEntity<Resource> export(ExportReservation data) throws FileNotFoundException, IOException, DocumentException {
   	 	String filename = "Reservation"+ data.getRental().getTitle() +".pdf";
		Context context = new Context();
	    context.setVariable("rental", data.getRental());
	    context.setVariable("lines", data.getLignes());
	     
	    return verse_service.generatePdfFromHtml(context, filename,"reservation_template");
   }
	
	String getMonthForInt(int num) {
	    String month = "";
	    DateFormatSymbols dfs = new DateFormatSymbols();
	    String[] months = dfs.getMonths();
	    if (num >= 1 && num <= 11) {
	        month = months[num-1];
	    }
	    return month;
	}
}
