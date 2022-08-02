package ci.gstoreplus.dashboard.controller;

import java.io.IOException;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ci.gstoreplus.dashboard.metier.CloudinaryService;
import ci.gstoreplus.dashboard.metier.ImageService;
import ci.gstoreplus.dashboard.metier.catalogue.ImageMetier;
import ci.gstoreplus.entity.catalogue.Image;
import ci.gstoreplus.entity.catalogue.Maison;
import ci.gstoreplus.exception.InvalideImmobilierException;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class UploadController {
	
	@Autowired
	CloudinaryService cloudinaryService;
	@Autowired
	ImageService imageSevice;
	@Autowired
	ImageMetier imageMetier;
	
	// solution alterntive cloudinary//////////////////////////
		@PostMapping("/upload")
		public ResponseEntity<?> upload(@RequestParam MultipartFile multipartFile, @RequestParam Long id)
				throws IOException, InvalideImmobilierException {
			System.out.println(multipartFile);
			Map result = cloudinaryService.upload(multipartFile);
			Image im = new Image();
			im.setIdProduit(id);
			im.setImageUrl((String) result.get("url"));
			imageMetier.creer(im);
			return new ResponseEntity(result, HttpStatus.OK);
		}

}
