package ci.gstoreplus.dashboard.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
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

import ci.gstoreplus.dashboard.metier.catalogue.DocumentMetier;
import ci.gstoreplus.entity.catalogue.Document;
import ci.gstoreplus.exception.InvalideImmobilierException;
import ci.gstoreplus.models.Reponse;
import ci.gstoreplus.utilitaire.Static;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class DocumentController {
	@Autowired
	private DocumentMetier categorieMetier;
	@Autowired
	private ObjectMapper jsonMapper;

	// recuper Categorie par identifiant
	private Reponse<Document> getCategorieById(Long id) {
		Document categorie = null;

		try {
			categorie = categorieMetier.findById(id);
			if (categorie == null) {
				List<String> messages = new ArrayList<>();
				messages.add(String.format("Categorie n'existe pas", id));
				new Reponse<Document>(2, messages, null);

			}
		} catch (RuntimeException e) {
			new Reponse<Document>(1, Static.getErreursForException(e), null);
		}

		return new Reponse<Document>(0, null, categorie);
	}

	//////////////////////////////////////////////////////////////////////////////////////////////
	////////////////// enregistrer une categorie dans la base de donnee
	////////////////////////////////////////////////////////////////////////////////////////////// donnee////////////////////////////////
	
	@PostMapping("/document")
	public String creer(@RequestBody Document document) throws JsonProcessingException {
		Reponse<Document> reponse;
		try {

			Document c = categorieMetier.creer(document);
			List<String> messages = new ArrayList<>();
			messages.add(String.format("%s  à été créer avec succes", c.getId()));
			reponse = new Reponse<Document>(0, messages, c);

		} catch (InvalideImmobilierException e) {

			reponse = new Reponse<Document>(1, Static.getErreursForException(e), null);
		}
		return jsonMapper.writeValueAsString(reponse);
	}

	@PutMapping("/document")
	public String update(@RequestBody Document modif) throws JsonProcessingException {

		Reponse<Document> reponse = null;
		Reponse<Document> reponseCatsModif = null;
		// on recupere autre a modifier
		System.out.println("modif recupere1:" + modif);
		reponseCatsModif = getCategorieById(modif.getId());
		if (reponseCatsModif.getBody() != null) {
			try {
				System.out.println("modif recupere2:" + modif);
				Document categorie = categorieMetier.modifier(modif);
				List<String> messages = new ArrayList<>();
				messages.add(String.format("%s a modifier avec succes", categorie.getId()));
				reponse = new Reponse<Document>(0, messages, categorie);
			} catch (InvalideImmobilierException e) {

				reponse = new Reponse<Document>(1, Static.getErreursForException(e), null);
			}

		} else {
			List<String> messages = new ArrayList<>();
			messages.add(String.format("La categorie n'existe pas"));
			reponse = new Reponse<Document>(0, messages, null);
		}

		return jsonMapper.writeValueAsString(reponse);

	}

	// recherche les categories par id
	@GetMapping("/document/{id}")
	public String getCategoriesById(@PathVariable("id") Long id) throws JsonProcessingException {

		Reponse<Document> reponse;

		try {

			Document c = categorieMetier.findById(id);
			List<String> messages = new ArrayList<>();
			messages.add(String.format(" à été créer avec succes"));
			reponse = new Reponse<Document>(0, messages, c);

		} catch (Exception e) {

			reponse = new Reponse<Document>(1, Static.getErreursForException(e), null);
		}
		return jsonMapper.writeValueAsString(reponse);
	}

	// recherche les categories par libelle
	@GetMapping("/getdocumentByLibelle/{libelle}")
	public String getCategoriesByNom(@PathVariable("libelle") String libelle) throws JsonProcessingException {

		Reponse<Document> reponse;

		try {

			Document c = categorieMetier.findByLibelle(libelle).get();
			List<String> messages = new ArrayList<>();
			messages.add(String.format(" à été recuperer avec succes"));
			reponse = new Reponse<Document>(0, messages, c);

		} catch (Exception e) {

			reponse = new Reponse<Document>(1, Static.getErreursForException(e), null);
		}
		return jsonMapper.writeValueAsString(reponse);
	}

	// supprimer une categorie
	@DeleteMapping("/document/{id}")
	public String supprimer(@PathVariable("id") Long id) throws JsonProcessingException {

		Reponse<Boolean> reponse = null;

		try {

			reponse = new Reponse<Boolean>(0, null, categorieMetier.supprimer(id));

		} catch (RuntimeException e1) {
			reponse = new Reponse<>(3, Static.getErreursForException(e1), null);
		}

		return jsonMapper.writeValueAsString(reponse);
	}

	// get all departement
	@GetMapping("/document")
	public String findAll() throws JsonProcessingException {
		Reponse<List<Document>> reponse;
		try {
			List<Document> pers = categorieMetier.findAll();
			if (!pers.isEmpty()) {
				reponse = new Reponse<List<Document>>(0, null, pers);
			} else {
				List<String> messages = new ArrayList<>();
				messages.add("Pas de categorie enregistrés");
				reponse = new Reponse<List<Document>>(1, messages, new ArrayList<>());
			}

		} catch (Exception e) {
			reponse = new Reponse<>(1, Static.getErreursForException(e), null);
		}
		return jsonMapper.writeValueAsString(reponse);

	}
}
