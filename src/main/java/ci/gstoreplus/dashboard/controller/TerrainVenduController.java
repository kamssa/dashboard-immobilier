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

import ci.gstoreplus.dashboard.metier.catalogue.ProduitAcheterMetier;
import ci.gstoreplus.dashboard.metier.catalogue.TerrainVenduMetier;
import ci.gstoreplus.entity.catalogue.TerrainAcheter;
import ci.gstoreplus.entity.catalogue.TerrainVendu;
import ci.gstoreplus.exception.InvalideImmobilierException;
import ci.gstoreplus.models.Reponse;
import ci.gstoreplus.utilitaire.Static;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class TerrainVenduController {
@Autowired
private TerrainVenduMetier terrainVenduMetier;

@Autowired
private ObjectMapper jsonMapper;

//recuper TerrainAcheter par identifiant
private Reponse<TerrainVendu> getTerrainAcheterById(Long id) {
	TerrainVendu terrainAcheter = null;

	try {
		terrainAcheter = terrainVenduMetier.findById(id);
		if (terrainAcheter == null) {
			List<String> messages = new ArrayList<>();
			messages.add(String.format("terrainAcheter n'existe pas", id));
			new Reponse<TerrainVendu>(2, messages, null);

		}
	} catch (RuntimeException e) {
		new Reponse<TerrainVendu>(1, Static.getErreursForException(e), null);
	}

	return new Reponse<TerrainVendu>(0, null, terrainAcheter);
}

//////////////////////////////////////////////////////////////////////////////////////////////
//////////////////enregistrer un TerrainAcheter  dans la base de donnee
//////////////////////////////////////////////////////////////////////////////////////////////donnee////////////////////////////////

@PostMapping("/terrainVendu")
public String creer(@RequestBody TerrainVendu terrainAcheter) throws JsonProcessingException {
	Reponse<TerrainVendu> reponse;
	System.out.println(terrainAcheter);
	try {

		TerrainVendu d = terrainVenduMetier.creer(terrainAcheter);
		List<String> messages = new ArrayList<>();
		messages.add(String.format("%s  à été créer avec succes", d.getId()));
		reponse = new Reponse<TerrainVendu>(0, messages, d);

	} catch (InvalideImmobilierException e) {

		reponse = new Reponse<TerrainVendu>(1, Static.getErreursForException(e), null);
	}
	return jsonMapper.writeValueAsString(reponse);
}
@PutMapping("/terrainVendu")
public String update(@RequestBody TerrainVendu  modif) throws JsonProcessingException {

	Reponse<TerrainVendu> reponse = null;
	Reponse<TerrainVendu> reponseTerrainAcheterModif = null;
	// on recupere autre a modifier
	System.out.println("modif recupere1:"+ modif);
	reponseTerrainAcheterModif = getTerrainAcheterById(modif.getId());
	if (reponseTerrainAcheterModif.getBody() != null) {
		try {
			System.out.println("modif recupere2:"+ modif);
			TerrainVendu terrainAcheter = terrainVenduMetier.modifier(modif);
			List<String> messages = new ArrayList<>();
			messages.add(String.format("%s a modifier avec succes", terrainAcheter.getId()));
			reponse = new Reponse<TerrainVendu>(0, messages, terrainAcheter);
		} catch (InvalideImmobilierException e) {

			reponse = new Reponse<TerrainVendu>(1, Static.getErreursForException(e), null);
		}

	} else {
		List<String> messages = new ArrayList<>();
		messages.add(String.format("TerrainAcheter n'existe pas"));
		reponse = new Reponse<TerrainVendu>(0, messages, null);
	}

	return jsonMapper.writeValueAsString(reponse);

}
// recherche les TerrainAcheter par id
	@GetMapping("/terrainVendu/{id}")
	public String getDemandesById(@PathVariable("id") Long id) throws JsonProcessingException {

		Reponse<TerrainVendu> reponse;

		try {

			TerrainVendu t = terrainVenduMetier.findById(id);
			List<String> messages = new ArrayList<>();
			messages.add(String.format(" à été créer avec succes"));
			reponse = new Reponse<TerrainVendu>(0, messages, t);

		} catch (Exception e) {

			reponse = new Reponse<TerrainVendu>(1, Static.getErreursForException(e), null);
		}
		return jsonMapper.writeValueAsString(reponse);
	}
	// supprimer un TerrainAcheter
			@DeleteMapping("/terrainVendu/{id}")
			public String supprimer(@PathVariable("id") Long id) throws JsonProcessingException {

				Reponse<Boolean> reponse = null;

				try {

					reponse = new Reponse<Boolean>(0, null, terrainVenduMetier.supprimer(id));

				} catch (RuntimeException e1) {
					reponse = new Reponse<>(3, Static.getErreursForException(e1), null);
				}

				return jsonMapper.writeValueAsString(reponse);
			}

// get all TerrainAcheter
	@GetMapping("/terrainVendu")
	public String findAll() throws JsonProcessingException {
		Reponse<List<TerrainVendu>> reponse;
		try {
			List<TerrainVendu> terrainAcheters = terrainVenduMetier.findAll();
			if (!terrainAcheters.isEmpty()) {
				reponse = new Reponse<List<TerrainVendu>>(0, null, terrainAcheters);
			} else {
				List<String> messages = new ArrayList<>();
				messages.add("Pas de TerrainAcheter enregistrés");
				reponse = new Reponse<List<TerrainVendu>>(1, messages, new ArrayList<>());
			}

		} catch (Exception e) {
			reponse = new Reponse<>(1, Static.getErreursForException(e), null);
		}
		return jsonMapper.writeValueAsString(reponse);

	}
         //////////recupere les terrains achetés par id personne
			@GetMapping("/getTerrainVenduByIdPersonne/{id}")
			public String chercherParPersonne(@PathVariable Long id) throws JsonProcessingException {
				Reponse<List<TerrainVendu>> reponse = null;

				try {
					List<TerrainVendu> t = terrainVenduMetier.findTerrainVenduByIdPersonne(id);
					List<String> messages = new ArrayList<>();
					messages.add(String.format(" à été créer avec succes"));
					reponse = new Reponse<List<TerrainVendu>>(0, messages, t);

				} catch (Exception e) {

					reponse = new Reponse<List<TerrainVendu>>(1, Static.getErreursForException(e), null);
				}
				return jsonMapper.writeValueAsString(reponse);

			}
			// get all TerrainAcheter
			@GetMapping("/terrainVenduAbonneGeo")
			public String findAllAbonneGeo() throws JsonProcessingException {
				Reponse<List<TerrainVendu>> reponse;
				try {
					List<TerrainVendu> terrainAcheters = terrainVenduMetier.findAllTerrainAbonneGeo();
					if (!terrainAcheters.isEmpty()) {
						reponse = new Reponse<List<TerrainVendu>>(0, null, terrainAcheters);
					} else {
						List<String> messages = new ArrayList<>();
						messages.add("Pas de TerrainAcheter enregistrés");
						reponse = new Reponse<List<TerrainVendu>>(1, messages, new ArrayList<>());
					}

				} catch (Exception e) {
					reponse = new Reponse<>(1, Static.getErreursForException(e), null);
				}
				return jsonMapper.writeValueAsString(reponse);

			}
}
