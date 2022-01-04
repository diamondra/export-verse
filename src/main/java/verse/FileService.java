package verse;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
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