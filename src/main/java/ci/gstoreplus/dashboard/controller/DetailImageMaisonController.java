package ci.gstoreplus.dashboard.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import ci.gstoreplus.dashboard.metier.catalogue.DetailMaisonMetier;
import ci.gstoreplus.dashboard.metier.catalogue.DetailTerrainMetier;
import ci.gstoreplus.dashboard.metier.catalogue.ImageDetailMaisonMetier;
import ci.gstoreplus.dashboard.metier.catalogue.ImageDetailTerrainMetier;
import ci.gstoreplus.entity.catalogue.DetailMaison;
import ci.gstoreplus.entity.catalogue.DetailTerrain;
import ci.gstoreplus.entity.catalogue.ImageDetail;
import ci.gstoreplus.entity.catalogue.ImageDetailMaison;
import ci.gstoreplus.exception.InvalideImmobilierException;
import ci.gstoreplus.models.Reponse;
import ci.gstoreplus.utilitaire.Static;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class DetailImageMaisonController {
	
	@Autowired
	private ImageDetailMaisonMetier imageDetailMaisonMetier;
	@Autowired
	private DetailMaisonMetier detailMaisonMetier;

	@Autowired
	private ObjectMapper jsonMapper;
	@Autowired
	CloudinaryService cloudinaryService;
	
	// solution alterntive cloudinary//////////////////////////
			@PostMapping("/uploadDetailMaison")
			public ResponseEntity<?> uploadMaison(@RequestParam MultipartFile multipartFile,
					@RequestParam Long id) throws IOException, InvalideImmobilierException{
				Map result = cloudinaryService.upload(multipartFile);
				DetailMaison d = detailMaisonMetier.findById(id);
				System.out.println("voir image detail maison:"+d);
				ImageDetailMaison image = new ImageDetailMaison();
				image.setDetailMaison(d);
				image.setImageUrl((String) result.get("url"));
				System.out.println("voir image:"+d);
				imageDetailMaisonMetier.creer(image);
				
				
				//imageMetier.modifier(img);
				return new ResponseEntity(result, HttpStatus.OK);
			}
			@GetMapping("/imageDetailMaisonByIdDetail/{id}")
			public String getByIdDetail(@PathVariable Long id) throws JsonProcessingException {
				Reponse<List<ImageDetailMaison>> reponse;
				try {
					List<ImageDetailMaison> imageDetail = imageDetailMaisonMetier.findImageByIdDetailMaisonn(id);
					if (!imageDetail.isEmpty()) {
						reponse = new Reponse<List<ImageDetailMaison>>(0, null, imageDetail);
					} else {
						List<String> messages = new ArrayList<>();
						messages.add("Pas d' imageDetail enregistrées");
						reponse = new Reponse<List<ImageDetailMaison>>(1, messages, new ArrayList<>());
					}

				} catch (Exception e) {
					reponse = new Reponse<>(1, Static.getErreursForException(e), null);
				}
				return jsonMapper.writeValueAsString(reponse);
			}
			
}
