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

import ci.gstoreplus.dashboard.metier.catalogue.FlashTerrainMetier;
import ci.gstoreplus.dashboard.metier.catalogue.TerrainVenduMetier;
import ci.gstoreplus.entity.catalogue.FlashTerrain;
import ci.gstoreplus.entity.catalogue.Produit;
import ci.gstoreplus.exception.InvalideImmobilierException;
import ci.gstoreplus.models.Reponse;
import ci.gstoreplus.utilitaire.Static;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api")
@CrossOrigin
@AllArgsConstructor
public class FlashTerrainController {

	private FlashTerrainMetier flashTerrainMetier;
    private ObjectMapper jsonMapper;
    
    @PostMapping("/flashTerrain")
	public String creer(@RequestBody FlashTerrain flashTerrain) throws JsonProcessingException {
		Reponse<FlashTerrain> reponse;
		System.out.println(flashTerrain);
		try {

			FlashTerrain f = flashTerrainMetier.creer(flashTerrain);
			List<String> messages = new ArrayList<>();
			messages.add(String.format("%s  à été créer avec succes", f.getPrix()));
			reponse = new Reponse<FlashTerrain>(0, messages, f);

		} catch (InvalideImmobilierException e) {

			reponse = new Reponse<FlashTerrain>(1, Static.getErreursForException(e), null);
		}
		return jsonMapper.writeValueAsString(reponse);
	}

	// get all Produit
		@GetMapping("/flashTerrain")
		public String findAll() throws JsonProcessingException {
			Reponse<List<FlashTerrain>> reponse;
			try {
				List<FlashTerrain> produits = flashTerrainMetier.findAll();
				if (!produits.isEmpty()) {
					reponse = new Reponse<List<FlashTerrain>>(0, null, produits);
				} else {
					List<String> messages = new ArrayList<>();
					messages.add("Pas de Produit enregistrés");
					reponse = new Reponse<List<FlashTerrain>>(1, messages, new ArrayList<>());
				}

			} catch (Exception e) {
				reponse = new Reponse<>(1, Static.getErreursForException(e), null);
			}
			return jsonMapper.writeValueAsString(reponse);

		}
}
