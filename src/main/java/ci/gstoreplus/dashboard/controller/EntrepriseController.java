package ci.gstoreplus.dashboard.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ci.gstoreplus.dashboard.metier.EntrepriseMetier;
import ci.gstoreplus.entity.dashboard.admin.Departement;
import ci.gstoreplus.entity.dashboard.admin.Entreprise;
import ci.gstoreplus.exception.InvalideImmobilierException;
import ci.gstoreplus.models.Reponse;
import ci.gstoreplus.utilitaire.Static;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class EntrepriseController {
	@Autowired
	private EntrepriseMetier entrepriseMetier;
	@Autowired
	private ObjectMapper jsonMapper;

// recuper entreprise par identifiant
	private Reponse<Entreprise> getEntrepriseById(Long id) {
		Entreprise entreprise = null;

		try {
			entreprise = entrepriseMetier.findById(id);
			if (entreprise == null) {
				List<String> messages = new ArrayList<>();
				messages.add(String.format("Entreprise n'existe pas", id));
				new Reponse<Departement>(2, messages, null);

			}
		} catch (RuntimeException e) {
			new Reponse<Entreprise>(1, Static.getErreursForException(e), null);
		}

		return new Reponse<Entreprise>(0, null, entreprise);
	}

//////////////////////////////////////////////////////////////////////////////////////////////
////////////////// enregistrer un entreprise  dans la base de donnee
////////////////////////////////////////////////////////////////////////////////////////////// donnee////////////////////////////////

	@PostMapping("/entreprise")
	public String creer(@RequestBody Entreprise entreprise) throws JsonProcessingException {
		Reponse<Entreprise> reponse;
		System.out.println(entreprise);
		try {

			Entreprise d = entrepriseMetier.creer(entreprise);
			List<String> messages = new ArrayList<>();
			messages.add(String.format("%s  à été créer avec succes", d.getId()));
			reponse = new Reponse<Entreprise>(0, messages, d);

		} catch (InvalideImmobilierException e) {

			reponse = new Reponse<Entreprise>(1, Static.getErreursForException(e), null);
		}
		return jsonMapper.writeValueAsString(reponse);
	}
	
	// recherche les departements par id
		@GetMapping("/entreprise/{nom}")
		public String getdepatermentById(@PathVariable("nom") String nom) throws JsonProcessingException {

			Reponse<Entreprise> reponse;

			try {

				Entreprise p = entrepriseMetier.findByNom(nom).get();
				List<String> messages = new ArrayList<>();
				messages.add(String.format(" entreprise recuperer avec succes"));
				reponse = new Reponse<Entreprise>(0, messages, p);

			} catch (Exception e) {

				reponse = new Reponse<Entreprise>(1, Static.getErreursForException(e), null);
			}
			return jsonMapper.writeValueAsString(reponse);
		}
	
		// supprimer une entreprise
		@DeleteMapping("/entreprise/{id}")
		public String supprimer(@PathVariable("id") Long id) throws JsonProcessingException {

			Reponse<Boolean> reponse = null;

			try {

				reponse = new Reponse<Boolean>(0, null, entrepriseMetier.supprimer(id));

			} catch (RuntimeException e1) {
				reponse = new Reponse<>(3, Static.getErreursForException(e1), null);
			}

			return jsonMapper.writeValueAsString(reponse);
		}

		
}
