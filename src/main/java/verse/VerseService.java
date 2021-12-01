package verse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.itextpdf.html2pdf.HtmlConverter;
import com.lowagie.text.DocumentException;
/**
 *
 * @author OPTIMANIA
 */
public class VerseService {
	private final FileService fileService = new FileService();
	private final String filename = "Location.pdf";
	private final String folder = "src/main/resources/";
	
    public String parseThymeleafTemplate(Export data) {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        Context context = new Context();
        context.setVariable("tenant", data.getContract().getTenant());
        context.setVariable("contract", data.getContract());
        context.setVariable("location", data.getLocation());
        context.setVariable("operations", data.getOperations());
        
        SimpleDateFormat dt1 = new SimpleDateFormat("dd-MM-yy");
        context.setVariable("now", dt1.format(new Date()));
        
        SimpleDateFormat dt2 = new SimpleDateFormat("dd MMMM yyyy");
        context.setVariable("dateFrom", dt2.format(data.getContract().getDateFrom()));
        context.setVariable("dateTo", dt2.format(data.getContract().getDateTo()));
        
        return templateEngine.process("template", context);
    }
    public ResponseEntity<Resource> generatePdfFromHtml(Export data) throws FileNotFoundException, IOException, DocumentException {
    	String HTML = this.parseThymeleafTemplate(data);
        HtmlConverter.convertToPdf(HTML, new FileOutputStream(filename));
        File file = new File("Location.pdf");
        HttpHeaders respHeaders = new HttpHeaders();
	    respHeaders.setContentDispositionFormData("attachment", filename);
	    respHeaders.add("Content-Type", "application/force-download");

	    return new ResponseEntity<Resource>(
	        new FileSystemResource(file), respHeaders, HttpStatus.OK
	    );
    }
    
    /*public ResponseEntity<Resource> exportPDF(Tenant tenant) throws FileNotFoundException, IOException, DocumentException {
    	this.generatePdfFromHtml(tenant);
    	 Resource file = fileService.download(filename);
         Path path = file.getFile()
                         .toPath();

         return ResponseEntity.ok()
                              .header(HttpHeaders.CONTENT_TYPE, Files.probeContentType(path))
                              .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                              .body(file);
    }*/

}
