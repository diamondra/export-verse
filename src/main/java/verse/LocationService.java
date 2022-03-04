package verse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.thymeleaf.context.Context;

import com.lowagie.text.DocumentException;
/**
 *
 * @author OPTIMANIA
 */
public class LocationService {
	private VerseService verse_service = new VerseService();
	
    public ResponseEntity<Resource> export(Export data) throws FileNotFoundException, IOException, DocumentException {
    	 String filename = "Location.pdf";
		 Context context = new Context();
	     context.setVariable("tenant", data.getContract().getTenant());
	     context.setVariable("contract", data.getContract());
	     context.setVariable("location", data.getLocation());
	     List<Operation> operations = data.getOperations()
	    		  .stream()
	    		  .filter(o -> o.getType().equals("arrhes") || o.getType().equals("loyer"))
	    		  .collect(Collectors.toList());
	     context.setVariable("operations",operations);
	     SimpleDateFormat dt1 = new SimpleDateFormat("dd-MM-yy");
	     context.setVariable("now", dt1.format(new Date()));
	     
	     SimpleDateFormat dt2 = new SimpleDateFormat("dd MMMM yyyy");
	     context.setVariable("dateFrom", dt2.format(data.getContract().getDateFrom()));
	     context.setVariable("dateTo", dt2.format(data.getContract().getDateTo()));
	     
	     return verse_service.generatePdfFromHtml(context, filename, "location_template");
    }
}
