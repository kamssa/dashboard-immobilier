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
import ci.gstoreplus.dashboard.metier.VilleMetier;
import ci.gstoreplus.entity.catalogue.Ville;
import ci.gstoreplus.exception.InvalideImmobilierException;
import ci.gstoreplus.models.Reponse;
import ci.gstoreplus.utilitaire.Static;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class VilleControlleur {
	@Autowired
	private VilleMetier villeMetier;
	@Autowired
	private ObjectMapper jsonMapper;

	// recuper Ville par identifiant
	private Reponse<Ville> getVilleById(Long id) {
		Ville ville = null;

		try {
			ville = villeMetier.findById(id);
			if (ville == null) {
				List<String> messages = new ArrayList<>();
				messages.add(String.format("Ville n'existe pas", id));
				new Reponse<Ville>(2, messages, null);

			}
		} catch (RuntimeException e) {
			new Reponse<Ville>(1, Static.getErreursForException(e), null);
		}

		return new Reponse<Ville>(0, null, ville);
	}

	//////////////////////////////////////////////////////////////////////////////////////////////
	////////////////// enregistrer une ville dans la base de donnee
	////////////////////////////////////////////////////////////////////////////////////////////// donnee////////////////////////////////

	@PostMapping("/ville")
	public String creer(@RequestBody Ville ville) throws JsonProcessingException {
		Reponse<Ville> reponse;
		System.out.println(ville);
		try {

			Ville v = villeMetier.creer(ville);
			List<String> messages = new ArrayList<>();
			messages.add(String.format("%s  à été créer avec succes", v.getId()));
			reponse = new Reponse<Ville>(0, messages, v);

		} catch (InvalideImmobilierException e) {

			reponse = new Reponse<Ville>(1, Static.getErreursForException(e), null);
		}
		return jsonMapper.writeValueAsString(reponse);
	}

	@PutMapping("/ville")
	public String update(@RequestBody Ville modif) throws JsonProcessingException {

		Reponse<Ville> reponse = null;
		Reponse<Ville> reponseCatsModif = null;
		// on recupere autre a modifier
		System.out.println("modif recupere1:" + modif);
		reponseCatsModif = getVilleById(modif.getId());
		if (reponseCatsModif.getBody() != null) {
			try {
				System.out.println("modif recupere2:" + modif);
				Ville ville = villeMetier.modifier(modif);
				List<String> messages = new ArrayList<>();
				messages.add(String.format("%s a modifier avec succes", ville.getId()));
				reponse = new Reponse<Ville>(0, messages, ville);
			} catch (InvalideImmobilierException e) {

				reponse = new Reponse<Ville>(1, Static.getErreursForException(e), null);
			}

		} else {
			List<String> messages = new ArrayList<>();
			messages.add(String.format("La ville n'existe pas"));
			reponse = new Reponse<Ville>(0, messages, null);
		}

		return jsonMapper.writeValueAsString(reponse);

	}

	// recherche les Ville par id
	@GetMapping("/ville/{id}")
	public String getVilleByIdVille(@PathVariable("id") Long id) throws JsonProcessingException {

		Reponse<Ville> reponse;

		try {

			Ville v = villeMetier.findById(id);
			List<String> messages = new ArrayList<>();
			messages.add(String.format(" à été recupere avec succes"));
			reponse = new Reponse<Ville>(0, messages, v);

		} catch (Exception e) {

			reponse = new Reponse<Ville>(1, Static.getErreursForException(e), null);
		}
		return jsonMapper.writeValueAsString(reponse);
	}

	// recherche les Ville par libelle
	@GetMapping("/getVilleByLibelle/{libelle}")
	public String getVilleByLibelle(@PathVariable("libelle") String libelle) throws JsonProcessingException {

		Reponse<Ville> reponse;

		try {

			Ville v = villeMetier.findByLibelle(libelle).get();
			List<String> messages = new ArrayList<>();
			messages.add(String.format(" à été recuperer avec succes"));
			reponse = new Reponse<Ville>(0, messages, v);

		} catch (Exception e) {

			reponse = new Reponse<Ville>(1, Static.getErreursForException(e), null);
		}
		return jsonMapper.writeValueAsString(reponse);
	}

	// supprimer une ville
	@DeleteMapping("/ville/{id}")
	public String supprimer(@PathVariable("id") Long id) throws JsonProcessingException {

		Reponse<Boolean> reponse = null;

		try {

			reponse = new Reponse<Boolean>(0, null, villeMetier.supprimer(id));

		} catch (RuntimeException e1) {
			reponse = new Reponse<>(3, Static.getErreursForException(e1), null);
		}

		return jsonMapper.writeValueAsString(reponse);
	}

	// get all departement
	@GetMapping("/ville")
	public String findAll() throws JsonProcessingException {
		Reponse<List<Ville>> reponse;
		try {
			List<Ville> villes = villeMetier.findAll();
			if (!villes.isEmpty()) {
				reponse = new Reponse<List<Ville>>(0, null, villes);
			} else {
				List<String> messages = new ArrayList<>();
				messages.add("Pas de categorie enregistrés");
				reponse = new Reponse<List<Ville>>(1, messages, new ArrayList<>());
			}

		} catch (Exception e) {
			reponse = new Reponse<>(1, Static.getErreursForException(e), null);
		}
		return jsonMapper.writeValueAsString(reponse);

	}
}
