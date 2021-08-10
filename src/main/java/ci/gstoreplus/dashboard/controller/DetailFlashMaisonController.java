package ci.gstoreplus.dashboard.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ci.gstoreplus.dashboard.metier.CloudinaryService;
import ci.gstoreplus.dashboard.metier.DetailFlashMaisonMetier;
import ci.gstoreplus.dashboard.metier.catalogue.ImageDetailFlashMaisonMetier;
import ci.gstoreplus.entity.catalogue.DetailFlashMaison;
import ci.gstoreplus.entity.catalogue.ImageDetailFlashMaison;
import ci.gstoreplus.exception.InvalideImmobilierException;
import ci.gstoreplus.models.Reponse;
import ci.gstoreplus.utilitaire.Static;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class DetailFlashMaisonController {
	@Autowired
	private DetailFlashMaisonMetier detailFlashMaisonMetier;
	@Autowired
	private ImageDetailFlashMaisonMetier imageDetailFlashMaisonMetier;
	@Autowired
	private ObjectMapper jsonMapper;
	@Autowired
	CloudinaryService cloudinaryService;
	


// recuper categorie par identifiant
	private Reponse<DetailFlashMaison> getDetailDetailFlashMaisonById(Long id) {
		DetailFlashMaison detailFlashMaison = null;

		try {
			detailFlashMaison = detailFlashMaisonMetier.findById(id);
			if (detailFlashMaison == null) {
				List<String> messages = new ArrayList<>();
				messages.add(String.format("Le detailTerrain n'existe pas", id));
				new Reponse<DetailFlashMaison>(2, messages, null);

			}
		} catch (RuntimeException e) {
			new Reponse<DetailFlashMaison>(1, Static.getErreursForException(e), null);
		}

		return new Reponse<DetailFlashMaison>(0, null, detailFlashMaison);
	}

//////////////////////////////////////////////////////////////////////////////////////////////
////////////////// enregistrer une categories  dans la base de donnee
////////////////////////////////////////////////////////////////////////////////////////////// donnee////////////////////////////////

	@PostMapping("/detailFlashMaison")
	public String creer(@RequestBody DetailFlashMaison detailFlashMaison) throws JsonProcessingException {
		Reponse<DetailFlashMaison> reponse;
		System.out.println(detailFlashMaison);
		try {

			DetailFlashMaison detailArticle = detailFlashMaisonMetier.creer(detailFlashMaison);
			List<String> messages = new ArrayList<>();
			messages.add(String.format("%s  à été créer avec succes", detailArticle.getId()));
			reponse = new Reponse<DetailFlashMaison>(0, messages, detailArticle);

		} catch (InvalideImmobilierException e) {

			reponse = new Reponse<DetailFlashMaison>(1, Static.getErreursForException(e), null);
		}
		return jsonMapper.writeValueAsString(reponse);
	}

	@PutMapping("/detailFlashMaison")
	public String update(@RequestBody DetailFlashMaison modif) throws JsonProcessingException {

		Reponse<DetailFlashMaison> reponse = null;
		Reponse<DetailFlashMaison> reponsePersModif = null;
		// on recupere abonnement a modifier
		System.out.println("modif recupere1:" + modif);
		reponsePersModif = getDetailDetailFlashMaisonById(modif.getId());
		if (reponsePersModif.getBody() != null) {
			try {
				System.out.println("modif recupere2:" + modif);
				DetailFlashMaison detailArticles = detailFlashMaisonMetier.modifier(modif);
				List<String> messages = new ArrayList<>();
				messages.add(String.format("%s a modifier avec succes", detailArticles.getId()));
				reponse = new Reponse<DetailFlashMaison>(0, messages, detailArticles);
			} catch (InvalideImmobilierException e) {

				reponse = new Reponse<DetailFlashMaison>(1, Static.getErreursForException(e), null);
			}

		} else {
			List<String> messages = new ArrayList<>();
			messages.add(String.format(" DetailTerrain n'existe pas"));
			reponse = new Reponse<DetailFlashMaison>(0, messages, null);
		}

		return jsonMapper.writeValueAsString(reponse);

	}

    ////////recuperer une categorie  par son id
	@GetMapping("/detailFlashMaison/{id}")
	public String getById(@PathVariable Long id) throws JsonProcessingException {
		// Annotation @PathVariable permet de recuperer le paremettre dans URI
		Reponse<DetailFlashMaison> reponse = null;

		reponse = getDetailDetailFlashMaisonById(id);
		if (reponse.getBody() == null) {
			throw new RuntimeException("pas d'enregistrement pour ce DetailTerrain");
		}

		return jsonMapper.writeValueAsString(reponse);

	}
	@GetMapping("/detailFlashByIdflash/{id}")
	public String getByIdDetailMaison(@PathVariable Long id) throws JsonProcessingException {
		Reponse<List<DetailFlashMaison>> reponse;
		try {
			List<DetailFlashMaison> detailTerrain = detailFlashMaisonMetier.findDetailFashMaisonIdFlashMaison(id);
			
		reponse = new Reponse<List<DetailFlashMaison>>(0, null, detailTerrain);
			
     } catch (Exception e) {
			reponse = new Reponse<>(1, Static.getErreursForException(e), null);
		}
		return jsonMapper.writeValueAsString(reponse);
	}

        //get all categories
	@GetMapping("/detailFlashMaison")
	public String findAll() throws JsonProcessingException {
		Reponse<List<DetailFlashMaison>> reponse;
		try {
			List<DetailFlashMaison> detailTerrains = detailFlashMaisonMetier.findAll();
			if (!detailTerrains.isEmpty()) {
				reponse = new Reponse<List<DetailFlashMaison>>(0, null, detailTerrains);
			} else {
				List<String> messages = new ArrayList<>();
				messages.add("Pas d'abonnés enregistrées");
				reponse = new Reponse<List<DetailFlashMaison>>(1, messages, new ArrayList<>());
			}

		} catch (Exception e) {
			reponse = new Reponse<>(1, Static.getErreursForException(e), null);
		}
		return jsonMapper.writeValueAsString(reponse);

	}
	// supprimer une categorie
		@DeleteMapping("/detailFlashMaison/{id}")
		public String supprimer(@PathVariable("id") Long id) throws JsonProcessingException {

			Reponse<Boolean> reponse = null;

			try {
				 List<ImageDetailFlashMaison> imgd =  imageDetailFlashMaisonMetier.findImageByIdDetailFlash(id);
				 imageDetailFlashMaisonMetier.supprimer(imgd);
				reponse = new Reponse<Boolean>(0, null, detailFlashMaisonMetier.supprimer(id));

			} catch (RuntimeException e1) {
				reponse = new Reponse<>(3, Static.getErreursForException(e1), null);
			}

			return jsonMapper.writeValueAsString(reponse);
		}

		@GetMapping("/imageDetailFlashMaison/{id}")
		public String getImageByIdDetailMaison(@PathVariable("id") long id)
				throws JsonProcessingException, InvalideImmobilierException{
			Reponse<List<ImageDetailFlashMaison>> reponse;
			try {
				List<ImageDetailFlashMaison> photos = imageDetailFlashMaisonMetier.findImageByIdDetailFlash(id);
				if (!photos.isEmpty()) {
					reponse = new Reponse<List<ImageDetailFlashMaison>>(0, null, photos);
				} else {
					List<String> messages = new ArrayList<>();
					messages.add("Pas de photos enregistrées");
					reponse = new Reponse<List<ImageDetailFlashMaison>>(1, messages, new ArrayList<>());
				}

			} catch (Exception e) {
				reponse = new Reponse<List<ImageDetailFlashMaison>>(1, Static.getErreursForException(e), new ArrayList<>());
			}
			return jsonMapper.writeValueAsString(reponse);

		}
		/*@GetMapping("/imageDetailByIdDetailFlashMaison/{id}")
		public String getImageByDetailMaisonBy(@PathVariable Long id) throws JsonProcessingException {
			Reponse<List<ImageDetailFlashMaison>> reponse;
			try {
				List<ImageDetailFlashMaison> imageDetail = imageDetailFlashMaisonMetier.findImageByIdDetailFlash(id);
				if (!imageDetail.isEmpty()) {
					reponse = new Reponse<List<ImageDetailFlashMaison>>(0, null, imageDetail);
				} else {
					List<String> messages = new ArrayList<>();
					messages.add("Pas d' imageDetail enregistrées");
					reponse = new Reponse<List<ImageDetailFlashMaison>>(1, messages, new ArrayList<>());
				}

			} catch (Exception e) {
				reponse = new Reponse<>(1, Static.getErreursForException(e), null);
			}
			return jsonMapper.writeValueAsString(reponse);
		}*/

}
