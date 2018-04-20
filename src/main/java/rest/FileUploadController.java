package rest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
public class FileUploadController {
	
	private static final String UPLOAD_ABSOLUTE_PATH = "F://Fakultet/isa/workspace/isaproject/src/main/webapp/images/";
	private static final String UPLOAD_RELATIVE_PATH = "/images/";
	
	@PostMapping(path = "/imageUpload", produces = "text/plain")
	public String imageUpload(@RequestParam("file") MultipartFile file) {
		try {
			byte[] bytes = file.getBytes();
			String imageName = LocalDateTime.now().toString().replace(":", "_").replace(".", "_") + file.getOriginalFilename();
			Path path = Paths.get(UPLOAD_ABSOLUTE_PATH + imageName);
			Files.write(path, bytes);
			return UPLOAD_RELATIVE_PATH + imageName;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
