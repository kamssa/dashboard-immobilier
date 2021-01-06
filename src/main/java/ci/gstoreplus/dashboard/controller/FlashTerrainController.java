package ci.gstoreplus.dashboard.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import ci.gstoreplus.dashboard.metier.catalogue.FlashTerrainMetier;
import ci.gstoreplus.dashboard.metier.catalogue.TerrainMetier;
import ci.gstoreplus.entity.catalogue.FlashTerrain;
import ci.gstoreplus.entity.catalogue.Image;
import ci.gstoreplus.entity.catalogue.Terrain;
import ci.gstoreplus.exception.InvalideImmobilierException;
import ci.gstoreplus.models.Reponse;
import ci.gstoreplus.utilitaire.Static;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class FlashTerrainController {
	@Autowired
	private FlashTerrainMetier flashTerrainMetier;
	@Autowired
	CloudinaryService cloudinaryService;
	@Autowired
	ImageService imageSevice;
	@Autowired
	private ObjectMapper jsonMapper;
	@Value("${dir.images}")
	private String storeImage;
	
	// recuper Categorie par identifiant
		private Reponse<FlashTerrain> getFlashTerrainById(Long id) {
			FlashTerrain flashTerrain = null;

			try {
				flashTerrain = flashTerrainMetier.findById(id);
				if (flashTerrain == null) {
					List<String> messages = new ArrayList<>();
					messages.add(String.format("Flash terrain n'existe pas", id));
					new Reponse<Terrain>(2, messages, null);

				}
			} catch (RuntimeException e) {
				new Reponse<FlashTerrain>(1, Static.getErreursForException(e), null);
			}

			return new Reponse<FlashTerrain>(0, null, flashTerrain);
		}

	//////////////////////////////////////////////////////////////////////////////////////////////
	////////////////// enregistrer un terrain  dans la base de donnee
	////////////////////////////////////////////////////////////////////////////////////////////// donnee////////////////////////////////

		@PostMapping("/flashTerrain")
		public String creer(@RequestBody FlashTerrain flashTerrain) throws JsonProcessingException {
			System.out.println("voir terrain"+ flashTerrain);
			Reponse<FlashTerrain> reponse;
			
			try {
                 
				FlashTerrain t = flashTerrainMetier.creer(flashTerrain);
				List<String> messages = new ArrayList<>();
				messages.add(String.format("%s  à été créer avec succes", t.getId()));
				reponse = new Reponse<FlashTerrain>(0, messages, t);

			} catch (InvalideImmobilierException e) {

				reponse = new Reponse<FlashTerrain>(1, Static.getErreursForException(e), null);
			}
			return jsonMapper.writeValueAsString(reponse);
		}
		@PutMapping("/flashTerrain")
		public String update(@RequestBody FlashTerrain  modif) throws JsonProcessingException {

			Reponse<FlashTerrain> reponse = null;
			Reponse<FlashTerrain> reponseTerrainModif = null;
			// on recupere autre a modifier
			System.out.println("modif recupere1:"+ modif);
			reponseTerrainModif = getFlashTerrainById(modif.getId());
			if (reponseTerrainModif.getBody() != null) {
				try {
					modif.setPath(reponseTerrainModif.getBody().getPath());
					System.out.println("modif recupere2:"+ modif);
					System.out.println("modif recupere2:"+ modif);
					FlashTerrain terrain = flashTerrainMetier.modifier(modif);
					List<String> messages = new ArrayList<>();
					messages.add(String.format("%s a modifier avec succes", terrain.getId()));
					reponse = new Reponse<FlashTerrain>(0, messages, terrain);
				} catch (InvalideImmobilierException e) {

					reponse = new Reponse<FlashTerrain>(1, Static.getErreursForException(e), null);
				}

			} else {
				List<String> messages = new ArrayList<>();
				messages.add(String.format("Le Terrain n'existe pas"));
				reponse = new Reponse<FlashTerrain>(0, messages, null);
			}

			return jsonMapper.writeValueAsString(reponse);

		}
		// recherche les terrains par id
			@GetMapping("/flashTerrain/{id}")
			public String getTerrainsById(@PathVariable("id") Long id) throws JsonProcessingException {

				Reponse<FlashTerrain> reponse;

				try {

					FlashTerrain t = flashTerrainMetier.findById(id);
					List<String> messages = new ArrayList<>();
					messages.add(String.format(" à été créer avec succes"));
					reponse = new Reponse<FlashTerrain>(0, messages, t);

				} catch (Exception e) {

					reponse = new Reponse<FlashTerrain>(1, Static.getErreursForException(e), null);
				}
				return jsonMapper.writeValueAsString(reponse);
			}
			// supprimer un  terrain
					@DeleteMapping("/flashTerrain/{id}")
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
			@GetMapping("/flashTerrain")
			public String findAll() throws JsonProcessingException {
				Reponse<List<FlashTerrain>> reponse;
				try {
					List<FlashTerrain> terrains = flashTerrainMetier.findAll();
					if (!terrains.isEmpty()) {
						reponse = new Reponse<List<FlashTerrain>>(0, null, terrains);
					} else {
						List<String> messages = new ArrayList<>();
						messages.add("Pas de terrain enregistrés");
						reponse = new Reponse<List<FlashTerrain>>(1, messages, new ArrayList<>());
					}

				} catch (Exception e) {
					reponse = new Reponse<>(1, Static.getErreursForException(e), null);
				}
				return jsonMapper.writeValueAsString(reponse);

			}
			// solution alterntive cloudinary//////////////////////////
			@PostMapping("/uploadf")
			public ResponseEntity<?> upload(@RequestParam MultipartFile multipartFile,
					@RequestParam Long id) throws IOException, InvalideImmobilierException{
				Map result = cloudinaryService.upload(multipartFile);
				FlashTerrain ft = flashTerrainMetier.findById(id);
				ft.setPath((String) result.get("url"));
				 flashTerrainMetier.modifier(ft);
				
				return new ResponseEntity(result, HttpStatus.OK);
			}
			// supp image
			@DeleteMapping("/supprim/{id}")
			public ResponseEntity<?> delete(@PathVariable("id") Long id) throws IOException{
				Image image = imageSevice.findById(id).get();
				Map result = cloudinaryService.delete(image.getImageId());
				imageSevice.deleteById(id);
				return new ResponseEntity(new InvalideImmobilierException("image supprimée"), HttpStatus.OK);
			}
			@GetMapping("/downloadImgft/{publicId}")
		     public ResponseEntity<ByteArrayResource> downloadImg(@PathVariable String publicId) {
		        return cloudinaryService.downloadImg(publicId);
		    }
			/////////////////////////////////////////////////////////////////////////////////////////////////
			////// ajouter une image a la base a partir du libelle d'un block
			///////////////////////////////////////////////////////////////////////////////////////////////// //////////////////////////////

			/*@PostMapping("/imageFlashTerrain")
			public String createImage(@RequestParam(name = "image_flash") 
			MultipartFile file, @RequestParam Long id) throws Exception {
				Reponse<FlashTerrain> reponse = null;
				Reponse<FlashTerrain> reponseParLibelle;
				// recuperer le libelle à partir du nom de la photo
				String libelle = file.getOriginalFilename();
				FlashTerrain t = flashTerrainMetier.findById(id);
                System.out.println(t);
                String path = "http://localhost:8080/api/getImageFlashTerrain/" + t.getVersion() + "/" + id+ "/"+libelle;
				System.out.println(path);

				String dossier = storeImage + "/" + "FlashTerrainImage" + "/"+id +"/";
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
					reponse = new Reponse<FlashTerrain>(0, messages, flashTerrainMetier.modifier(t));

				} catch (Exception e) {

					reponse = new Reponse<FlashTerrain>(1, Static.getErreursForException(e), null);
				}

				return jsonMapper.writeValueAsString(reponse);
			}

			//////// recuperer une photo avec pour retour tableau de byte
			//////// /////////////////////////////////

			@GetMapping(value = "/getImageFlashTerrain/{version}/{id}/{libelle}", produces = MediaType.IMAGE_JPEG_VALUE)
			public byte[] getPhotos(@PathVariable Long version,
					@PathVariable Long id, @PathVariable String libelle)
					throws FileNotFoundException, IOException {

				String dossier = storeImage + "/" + "FlashTerrainImage" + "/"+id +"/" + libelle;
				File f = new File(dossier);
				byte[] img = IOUtils.toByteArray(new FileInputStream(f));

				return img;
			}*/
}
