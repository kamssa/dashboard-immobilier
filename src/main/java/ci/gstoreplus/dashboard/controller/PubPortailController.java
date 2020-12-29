package ci.gstoreplus.dashboard.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
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

import ci.gstoreplus.dashboard.metier.catalogue.FlashTerrainMetier;
import ci.gstoreplus.dashboard.metier.catalogue.PubPortailMetier;
import ci.gstoreplus.entity.catalogue.FlashTerrain;
import ci.gstoreplus.entity.catalogue.PubPortail;
import ci.gstoreplus.entity.catalogue.Terrain;
import ci.gstoreplus.exception.InvalideImmobilierException;
import ci.gstoreplus.metier.Imetier;
import ci.gstoreplus.models.Reponse;
import ci.gstoreplus.utilitaire.Static;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class PubPortailController {
	@Autowired
	private PubPortailMetier pubPortailMetier;
	@Autowired
	private ObjectMapper jsonMapper;
	@Value("${dir.images}")
	private String storeImage;
	private Imetier<FlashTerrain, Long> flashTerrainMetier;
	
	// recuper Categorie par identifiant
		private Reponse<PubPortail> getPubPotailById(Long id) {
			PubPortail pubPotail = null;

			try {
				pubPotail = pubPortailMetier.findById(id);
				if (pubPotail == null) {
					List<String> messages = new ArrayList<>();
					messages.add(String.format("pubPotail  n'existe pas", id));
					new Reponse<PubPortail>(2, messages, null);

				}
			} catch (RuntimeException e) {
				new Reponse<PubPortail>(1, Static.getErreursForException(e), null);
			}

			return new Reponse<PubPortail>(0, null, pubPotail);
		}

	//////////////////////////////////////////////////////////////////////////////////////////////
	////////////////// enregistrer un pubPotail  dans la base de donnee
	////////////////////////////////////////////////////////////////////////////////////////////// donnee////////////////////////////////

		@PostMapping("/pubPotail")
		public String creer(@RequestBody PubPortail pubPotail) throws JsonProcessingException {
			System.out.println("voir terrain"+ pubPotail);
			Reponse<PubPortail> reponse;
			
			try {
                 
				PubPortail t = pubPortailMetier.creer(pubPotail);
				List<String> messages = new ArrayList<>();
				messages.add(String.format("%s  à été créer avec succes", t.getId()));
				reponse = new Reponse<PubPortail>(0, messages, t);

			} catch (InvalideImmobilierException e) {

				reponse = new Reponse<PubPortail>(1, Static.getErreursForException(e), null);
			}
			return jsonMapper.writeValueAsString(reponse);
		}
		@PutMapping("/pubPotail")
		public String update(@RequestBody PubPortail  modif) throws JsonProcessingException {

			Reponse<PubPortail> reponse = null;
			Reponse<PubPortail> reponseTerrainModif = null;
			// on recupere autre a modifier
			System.out.println("modif recupere1:"+ modif);
			reponseTerrainModif = getPubPotailById(modif.getId());
			if (reponseTerrainModif.getBody() != null) {
				try {
					System.out.println("modif recupere2:"+ modif);
					PubPortail pubPotail = pubPortailMetier.modifier(modif);
					List<String> messages = new ArrayList<>();
					messages.add(String.format("%s a modifier avec succes", pubPotail.getId()));
					reponse = new Reponse<PubPortail>(0, messages, pubPotail);
				} catch (InvalideImmobilierException e) {

					reponse = new Reponse<PubPortail>(1, Static.getErreursForException(e), null);
				}

			} else {
				List<String> messages = new ArrayList<>();
				messages.add(String.format("Le PubPotail n'existe pas"));
				reponse = new Reponse<PubPortail>(0, messages, null);
			}

			return jsonMapper.writeValueAsString(reponse);

		}
		// recherche les terrains par id
			@GetMapping("/pubPotail/{id}")
			public String getTerrainsById(@PathVariable("id") Long id) throws JsonProcessingException {

				Reponse<PubPortail> reponse;

				try {

					PubPortail t = pubPortailMetier.findById(id);
					List<String> messages = new ArrayList<>();
					messages.add(String.format(" à été créer avec succes"));
					reponse = new Reponse<PubPortail>(0, messages, t);

				} catch (Exception e) {

					reponse = new Reponse<PubPortail>(1, Static.getErreursForException(e), null);
				}
				return jsonMapper.writeValueAsString(reponse);
			}
			// supprimer un  terrain
					@DeleteMapping("/pubPotail/{id}")
					public String supprimer(@PathVariable("id") Long id) throws JsonProcessingException {

						Reponse<Boolean> reponse = null;

						try {

							reponse = new Reponse<Boolean>(0, null, flashTerrainMetier.supprimer(id));

						} catch (RuntimeException e1) {
							reponse = new Reponse<>(3, Static.getErreursForException(e1), null);
						}

						return jsonMapper.writeValueAsString(reponse);
					}

		// get all Terrains
			@GetMapping("/pubPotail")
			public String findAll() throws JsonProcessingException {
				Reponse<List<PubPortail>> reponse;
				try {
					List<PubPortail> terrains = pubPortailMetier.findAll();
					if (!terrains.isEmpty()) {
						reponse = new Reponse<List<PubPortail>>(0, null, terrains);
					} else {
						List<String> messages = new ArrayList<>();
						messages.add("Pas de PubPotail enregistrés");
						reponse = new Reponse<List<PubPortail>>(1, messages, new ArrayList<>());
					}

				} catch (Exception e) {
					reponse = new Reponse<>(1, Static.getErreursForException(e), null);
				}
				return jsonMapper.writeValueAsString(reponse);

			}
	
			/////////////////////////////////////////////////////////////////////////////////////////////////
			////// ajouter une image a la base a partir du libelle d'un block
			///////////////////////////////////////////////////////////////////////////////////////////////// //////////////////////////////

			@PostMapping("/imagePubPotail")
			public String createImage(@RequestParam(name = "image_PubPotail") 
			MultipartFile file, @RequestParam Long id) throws Exception {
				Reponse<PubPortail> reponse = null;
				Reponse<PubPortail> reponseParLibelle;
				// recuperer le libelle à partir du nom de la photo
				String libelle = file.getOriginalFilename();
				PubPortail t = pubPortailMetier.findById(id);
                System.out.println(t);
                String path = "http://localhost:8080/api/getImagePubPotail/" + t.getVersion() + "/" + id+ "/"+libelle;
				System.out.println(path);

				String dossier = storeImage + "/" + "PubPotailImage" + "/"+id +"/";
				File rep = new File(dossier);

				if (!file.isEmpty()) {
					if (!rep.exists() && !rep.isDirectory()) {
						rep.mkdir();
					}
				}
				try {
					// enregistrer le chemin dans la photo
					t.setPath(path);
					System.out.println(path);
					file.transferTo(new File(dossier + file.getOriginalFilename()));
					List<String> messages = new ArrayList<>();
					messages.add(String.format("%s (image ajouter avec succes)", t.getLibelle()));
					reponse = new Reponse<PubPortail>(0, messages, pubPortailMetier.modifier(t));

				} catch (Exception e) {

					reponse = new Reponse<PubPortail>(1, Static.getErreursForException(e), null);
				}

				return jsonMapper.writeValueAsString(reponse);
			}

			//////// recuperer une photo avec pour retour tableau de byte
			//////// /////////////////////////////////

			@GetMapping(value = "/getImagePubPotail/{version}/{id}/{libelle}", produces = MediaType.IMAGE_JPEG_VALUE)
			public byte[] getPhotos(@PathVariable Long version,
					@PathVariable Long id, @PathVariable String libelle)
					throws FileNotFoundException, IOException {

				String dossier = storeImage + "/" + "PubPotailImage" + "/"+id +"/" + libelle;
				File f = new File(dossier);
				byte[] img = IOUtils.toByteArray(new FileInputStream(f));

				return img;
			}
}
