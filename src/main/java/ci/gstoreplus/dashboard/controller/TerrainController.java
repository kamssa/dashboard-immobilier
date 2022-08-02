package ci.gstoreplus.dashboard.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

import ci.gstoreplus.dashboard.metier.catalogue.TerrainMetier;
import ci.gstoreplus.entity.catalogue.Terrain;
import ci.gstoreplus.exception.InvalideImmobilierException;
import ci.gstoreplus.models.Reponse;
import ci.gstoreplus.utilitaire.Static;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class TerrainController {
	@Autowired
	private TerrainMetier terrainMetier;
	@Autowired
	private ObjectMapper jsonMapper;
	@Value("${dir.images}")
	private String storeImage;

	// recuper Categorie par identifiant
	private Reponse<Terrain> getTerrainById(Long id) {
		Terrain terrain = null;

		try {
			terrain = terrainMetier.findById(id);
			if (terrain == null) {
				List<String> messages = new ArrayList<>();
				messages.add(String.format("terrain n'existe pas", id));
				new Reponse<Terrain>(2, messages, null);

			}
		} catch (RuntimeException e) {
			new Reponse<Terrain>(1, Static.getErreursForException(e), null);
		}

		return new Reponse<Terrain>(0, null, terrain);
	}

	//////////////////////////////////////////////////////////////////////////////////////////////
	////////////////// enregistrer un terrain dans la base de donnee
	////////////////////////////////////////////////////////////////////////////////////////////// donnee////////////////////////////////

	@PostMapping("/terrain")
	public String creer(@RequestBody Terrain terrain) throws JsonProcessingException {
		Reponse<Terrain> reponse;

		try {

			Terrain t = terrainMetier.creer(terrain);
			List<String> messages = new ArrayList<>();
			messages.add(String.format("%s  à été créer avec succes", t.getId()));
			reponse = new Reponse<Terrain>(0, messages, t);

		} catch (InvalideImmobilierException e) {

			reponse = new Reponse<Terrain>(1, Static.getErreursForException(e), null);
		}
		return jsonMapper.writeValueAsString(reponse);
	}

	@PutMapping("/terrain")
	public String update(@RequestBody Terrain modif) throws JsonProcessingException {

		Reponse<Terrain> reponse = null;
		Reponse<Terrain> reponseTerrainModif = null;
		// on recupere autre a modifier
		System.out.println("modif recupere1:" + modif);
		reponseTerrainModif = getTerrainById(modif.getId());
		if (reponseTerrainModif.getBody() != null) {
			try {
				// modif.setPath(reponseTerrainModif.getBody().getPath());
				System.out.println("modif recupere2:" + modif);
				Terrain terrain = terrainMetier.modifier(modif);
				List<String> messages = new ArrayList<>();
				messages.add(String.format("%s a modifier avec succes", terrain.getId()));
				reponse = new Reponse<Terrain>(0, messages, terrain);
			} catch (InvalideImmobilierException e) {

				reponse = new Reponse<Terrain>(1, Static.getErreursForException(e), null);
			}

		} else {
			List<String> messages = new ArrayList<>();
			messages.add(String.format("Le Terrain n'existe pas"));
			reponse = new Reponse<Terrain>(0, messages, null);
		}

		return jsonMapper.writeValueAsString(reponse);

	}

	// recherche les terrains par id
	@GetMapping("/terrain/{id}")
	public String getTerrainsById(@PathVariable("id") Long id) throws JsonProcessingException {

		Reponse<Terrain> reponse;

		try {

			Terrain t = terrainMetier.findById(id);
			List<String> messages = new ArrayList<>();
			messages.add(String.format(" à été créer avec succes"));
			reponse = new Reponse<Terrain>(0, messages, t);

		} catch (Exception e) {

			reponse = new Reponse<Terrain>(1, Static.getErreursForException(e), null);
		}
		return jsonMapper.writeValueAsString(reponse);
	}

	// supprimer un terrain
	@DeleteMapping("/terrain/{id}")
	public String supprimer(@PathVariable("id") Long id) throws JsonProcessingException {

		Reponse<Boolean> reponse = null;

		try {

			reponse = new Reponse<Boolean>(0, null, terrainMetier.supprimer(id));

		} catch (RuntimeException e1) {
			reponse = new Reponse<>(3, Static.getErreursForException(e1), null);
		}

		return jsonMapper.writeValueAsString(reponse);
	}

	// get all Terrains
	@GetMapping("/terrain")
	public String findAll() throws JsonProcessingException {
		Reponse<List<Terrain>> reponse;
		try {
			List<Terrain> terrains = terrainMetier.findAll();
			if (!terrains.isEmpty()) {
				reponse = new Reponse<List<Terrain>>(0, null, terrains);
			} else {
				List<String> messages = new ArrayList<>();
				messages.add("Pas de terrain enregistrés");
				reponse = new Reponse<List<Terrain>>(1, messages, new ArrayList<>());
			}

		} catch (Exception e) {
			reponse = new Reponse<>(1, Static.getErreursForException(e), null);
		}
		return jsonMapper.writeValueAsString(reponse);

	}

	
}
