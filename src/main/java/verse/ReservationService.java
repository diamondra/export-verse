package verse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
	    context.setVariable("linesPerMonth", groupLinesByMonth(data.getLignes()));
	    
	    return verse_service.generatePdfFromHtml(context, filename,"reservation_template");
   }
	
	public Map<String, List<LigneExport>> groupLinesByMonth(ArrayList<LigneExport> lines){
	    Collections.sort(lines,  new Comparator<LigneExport>() {
	         public int compare(LigneExport line1, LigneExport line2) {
	             try {
	                 SimpleDateFormat sdf = new SimpleDateFormat("MMM");
	                 return sdf.parse(line1.getDateTo().toString()).compareTo(sdf.parse(line2.getDateTo().toString()));  //sdf.parse returns date - So, Compare Date with date
	             } catch (ParseException ex) {
	                 return line1.compareTo(line2);
	             }
	         }
	     });
	     
		Map<String, List<LigneExport>> linesPerMonth = lines.stream()
				  .collect(Collectors.groupingBy(LigneExport::getMonth));
			
		return linesPerMonth;
	}
}
