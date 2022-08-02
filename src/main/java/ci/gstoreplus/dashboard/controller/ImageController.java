package ci.gstoreplus.dashboard.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ci.gstoreplus.dashboard.metier.CloudinaryService;
import ci.gstoreplus.dashboard.metier.ImageService;
import ci.gstoreplus.entity.catalogue.Image;
import ci.gstoreplus.exception.InvalideImmobilierException;
import ci.gstoreplus.models.Reponse;
import ci.gstoreplus.utilitaire.Static;

@RestController
@RequestMapping("/cloudinary")
@CrossOrigin
public class ImageController {
	@Autowired
	CloudinaryService cloudinaryService;
	@Autowired
	ImageService imageSevice;
	@Autowired
	private ObjectMapper jsonMapper;

	@GetMapping("/list")
	public ResponseEntity<List<Image>> list() {
		List<Image> list = imageSevice.list();
		return new ResponseEntity(list, HttpStatus.OK);
	}

	@PostMapping("/upload")
	public ResponseEntity<?> upload(@RequestParam MultipartFile multipartFile) throws IOException {
		Map result = cloudinaryService.upload(multipartFile);
		return new ResponseEntity(result, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) throws IOException {
		if (!imageSevice.exists(id)) {
			new InvalideImmobilierException("l'image n'xiste pas");
		}
		Image image = imageSevice.findById(id).get();
		Map result = cloudinaryService.delete(image.getImageId());
		imageSevice.deleteById(id);
		return new ResponseEntity(new InvalideImmobilierException("image supprimée"), HttpStatus.OK);
	}

    //retourne une  image par son identifiant
	@GetMapping("/image/{id}")
	public String getImageById(@PathVariable("id") Long id) throws JsonProcessingException {

		Reponse<Image> reponse;

		try {

			Image t = imageSevice.findImageByIdTerrain(id);
			List<String> messages = new ArrayList<>();
			messages.add(String.format(" à été créer avec succes"));
			reponse = new Reponse<Image>(0, messages, t);

		} catch (Exception e) {

			reponse = new Reponse<Image>(1, Static.getErreursForException(e), null);
		}
		return jsonMapper.writeValueAsString(reponse);
	}
}
