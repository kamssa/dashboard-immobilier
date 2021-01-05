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
import ci.gstoreplus.dashboard.metier.catalogue.MaisonMetier;
import ci.gstoreplus.dashboard.metier.catalogue.TerrainMetier;
import ci.gstoreplus.entity.catalogue.Image;
import ci.gstoreplus.entity.catalogue.Maison;
import ci.gstoreplus.entity.catalogue.Terrain;
import ci.gstoreplus.exception.InvalideImmobilierException;
import ci.gstoreplus.models.Reponse;
import ci.gstoreplus.utilitaire.Static;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class MaisonController {
	@Autowired
	private MaisonMetier maisonMetier;
	@Autowired
	CloudinaryService cloudinaryService;
	@Autowired
	ImageService imageSevice;
	@Autowired
	private ObjectMapper jsonMapper;
	@Value("${dir.images}")
	private String storeImage;
	
	// recuper maison par identifiant
		private Reponse<Maison> getMaisonById(Long id) {
			Maison maison = null;

			try {
				maison = maisonMetier.findById(id);
				if (maison == null) {
					List<String> messages = new ArrayList<>();
					messages.add(String.format("cette maison n'existe pas", id));
					new Reponse<Terrain>(2, messages, null);

				}
			} catch (RuntimeException e) {
				new Reponse<Maison>(1, Static.getErreursForException(e), null);
			}

			return new Reponse<Maison>(0, null, maison);
		}

	//////////////////////////////////////////////////////////////////////////////////////////////
	////////////////// enregistrer une maison  dans la base de donnee
	////////////////////////////////////////////////////////////////////////////////////////////// donnee////////////////////////////////

		@PostMapping("/maison")
		public String creer(@RequestBody Maison maison) throws JsonProcessingException {
			System.out.println("voir maison"+ maison);
			Reponse<Maison> reponse;
			
			try {
                 
				Maison m = maisonMetier.creer(maison);
				List<String> messages = new ArrayList<>();
				messages.add(String.format("%s  à été créer avec succes", m.getId()));
				reponse = new Reponse<Maison>(0, messages, m);

			} catch (InvalideImmobilierException e) {

				reponse = new Reponse<Maison>(1, Static.getErreursForException(e), null);
			}
			return jsonMapper.writeValueAsString(reponse);
		}
		@PutMapping("/maison")
		public String update(@RequestBody Maison  modif) throws JsonProcessingException {

			Reponse<Maison> reponse = null;
			Reponse<Maison> reponseTerrainModif = null;
			// on recupere autre a modifier
			System.out.println("modif recupere1:"+ modif);
			reponseTerrainModif = getMaisonById(modif.getId());
			if (reponseTerrainModif.getBody() != null) {
				try {
					System.out.println("modif recupere2:"+ modif);
					Maison maison = maisonMetier.modifier(modif);
					List<String> messages = new ArrayList<>();
					messages.add(String.format("%s a modifier avec succes", maison.getId()));
					reponse = new Reponse<Maison>(0, messages, maison);
				} catch (InvalideImmobilierException e) {

					reponse = new Reponse<Maison>(1, Static.getErreursForException(e), null);
				}

			} else {
				List<String> messages = new ArrayList<>();
				messages.add(String.format("la maison n'existe pas"));
				reponse = new Reponse<Maison>(0, messages, null);
			}

			return jsonMapper.writeValueAsString(reponse);

		}
		// recherche les terrains par id
			@GetMapping("/maison/{id}")
			public String getMaisonByIdMaison(@PathVariable("id") Long id) throws JsonProcessingException {

				Reponse<Maison> reponse;

				try {

					Maison m = maisonMetier.findById(id);
					List<String> messages = new ArrayList<>();
					messages.add(String.format(" à été créer avec succes"));
					reponse = new Reponse<Maison>(0, messages, m);

				} catch (Exception e) {

					reponse = new Reponse<Maison>(1, Static.getErreursForException(e), null);
				}
				return jsonMapper.writeValueAsString(reponse);
			}
			// supprimer un  terrain
					@DeleteMapping("/maison/{id}")
					public String supprimer(@PathVariable("id") Long id) throws JsonProcessingException {

						Reponse<Boolean> reponse = null;

						try {

							reponse = new Reponse<Boolean>(0, null, maisonMetier.supprimer(id));

						} catch (RuntimeException e1) {
							reponse = new Reponse<>(3, Static.getErreursForException(e1), null);
						}

						return jsonMapper.writeValueAsString(reponse);
					}

		// get all Terrains
			@GetMapping("/maison")
			public String findAll() throws JsonProcessingException {
				Reponse<List<Maison>> reponse;
				try {
					List<Maison> maisons = maisonMetier.findAll();
					if (!maisons.isEmpty()) {
						reponse = new Reponse<List<Maison>>(0, null, maisons);
					} else {
						List<String> messages = new ArrayList<>();
						messages.add("Pas de terrain enregistrés");
						reponse = new Reponse<List<Maison>>(1, messages, new ArrayList<>());
					}

				} catch (Exception e) {
					reponse = new Reponse<>(1, Static.getErreursForException(e), null);
				}
				return jsonMapper.writeValueAsString(reponse);

			}
	
	//////////recupere les terrains par ville
				@GetMapping("/getMaisonByIdVille/{id}")
				public String chercherParVille(@PathVariable Long id) throws JsonProcessingException {
					Reponse<List<Maison>> reponse = null;

					try {
						List<Maison> db = maisonMetier.findMaisonByIdVille(id);
						if (!db.isEmpty()) {
							reponse = new Reponse<List<Maison>>(0, null, db);
						} else {
							List<String> messages = new ArrayList<>();
							messages.add(String.format("pas de maison enregistrer "));
							reponse = new Reponse<List<Maison>>(2, messages, new ArrayList<>());
						}

					} catch (Exception e) {
						reponse = new Reponse<>(1, Static.getErreursForException(e), null);
					}
					return jsonMapper.writeValueAsString(reponse);

				}
				
				// solution alterntive cloudinary//////////////////////////
				@PostMapping("/uploadMaison")
				public ResponseEntity<?> upload(@RequestParam MultipartFile multipartFile,
						@RequestParam Long id) throws IOException, InvalideImmobilierException{
					Map result = cloudinaryService.upload(multipartFile);
					Maison maison = maisonMetier.findById(id);
					maison.setPath((String) result.get("url"));
					maisonMetier.modifier(maison);
					
					return new ResponseEntity(result, HttpStatus.OK);
				}
				// supp image
				@DeleteMapping("/supMaison/{id}")
				public ResponseEntity<?> delete(@PathVariable("id") Long id) throws IOException{
					if(!imageSevice.exists(id)) {
						new InvalideImmobilierException("l'image n'xiste pas");
					}
					Image image = imageSevice.findById(id).get();
					Map result = cloudinaryService.delete(image.getImageId());
					imageSevice.deleteById(id);
					return new ResponseEntity(new InvalideImmobilierException("image supprimée"), HttpStatus.OK);
				}
				@GetMapping("/downloadImgM/{publicId}")
			     public ResponseEntity<ByteArrayResource> downloadImg(@PathVariable String publicId) {
			        return cloudinaryService.downloadImg(publicId);
			    }
				
}
