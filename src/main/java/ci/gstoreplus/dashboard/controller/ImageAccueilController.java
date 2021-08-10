package ci.gstoreplus.dashboard.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ci.gstoreplus.dashboard.metier.CloudinaryService;
import ci.gstoreplus.dashboard.metier.ImageService;
import ci.gstoreplus.dashboard.metier.catalogue.FlashMaisonMetier;
import ci.gstoreplus.dashboard.metier.catalogue.ImageAccueilMetier;
import ci.gstoreplus.entity.catalogue.FlashMaison;
import ci.gstoreplus.entity.catalogue.Image;
import ci.gstoreplus.entity.catalogue.ImageAccueil;
import ci.gstoreplus.entity.catalogue.Terrain;
import ci.gstoreplus.exception.InvalideImmobilierException;
import ci.gstoreplus.models.Reponse;
import ci.gstoreplus.utilitaire.Static;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ImageAccueilController {
	@Autowired
	private ImageAccueilMetier imageAccueilMetier;
	@Autowired
	CloudinaryService cloudinaryService;
   @Autowired
	private ObjectMapper jsonMapper;
	@Value("${dir.images}")
	private String storeImage;
	
	// recuper Categorie par identifiant
		private Reponse<ImageAccueil> getFlashMaisonById(Long id) {
			ImageAccueil imageAccueil = null;

			try {
				imageAccueil = imageAccueilMetier.findById(id);
				if (imageAccueil == null) {
					List<String> messages = new ArrayList<>();
					messages.add(String.format("Flash terrain n'existe pas", id));
					new Reponse<ImageAccueil>(2, messages, null);

				}
			} catch (RuntimeException e) {
				new Reponse<ImageAccueil>(1, Static.getErreursForException(e), null);
			}

			return new Reponse<ImageAccueil>(0, null, imageAccueil);
		}

	//////////////////////////////////////////////////////////////////////////////////////////////
	////////////////// enregistrer un terrain  dans la base de donnee
	////////////////////////////////////////////////////////////////////////////////////////////// donnee////////////////////////////////

		@PostMapping("/imageAccueil")
		public String creer(@RequestBody ImageAccueil imageAccueil) throws JsonProcessingException {
			System.out.println("voir terrain"+ imageAccueil);
			Reponse<ImageAccueil> reponse;
			
			try {
                 
				ImageAccueil t = imageAccueilMetier.creer(imageAccueil);
				List<String> messages = new ArrayList<>();
				messages.add(String.format("%s  à été créer avec succes", t.getId()));
				reponse = new Reponse<ImageAccueil>(0, messages, t);

			} catch (InvalideImmobilierException e) {

				reponse = new Reponse<ImageAccueil>(1, Static.getErreursForException(e), null);
			}
			return jsonMapper.writeValueAsString(reponse);
		}
		// recherche les terrains par id
			@GetMapping("/imageAccueil/{id}")
			public String getImageAccueilById(@PathVariable("id") Long id) throws JsonProcessingException {

				Reponse<ImageAccueil> reponse;

				try {

					ImageAccueil t = imageAccueilMetier.findById(id);
					List<String> messages = new ArrayList<>();
					messages.add(String.format(" à été créer avec succes"));
					reponse = new Reponse<ImageAccueil>(0, messages, t);

				} catch (Exception e) {

					reponse = new Reponse<ImageAccueil>(1, Static.getErreursForException(e), null);
				}
				return jsonMapper.writeValueAsString(reponse);
			}
			// supprimer un  terrain
					@DeleteMapping("/imageAccueil/{id}")
					public String supprimer(@PathVariable("id") Long id) throws JsonProcessingException {

						Reponse<Boolean> reponse = null;

						try {

							reponse = new Reponse<Boolean>(0, null, imageAccueilMetier.supprimer(id));

						} catch (RuntimeException e1) {
							reponse = new Reponse<>(3, Static.getErreursForException(e1), null);
						}

						return jsonMapper.writeValueAsString(reponse);
					}

		// get all Terrains
			@GetMapping("/imageAccueil")
			public String findAll() throws JsonProcessingException {
				Reponse<List<ImageAccueil>> reponse;
				try {
					List<ImageAccueil> terrains = imageAccueilMetier.findAll();
					if (!terrains.isEmpty()) {
						reponse = new Reponse<List<ImageAccueil>>(0, null, terrains);
					} else {
						List<String> messages = new ArrayList<>();
						messages.add("Pas de terrain enregistrés");
						reponse = new Reponse<List<ImageAccueil>>(1, messages, new ArrayList<>());
					}

				} catch (Exception e) {
					reponse = new Reponse<>(1, Static.getErreursForException(e), null);
				}
				return jsonMapper.writeValueAsString(reponse);

			}
			// solution alterntive cloudinary//////////////////////////
			@PostMapping("/uploadImageAccueil")
			public ResponseEntity<?> upload(@RequestParam MultipartFile multipartFile,
					@RequestParam Long id) throws IOException, InvalideImmobilierException{
				Map result = cloudinaryService.upload(multipartFile);
				ImageAccueil ft = imageAccueilMetier.findById(id);
				ft.setPath((String) result.get("url"));
				 imageAccueilMetier.modifier(ft);
				
				return new ResponseEntity(result, HttpStatus.OK);
			}
		
}
