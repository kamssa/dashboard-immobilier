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

import ci.gstoreplus.dashboard.metier.DemandeMetier;
import ci.gstoreplus.dashboard.metier.catalogue.TerrainAcheterMetier;
import ci.gstoreplus.entity.catalogue.Demande;
import ci.gstoreplus.entity.catalogue.TerrainAcheter;
import ci.gstoreplus.exception.InvalideImmobilierException;
import ci.gstoreplus.models.Reponse;
import ci.gstoreplus.utilitaire.Static;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class TerrainAcheterController {
	@Autowired
	private TerrainAcheterMetier terrainAcheterMetier;
	@Autowired
	private ObjectMapper jsonMapper;

// recuper TerrainAcheter par identifiant
	private Reponse<TerrainAcheter> getTerrainAcheterById(Long id) {
		TerrainAcheter terrainAcheter = null;

		try {
			terrainAcheter = terrainAcheterMetier.findById(id);
			if (terrainAcheter == null) {
				List<String> messages = new ArrayList<>();
				messages.add(String.format("terrainAcheter n'existe pas", id));
				new Reponse<TerrainAcheter>(2, messages, null);

			}
		} catch (RuntimeException e) {
			new Reponse<TerrainAcheter>(1, Static.getErreursForException(e), null);
		}

		return new Reponse<TerrainAcheter>(0, null, terrainAcheter);
	}

//////////////////////////////////////////////////////////////////////////////////////////////
////////////////// enregistrer un TerrainAcheter  dans la base de donnee
////////////////////////////////////////////////////////////////////////////////////////////// donnee////////////////////////////////

	@PostMapping("/terrainAcheter")
	public String creer(@RequestBody TerrainAcheter terrainAcheter) throws JsonProcessingException {
		Reponse<TerrainAcheter> reponse;
		System.out.println(terrainAcheter);
		try {

			TerrainAcheter d = terrainAcheterMetier.creer(terrainAcheter);
			List<String> messages = new ArrayList<>();
			messages.add(String.format("%s  à été créer avec succes", d.getId()));
			reponse = new Reponse<TerrainAcheter>(0, messages, d);

		} catch (InvalideImmobilierException e) {

			reponse = new Reponse<TerrainAcheter>(1, Static.getErreursForException(e), null);
		}
		return jsonMapper.writeValueAsString(reponse);
	}
	@PutMapping("/terrainAcheter")
	public String update(@RequestBody TerrainAcheter  modif) throws JsonProcessingException {

		Reponse<TerrainAcheter> reponse = null;
		Reponse<TerrainAcheter> reponseTerrainAcheterModif = null;
		// on recupere autre a modifier
		System.out.println("modif recupere1:"+ modif);
		reponseTerrainAcheterModif = getTerrainAcheterById(modif.getId());
		if (reponseTerrainAcheterModif.getBody() != null) {
			try {
				System.out.println("modif recupere2:"+ modif);
				TerrainAcheter terrainAcheter = terrainAcheterMetier.modifier(modif);
				List<String> messages = new ArrayList<>();
				messages.add(String.format("%s a modifier avec succes", terrainAcheter.getId()));
				reponse = new Reponse<TerrainAcheter>(0, messages, terrainAcheter);
			} catch (InvalideImmobilierException e) {

				reponse = new Reponse<TerrainAcheter>(1, Static.getErreursForException(e), null);
			}

		} else {
			List<String> messages = new ArrayList<>();
			messages.add(String.format("TerrainAcheter n'existe pas"));
			reponse = new Reponse<TerrainAcheter>(0, messages, null);
		}

		return jsonMapper.writeValueAsString(reponse);

	}
	// recherche les TerrainAcheter par id
		@GetMapping("/terrainAcheter/{id}")
		public String getDemandesById(@PathVariable("id") Long id) throws JsonProcessingException {

			Reponse<TerrainAcheter> reponse;

			try {

				TerrainAcheter t = terrainAcheterMetier.findById(id);
				List<String> messages = new ArrayList<>();
				messages.add(String.format(" à été créer avec succes"));
				reponse = new Reponse<TerrainAcheter>(0, messages, t);

			} catch (Exception e) {

				reponse = new Reponse<TerrainAcheter>(1, Static.getErreursForException(e), null);
			}
			return jsonMapper.writeValueAsString(reponse);
		}
		// supprimer un TerrainAcheter
				@DeleteMapping("/terrainAcheter/{id}")
				public String supprimer(@PathVariable("id") Long id) throws JsonProcessingException {

					Reponse<Boolean> reponse = null;

					try {

						reponse = new Reponse<Boolean>(0, null, terrainAcheterMetier.supprimer(id));

					} catch (RuntimeException e1) {
						reponse = new Reponse<>(3, Static.getErreursForException(e1), null);
					}

					return jsonMapper.writeValueAsString(reponse);
				}

	// get all TerrainAcheter
		@GetMapping("/terrainAcheter")
		public String findAll() throws JsonProcessingException {
			Reponse<List<TerrainAcheter>> reponse;
			try {
				List<TerrainAcheter> terrainAcheters = terrainAcheterMetier.findAll();
				if (!terrainAcheters.isEmpty()) {
					reponse = new Reponse<List<TerrainAcheter>>(0, null, terrainAcheters);
				} else {
					List<String> messages = new ArrayList<>();
					messages.add("Pas de TerrainAcheter enregistrés");
					reponse = new Reponse<List<TerrainAcheter>>(1, messages, new ArrayList<>());
				}

			} catch (Exception e) {
				reponse = new Reponse<>(1, Static.getErreursForException(e), null);
			}
			return jsonMapper.writeValueAsString(reponse);

		}
}
