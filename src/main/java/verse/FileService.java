package verse;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;


@Service
public class FileService {
    public Resource download(String filename) {
       
		Resource resource = new ClassPathResource(filename);

		if (resource.exists() || resource.isReadable()) {
		    return resource;
		} else {
		    throw new RuntimeException("Could not read the file!");
		}
    }
}