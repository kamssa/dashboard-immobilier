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
public class DetailMaisonController {
	@Autowired
	private DetailMaisonMetier detailMaisonMetier;
	@Autowired
	private ImageDetailMaisonMetier imageDetailMaisonMetier;

	@Autowired
	private ObjectMapper jsonMapper;
	@Autowired
	CloudinaryService cloudinaryService;
	


// recuper categorie par identifiant
	private Reponse<DetailMaison> getDetailMaisonById(Long id) {
		DetailMaison detailMaison = null;

		try {
			detailMaison = detailMaisonMetier.findById(id);
			if (detailMaison == null) {
				List<String> messages = new ArrayList<>();
				messages.add(String.format("Le detailTerrain n'existe pas", id));
				new Reponse<DetailMaison>(2, messages, null);

			}
		} catch (RuntimeException e) {
			new Reponse<DetailMaison>(1, Static.getErreursForException(e), null);
		}

		return new Reponse<DetailMaison>(0, null, detailMaison);
	}

//////////////////////////////////////////////////////////////////////////////////////////////
////////////////// enregistrer une categories  dans la base de donnee
////////////////////////////////////////////////////////////////////////////////////////////// donnee////////////////////////////////

	@PostMapping("/detailMaison")
	public String creer(@RequestBody DetailMaison detailMaison) throws JsonProcessingException {
		Reponse<DetailMaison> reponse;
		System.out.println(detailMaison);
		try {

			DetailMaison detailArticle = detailMaisonMetier.creer(detailMaison);
			List<String> messages = new ArrayList<>();
			messages.add(String.format("%s  à été créer avec succes", detailArticle.getId()));
			reponse = new Reponse<DetailMaison>(0, messages, detailArticle);

		} catch (InvalideImmobilierException e) {

			reponse = new Reponse<DetailMaison>(1, Static.getErreursForException(e), null);
		}
		return jsonMapper.writeValueAsString(reponse);
	}

	@PutMapping("/detailMaison")
	public String update(@RequestBody DetailMaison modif) throws JsonProcessingException {

		Reponse<DetailMaison> reponse = null;
		Reponse<DetailMaison> reponsePersModif = null;
		// on recupere abonnement a modifier
		System.out.println("modif recupere1:" + modif);
		reponsePersModif = getDetailMaisonById(modif.getId());
		if (reponsePersModif.getBody() != null) {
			try {
				System.out.println("modif recupere2:" + modif);
				DetailMaison detailArticles = detailMaisonMetier.modifier(modif);
				List<String> messages = new ArrayList<>();
				messages.add(String.format("%s a modifier avec succes", detailArticles.getId()));
				reponse = new Reponse<DetailMaison>(0, messages, detailArticles);
			} catch (InvalideImmobilierException e) {

				reponse = new Reponse<DetailMaison>(1, Static.getErreursForException(e), null);
			}

		} else {
			List<String> messages = new ArrayList<>();
			messages.add(String.format(" DetailTerrain n'existe pas"));
			reponse = new Reponse<DetailMaison>(0, messages, null);
		}

		return jsonMapper.writeValueAsString(reponse);

	}

    ////////recuperer une categorie  par son id
	@GetMapping("/detailMaison/{id}")
	public String getById(@PathVariable Long id) throws JsonProcessingException {
		// Annotation @PathVariable permet de recuperer le paremettre dans URI
		Reponse<DetailMaison> reponse = null;

		reponse = getDetailMaisonById(id);
		if (reponse.getBody() == null) {
			throw new RuntimeException("pas d'enregistrement pour ce DetailTerrain");
		}

		return jsonMapper.writeValueAsString(reponse);

	}
	@GetMapping("/detailMaisonByIdMaison/{id}")
	public String getByIdDetailMaison(@PathVariable Long id) throws JsonProcessingException {
		Reponse<List<DetailMaison>> reponse;
		try {
			List<DetailMaison> detailTerrain = detailMaisonMetier.findDetailMaisonIdMaison(id);
			
		reponse = new Reponse<List<DetailMaison>>(0, null, detailTerrain);
			
     } catch (Exception e) {
			reponse = new Reponse<>(1, Static.getErreursForException(e), null);
		}
		return jsonMapper.writeValueAsString(reponse);
	}

        //get all categories
	@GetMapping("/detailMaison")
	public String findAll() throws JsonProcessingException {
		Reponse<List<DetailMaison>> reponse;
		try {
			List<DetailMaison> detailTerrains = detailMaisonMetier.findAll();
			if (!detailTerrains.isEmpty()) {
				reponse = new Reponse<List<DetailMaison>>(0, null, detailTerrains);
			} else {
				List<String> messages = new ArrayList<>();
				messages.add("Pas d'abonnés enregistrées");
				reponse = new Reponse<List<DetailMaison>>(1, messages, new ArrayList<>());
			}

		} catch (Exception e) {
			reponse = new Reponse<>(1, Static.getErreursForException(e), null);
		}
		return jsonMapper.writeValueAsString(reponse);

	}
	// supprimer une categorie
		@DeleteMapping("/detailMaison/{id}")
		public String supprimer(@PathVariable("id") Long id) throws JsonProcessingException {

			Reponse<Boolean> reponse = null;

			try {

				reponse = new Reponse<Boolean>(0, null, detailMaisonMetier.supprimer(id));

			} catch (RuntimeException e1) {
				reponse = new Reponse<>(3, Static.getErreursForException(e1), null);
			}

			return jsonMapper.writeValueAsString(reponse);
		}

		@GetMapping("/imageDetailMaison/{iDdetailMaison}")
		public String getImageByIdDetailMaison(@PathVariable("iDdetailMaison") long iDdetailTerrains)
				throws JsonProcessingException, InvalideImmobilierException{
			Reponse<List<ImageDetailMaison>> reponse;
			try {
				List<ImageDetailMaison> photos = imageDetailMaisonMetier.findImageByIdDetailMaisonn(iDdetailTerrains);
				if (!photos.isEmpty()) {
					reponse = new Reponse<List<ImageDetailMaison>>(0, null, photos);
				} else {
					List<String> messages = new ArrayList<>();
					messages.add("Pas de photos enregistrées");
					reponse = new Reponse<List<ImageDetailMaison>>(1, messages, new ArrayList<>());
				}

			} catch (Exception e) {
				reponse = new Reponse<List<ImageDetailMaison>>(1, Static.getErreursForException(e), new ArrayList<>());
			}
			return jsonMapper.writeValueAsString(reponse);

		}
		@GetMapping("/imageDetailByIdDetailMaison/{id}")
		public String getImageByDetailMaisonBy(@PathVariable Long id) throws JsonProcessingException {
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
