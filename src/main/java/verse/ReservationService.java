package verse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
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
   	 	String filename = "Reservation.pdf";
		Context context = new Context();
	    context.setVariable("rental", data.getRental());
	    context.setVariable("linesPerMonth", groupLinesByMonth(data.getLignes()));
	    context.setVariable("months", new Months());
	    
	    return verse_service.generatePdfFromHtml(context, filename,"reservation_template");
   }
	
	public Map<Integer, List<LigneExport>> groupLinesByMonth(ArrayList<LigneExport> lines){

		Map<Integer, List<LigneExport>> linesPerMonth =lines.stream()
	      .collect(
	         Collectors.groupingBy(LigneExport::getMonth, TreeMap::new, Collectors.toList())
	      );

		return linesPerMonth;
	}
}
