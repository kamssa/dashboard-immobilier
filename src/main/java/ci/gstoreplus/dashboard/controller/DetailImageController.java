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
import ci.gstoreplus.dashboard.metier.catalogue.DetailTerrainMetier;
import ci.gstoreplus.dashboard.metier.catalogue.ImageDetailTerrainMetier;
import ci.gstoreplus.entity.catalogue.DetailTerrain;
import ci.gstoreplus.entity.catalogue.ImageDetail;
import ci.gstoreplus.exception.InvalideImmobilierException;
import ci.gstoreplus.models.Reponse;
import ci.gstoreplus.utilitaire.Static;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class DetailImageController {
	
	@Autowired
	private ImageDetailTerrainMetier imageDetailTerrainMetier;
	@Autowired
	private DetailTerrainMetier detailTerrainMetier;

	@Autowired
	private ObjectMapper jsonMapper;
	@Autowired
	CloudinaryService cloudinaryService;
	
	// solution alterntive cloudinary//////////////////////////
			@PostMapping("/uploadDetail")
			public ResponseEntity<?> upload(@RequestParam MultipartFile multipartFile,
					@RequestParam Long id) throws IOException, InvalideImmobilierException{
				
				 
				 DetailTerrain  d = detailTerrainMetier.findById(id);
				  
			   	Map result = cloudinaryService.upload(multipartFile);
					ImageDetail image = new ImageDetail();
					image.setDetailTerrain(d);
					image.setImageUrl((String) result.get("url"));
					
					//System.out.println(res);
				   imageDetailTerrainMetier.modifier(image);
				
				
				//imageMetier.modifier(img);
				return new ResponseEntity(result, HttpStatus.OK);
			}
			
			@GetMapping("/imageDetailByIdDetail/{id}")
			public String getByIdDetail(@PathVariable Long id) throws JsonProcessingException {
				Reponse<List<ImageDetail>> reponse;
				try {
					List<ImageDetail> imageDetail = imageDetailTerrainMetier.findImageByIdDetailTerrain(id);
					if (!imageDetail.isEmpty()) {
						reponse = new Reponse<List<ImageDetail>>(0, null, imageDetail);
					} else {
						List<String> messages = new ArrayList<>();
						messages.add("Pas d' imageDetail enregistrées");
						reponse = new Reponse<List<ImageDetail>>(1, messages, new ArrayList<>());
					}

				} catch (Exception e) {
					reponse = new Reponse<>(1, Static.getErreursForException(e), null);
				}
				return jsonMapper.writeValueAsString(reponse);
			}
			// supprimer une categorie
			@DeleteMapping("/imageDetail/{id}")
			public String supprimer(@PathVariable("id") Long id) throws JsonProcessingException {

				Reponse<Boolean> reponse = null;

				try {

					reponse = new Reponse<Boolean>(0, null, imageDetailTerrainMetier.supprimer(id));

				} catch (RuntimeException e1) {
					reponse = new Reponse<>(3, Static.getErreursForException(e1), null);
				}

				return jsonMapper.writeValueAsString(reponse);
			}
			
}
