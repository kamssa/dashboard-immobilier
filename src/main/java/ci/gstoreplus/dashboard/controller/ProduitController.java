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

import ci.gstoreplus.dashboard.metier.catalogue.ProduitMetier;
import ci.gstoreplus.dashboard.metier.catalogue.ProduitAcheterMetier;
import ci.gstoreplus.entity.catalogue.Produit;
import ci.gstoreplus.entity.catalogue.TerrainAcheter;
import ci.gstoreplus.exception.InvalideImmobilierException;
import ci.gstoreplus.models.Reponse;
import ci.gstoreplus.utilitaire.Static;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ProduitController {
	@Autowired
	private ProduitMetier produitMetier;
	@Autowired
	private ObjectMapper jsonMapper;

// recuper Produit par identifiant
	private Reponse<Produit> getProduitById(Long id) {
		Produit produit = null;

		try {
			produit = produitMetier.findById(id);
			if (produit == null) {
				List<String> messages = new ArrayList<>();
				messages.add(String.format("produit n'existe pas", id));
				new Reponse<Produit>(2, messages, null);

			}
		} catch (RuntimeException e) {
			new Reponse<Produit>(1, Static.getErreursForException(e), null);
		}

		return new Reponse<Produit>(0, null, produit);
	}

//////////////////////////////////////////////////////////////////////////////////////////////
////////////////// enregistrer un produit  dans la base de donnee
////////////////////////////////////////////////////////////////////////////////////////////// donnee////////////////////////////////

	@PostMapping("/produit")
	public String creer(@RequestBody Produit produit) throws JsonProcessingException {
		Reponse<Produit> reponse;
		System.out.println(produit);
		try {

			Produit d = produitMetier.creer(produit);
			List<String> messages = new ArrayList<>();
			messages.add(String.format("%s  à été créer avec succes", d.getId()));
			reponse = new Reponse<Produit>(0, messages, d);

		} catch (InvalideImmobilierException e) {

			reponse = new Reponse<Produit>(1, Static.getErreursForException(e), null);
		}
		return jsonMapper.writeValueAsString(reponse);
	}
	@PutMapping("/produit")
	public String update(@RequestBody Produit  modif) throws JsonProcessingException {

		Reponse<Produit> reponse = null;
		Reponse<Produit> reponseTerrainAcheterModif = null;
		// on recupere autre a modifier
		System.out.println("modif recupere1:"+ modif);
		reponseTerrainAcheterModif = getProduitById(modif.getId());
		if (reponseTerrainAcheterModif.getBody() != null) {
			try {
				System.out.println("modif recupere2:"+ modif);
				Produit produit = produitMetier.modifier(modif);
				List<String> messages = new ArrayList<>();
				messages.add(String.format("%s a modifier avec succes", produit.getId()));
				reponse = new Reponse<Produit>(0, messages, produit);
			} catch (InvalideImmobilierException e) {

				reponse = new Reponse<Produit>(1, Static.getErreursForException(e), null);
			}

		} else {
			List<String> messages = new ArrayList<>();
			messages.add(String.format("Produit n'existe pas"));
			reponse = new Reponse<Produit>(0, messages, null);
		}

		return jsonMapper.writeValueAsString(reponse);

	}
	// recherche les Produit par id
		@GetMapping("/produit/{id}")
		public String getDemandesById(@PathVariable("id") Long id) throws JsonProcessingException {

			Reponse<Produit> reponse;

			try {

				Produit t = produitMetier.findById(id);
				List<String> messages = new ArrayList<>();
				messages.add(String.format(" à été créer avec succes"));
				reponse = new Reponse<Produit>(0, messages, t);

			} catch (Exception e) {

				reponse = new Reponse<Produit>(1, Static.getErreursForException(e), null);
			}
			return jsonMapper.writeValueAsString(reponse);
		}
		// supprimer un Produit
				@DeleteMapping("/produit/{id}")
				public String supprimer(@PathVariable("id") Long id) throws JsonProcessingException {

					Reponse<Boolean> reponse = null;

					try {

						reponse = new Reponse<Boolean>(0, null, produitMetier.supprimer(id));

					} catch (RuntimeException e1) {
						reponse = new Reponse<>(3, Static.getErreursForException(e1), null);
					}

					return jsonMapper.writeValueAsString(reponse);
				}

	// get all Produit
		@GetMapping("/produit")
		public String findAll() throws JsonProcessingException {
			Reponse<List<Produit>> reponse;
			try {
				List<Produit> produits = produitMetier.findAll();
				if (!produits.isEmpty()) {
					reponse = new Reponse<List<Produit>>(0, null, produits);
				} else {
					List<String> messages = new ArrayList<>();
					messages.add("Pas de Produit enregistrés");
					reponse = new Reponse<List<Produit>>(1, messages, new ArrayList<>());
				}

			} catch (Exception e) {
				reponse = new Reponse<>(1, Static.getErreursForException(e), null);
			}
			return jsonMapper.writeValueAsString(reponse);

		}
	
}
