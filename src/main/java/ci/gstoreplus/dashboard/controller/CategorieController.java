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

import ci.gstoreplus.dashboard.metier.catalogue.CategorieMetier;
import ci.gstoreplus.entity.catalogue.Categorie;
import ci.gstoreplus.exception.InvalideImmobilierException;
import ci.gstoreplus.models.Reponse;
import ci.gstoreplus.utilitaire.Static;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class CategorieController {
	@Autowired
	private CategorieMetier categorieMetier;
	@Autowired
	private ObjectMapper jsonMapper;

	// recuper Categorie par identifiant
	private Reponse<Categorie> getCategorieById(Long id) {
		Categorie categorie = null;

		try {
			categorie = categorieMetier.findById(id);
			if (categorie == null) {
				List<String> messages = new ArrayList<>();
				messages.add(String.format("Categorie n'existe pas", id));
				new Reponse<Categorie>(2, messages, null);

			}
		} catch (RuntimeException e) {
			new Reponse<Categorie>(1, Static.getErreursForException(e), null);
		}

		return new Reponse<Categorie>(0, null, categorie);
	}

	//////////////////////////////////////////////////////////////////////////////////////////////
	////////////////// enregistrer une categorie dans la base de donnee
	////////////////////////////////////////////////////////////////////////////////////////////// donnee////////////////////////////////
	
	@PostMapping("/categorie")
	public String creer(@RequestBody Categorie categorie) throws JsonProcessingException {
		Reponse<Categorie> reponse;
		try {

			Categorie c = categorieMetier.creer(categorie);
			List<String> messages = new ArrayList<>();
			messages.add(String.format("%s  à été créer avec succes", c.getId()));
			reponse = new Reponse<Categorie>(0, messages, c);

		} catch (InvalideImmobilierException e) {

			reponse = new Reponse<Categorie>(1, Static.getErreursForException(e), null);
		}
		return jsonMapper.writeValueAsString(reponse);
	}

	@PutMapping("/categorie")
	public String update(@RequestBody Categorie modif) throws JsonProcessingException {

		Reponse<Categorie> reponse = null;
		Reponse<Categorie> reponseCatsModif = null;
		// on recupere autre a modifier
		System.out.println("modif recupere1:" + modif);
		reponseCatsModif = getCategorieById(modif.getId());
		if (reponseCatsModif.getBody() != null) {
			try {
				System.out.println("modif recupere2:" + modif);
				Categorie categorie = categorieMetier.modifier(modif);
				List<String> messages = new ArrayList<>();
				messages.add(String.format("%s a modifier avec succes", categorie.getId()));
				reponse = new Reponse<Categorie>(0, messages, categorie);
			} catch (InvalideImmobilierException e) {

				reponse = new Reponse<Categorie>(1, Static.getErreursForException(e), null);
			}

		} else {
			List<String> messages = new ArrayList<>();
			messages.add(String.format("La categorie n'existe pas"));
			reponse = new Reponse<Categorie>(0, messages, null);
		}

		return jsonMapper.writeValueAsString(reponse);

	}

	// recherche les categories par id
	@GetMapping("/categorie/{id}")
	public String getCategoriesById(@PathVariable("id") Long id) throws JsonProcessingException {

		Reponse<Categorie> reponse;

		try {

			Categorie c = categorieMetier.findById(id);
			List<String> messages = new ArrayList<>();
			messages.add(String.format(" à été créer avec succes"));
			reponse = new Reponse<Categorie>(0, messages, c);

		} catch (Exception e) {

			reponse = new Reponse<Categorie>(1, Static.getErreursForException(e), null);
		}
		return jsonMapper.writeValueAsString(reponse);
	}

	// recherche les categories par libelle
	@GetMapping("/getCategorieByNom/{nom}")
	public String getCategoriesByNom(@PathVariable("nom") String nom) throws JsonProcessingException {

		Reponse<Categorie> reponse;

		try {

			Categorie c = categorieMetier.findByNom(nom).get();
			List<String> messages = new ArrayList<>();
			messages.add(String.format(" à été recuperer avec succes"));
			reponse = new Reponse<Categorie>(0, messages, c);

		} catch (Exception e) {

			reponse = new Reponse<Categorie>(1, Static.getErreursForException(e), null);
		}
		return jsonMapper.writeValueAsString(reponse);
	}

	// supprimer une categorie
	@DeleteMapping("/categorie/{id}")
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
	@GetMapping("/categorie")
	public String findAll() throws JsonProcessingException {
		Reponse<List<Categorie>> reponse;
		try {
			List<Categorie> pers = categorieMetier.findAll();
			if (!pers.isEmpty()) {
				reponse = new Reponse<List<Categorie>>(0, null, pers);
			} else {
				List<String> messages = new ArrayList<>();
				messages.add("Pas de categorie enregistrés");
				reponse = new Reponse<List<Categorie>>(1, messages, new ArrayList<>());
			}

		} catch (Exception e) {
			reponse = new Reponse<>(1, Static.getErreursForException(e), null);
		}
		return jsonMapper.writeValueAsString(reponse);

	}
}
