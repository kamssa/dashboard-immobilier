package ci.gstoreplus.dashboard.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ci.gstoreplus.dashboard.metier.PersonneMetier;
import ci.gstoreplus.entity.dashboard.admin.Employe;
import ci.gstoreplus.entity.dashboard.shared.Personne;
import ci.gstoreplus.models.Reponse;
import ci.gstoreplus.utilitaire.Static;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class PersonneController {
	@Autowired
	PersonneMetier personneMetier;
	@Autowired
	private ObjectMapper jsonMapper;
	// recherche les personne par id
		@GetMapping("/personne/{id}")
		public String getAdminById(@PathVariable("id") Long id) throws JsonProcessingException {

			Reponse<Personne> reponse;

			try {

				Personne p = personneMetier.findById(id);
				List<String> messages = new ArrayList<>();
				messages.add(String.format(" à été créer avec succes"));
				reponse = new Reponse<Personne>(0, messages, p);

			} catch (Exception e) {

				reponse = new Reponse<Personne>(1, Static.getErreursForException(e), null);
			}
			return jsonMapper.writeValueAsString(reponse);
		}
		// get all personne
		@GetMapping("/personne")
		public String findAll() throws JsonProcessingException {
			Reponse<List<Personne>> reponse;
			try {
				List<Personne> pers = personneMetier.findAll();
				if (!pers.isEmpty()) {
					reponse = new Reponse<List<Personne>>(0, null, pers);
				} else {
					List<String> messages = new ArrayList<>();
					messages.add("Pas de personne enregistrés");
					reponse = new Reponse<List<Personne>>(1, messages, new ArrayList<>());
				}

			} catch (Exception e) {
				reponse = new Reponse<>(1, Static.getErreursForException(e), null);
			}
			return jsonMapper.writeValueAsString(reponse);

		}
		
}
