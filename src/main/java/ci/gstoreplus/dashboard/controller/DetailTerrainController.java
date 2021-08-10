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
public class DetailTerrainController {
	@Autowired
	private DetailTerrainMetier detailTerrainMetier;
	@Autowired
	private ImageDetailTerrainMetier imageDetailTerrainMetier;

	@Autowired
	private ObjectMapper jsonMapper;
	@Autowired
	CloudinaryService cloudinaryService;
	


// recuper categorie par identifiant
	private Reponse<DetailTerrain> getDetailTerrainById(Long id) {
		DetailTerrain detailTerrain = null;

		try {
			detailTerrain = detailTerrainMetier.findById(id);
			if (detailTerrain == null) {
				List<String> messages = new ArrayList<>();
				messages.add(String.format("Le detailTerrain n'existe pas", id));
				new Reponse<DetailTerrain>(2, messages, null);

			}
		} catch (RuntimeException e) {
			new Reponse<DetailTerrain>(1, Static.getErreursForException(e), null);
		}

		return new Reponse<DetailTerrain>(0, null, detailTerrain);
	}

//////////////////////////////////////////////////////////////////////////////////////////////
////////////////// enregistrer une categories  dans la base de donnee
////////////////////////////////////////////////////////////////////////////////////////////// donnee////////////////////////////////

	@PostMapping("/detailTerrain")
	public String creer(@RequestBody DetailTerrain detailTerrain) throws JsonProcessingException {
		Reponse<DetailTerrain> reponse;
		System.out.println(detailTerrain);
		try {

			DetailTerrain detailArticle = detailTerrainMetier.creer(detailTerrain);
			List<String> messages = new ArrayList<>();
			messages.add(String.format("%s  à été créer avec succes", detailArticle.getId()));
			reponse = new Reponse<DetailTerrain>(0, messages, detailArticle);

		} catch (InvalideImmobilierException e) {

			reponse = new Reponse<DetailTerrain>(1, Static.getErreursForException(e), null);
		}
		return jsonMapper.writeValueAsString(reponse);
	}

	@PutMapping("/detailTerrain")
	public String update(@RequestBody DetailTerrain modif) throws JsonProcessingException {

		Reponse<DetailTerrain> reponse = null;
		Reponse<DetailTerrain> reponsePersModif = null;
		// on recupere abonnement a modifier
		System.out.println("modif recupere1:" + modif);
		reponsePersModif = getDetailTerrainById(modif.getId());
		if (reponsePersModif.getBody() != null) {
			try {
				System.out.println("modif recupere2:" + modif);
				DetailTerrain detailArticles = detailTerrainMetier.modifier(modif);
				List<String> messages = new ArrayList<>();
				messages.add(String.format("%s a modifier avec succes", detailArticles.getId()));
				reponse = new Reponse<DetailTerrain>(0, messages, detailArticles);
			} catch (InvalideImmobilierException e) {

				reponse = new Reponse<DetailTerrain>(1, Static.getErreursForException(e), null);
			}

		} else {
			List<String> messages = new ArrayList<>();
			messages.add(String.format(" DetailTerrain n'existe pas"));
			reponse = new Reponse<DetailTerrain>(0, messages, null);
		}

		return jsonMapper.writeValueAsString(reponse);

	}

    ////////recuperer une categorie  par son id
	@GetMapping("/detailTerrain/{id}")
	public String getById(@PathVariable Long id) throws JsonProcessingException {
		// Annotation @PathVariable permet de recuperer le paremettre dans URI
		Reponse<DetailTerrain> reponse = null;

		reponse = getDetailTerrainById(id);
		if (reponse.getBody() == null) {
			throw new RuntimeException("pas d'enregistrement pour ce DetailTerrain");
		}

		return jsonMapper.writeValueAsString(reponse);

	}
	@GetMapping("/detailTerrainByIdTerrain/{id}")
	public String getByIdDetailTerrain(@PathVariable Long id) throws JsonProcessingException {
		Reponse<List<DetailTerrain>> reponse;
		try {
			List<DetailTerrain> detailTerrain = detailTerrainMetier.findDetailTerrainIdTerrain(id);
			
		reponse = new Reponse<List<DetailTerrain>>(0, null, detailTerrain);
			
     } catch (Exception e) {
			reponse = new Reponse<>(1, Static.getErreursForException(e), null);
		}
		return jsonMapper.writeValueAsString(reponse);
	}

        //get all categories
	@GetMapping("/detailTerrain")
	public String findAll() throws JsonProcessingException {
		Reponse<List<DetailTerrain>> reponse;
		try {
			List<DetailTerrain> detailTerrains = detailTerrainMetier.findAll();
			if (!detailTerrains.isEmpty()) {
				reponse = new Reponse<List<DetailTerrain>>(0, null, detailTerrains);
			} else {
				List<String> messages = new ArrayList<>();
				messages.add("Pas d'abonnés enregistrées");
				reponse = new Reponse<List<DetailTerrain>>(1, messages, new ArrayList<>());
			}

		} catch (Exception e) {
			reponse = new Reponse<>(1, Static.getErreursForException(e), null);
		}
		return jsonMapper.writeValueAsString(reponse);

	}
	// supprimer une categorie
		@DeleteMapping("/detailTerrain/{id}")
		public String supprimer(@PathVariable("id") Long id) throws JsonProcessingException {

			Reponse<Boolean> reponse = null;

			try {
               List<ImageDetail> imgd =  imageDetailTerrainMetier.findImageByIdDetailTerrain(id);
               imageDetailTerrainMetier.supprimer(imgd);
				reponse = new Reponse<Boolean>(0, null, detailTerrainMetier.supprimer(id));

			} catch (RuntimeException e1) {
				reponse = new Reponse<>(3, Static.getErreursForException(e1), null);
			}

			return jsonMapper.writeValueAsString(reponse);
		}

		/*@GetMapping("/image/{iDdetailTerrains}")
		public String getImageByIdDetailArticle(@PathVariable("iDdetailTerrains") long iDdetailTerrains)
				throws JsonProcessingException, InvalideImmobilierException{
			Reponse<List<ImageDetail>> reponse;
			try {
				List<ImageDetail> photos = imageDetailTerrainMetier.findImageByIdDetailTerrain(iDdetailTerrains);
				if (!photos.isEmpty()) {
					reponse = new Reponse<List<ImageDetail>>(0, null, photos);
				} else {
					List<String> messages = new ArrayList<>();
					messages.add("Pas de photos enregistrées");
					reponse = new Reponse<List<ImageDetail>>(1, messages, new ArrayList<>());
				}

			} catch (Exception e) {
				reponse = new Reponse<List<ImageDetail>>(1, Static.getErreursForException(e), new ArrayList<>());
			}
			return jsonMapper.writeValueAsString(reponse);

		}*/
		
}
