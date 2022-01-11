package verse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DateFormatSymbols;
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
	public String parseThymeleafTemplate(Context context, String template) {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        
        return templateEngine.process(template, context);
    }
    public ResponseEntity<Resource> generatePdfFromHtml(Context context,String filename, String template) throws FileNotFoundException, IOException, DocumentException {
    	String HTML = this.parseThymeleafTemplate(context, template);
        HtmlConverter.convertToPdf(HTML, new FileOutputStream(filename));
        File file = new File(filename);
        HttpHeaders respHeaders = new HttpHeaders();
	    respHeaders.setContentDispositionFormData("attachment", filename);
	    respHeaders.add("Content-Type", "application/force-download");

	    return new ResponseEntity<Resource>(
	        new FileSystemResource(file), respHeaders, HttpStatus.OK
	    );
    }

    public String getMonthLetters(int number) {
	    DateFormatSymbols dfs = new DateFormatSymbols();
	    String[] months = dfs.getMonths();
	    return months[number];
	}
}
