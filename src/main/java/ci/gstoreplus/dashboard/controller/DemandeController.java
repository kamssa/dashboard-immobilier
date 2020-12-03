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
import ci.gstoreplus.dashboard.metier.IDepartementMetier;
import ci.gstoreplus.entity.catalogue.Demande;
import ci.gstoreplus.entity.dashboard.admin.Departement;
import ci.gstoreplus.exception.InvalideImmobilierException;
import ci.gstoreplus.models.Reponse;
import ci.gstoreplus.utilitaire.Static;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class DemandeController {
	@Autowired
	private DemandeMetier demandeMetier;
	@Autowired
	private ObjectMapper jsonMapper;

// recuper Demande par identifiant
	private Reponse<Demande> getDemandeById(Long id) {
		Demande demande = null;

		try {
			demande = demandeMetier.findById(id);
			if (demande == null) {
				List<String> messages = new ArrayList<>();
				messages.add(String.format("demande n'existe pas", id));
				new Reponse<Demande>(2, messages, null);

			}
		} catch (RuntimeException e) {
			new Reponse<Demande>(1, Static.getErreursForException(e), null);
		}

		return new Reponse<Demande>(0, null, demande);
	}

//////////////////////////////////////////////////////////////////////////////////////////////
////////////////// enregistrer une demande  dans la base de donnee
////////////////////////////////////////////////////////////////////////////////////////////// donnee////////////////////////////////

	@PostMapping("/demande")
	public String creer(@RequestBody Demande demande) throws JsonProcessingException {
		Reponse<Demande> reponse;
		System.out.println(demande);
		try {

			Demande d = demandeMetier.creer(demande);
			List<String> messages = new ArrayList<>();
			messages.add(String.format("%s  à été créer avec succes", d.getId()));
			reponse = new Reponse<Demande>(0, messages, d);

		} catch (InvalideImmobilierException e) {

			reponse = new Reponse<Demande>(1, Static.getErreursForException(e), null);
		}
		return jsonMapper.writeValueAsString(reponse);
	}
	@PutMapping("/demande")
	public String update(@RequestBody Demande  modif) throws JsonProcessingException {

		Reponse<Demande> reponse = null;
		Reponse<Demande> reponseDemandeModif = null;
		// on recupere autre a modifier
		System.out.println("modif recupere1:"+ modif);
		reponseDemandeModif = getDemandeById(modif.getId());
		if (reponseDemandeModif.getBody() != null) {
			try {
				System.out.println("modif recupere2:"+ modif);
				Demande demande = demandeMetier.modifier(modif);
				List<String> messages = new ArrayList<>();
				messages.add(String.format("%s a modifier avec succes", demande.getId()));
				reponse = new Reponse<Demande>(0, messages, demande);
			} catch (InvalideImmobilierException e) {

				reponse = new Reponse<Demande>(1, Static.getErreursForException(e), null);
			}

		} else {
			List<String> messages = new ArrayList<>();
			messages.add(String.format("Demande n'existe pas"));
			reponse = new Reponse<Demande>(0, messages, null);
		}

		return jsonMapper.writeValueAsString(reponse);

	}
	// recherche les Demandes par id
		@GetMapping("/demande/{id}")
		public String getDemandesById(@PathVariable("id") Long id) throws JsonProcessingException {

			Reponse<Demande> reponse;

			try {

				Demande d = demandeMetier.findById(id);
				List<String> messages = new ArrayList<>();
				messages.add(String.format(" à été créer avec succes"));
				reponse = new Reponse<Demande>(0, messages, d);

			} catch (Exception e) {

				reponse = new Reponse<Demande>(1, Static.getErreursForException(e), null);
			}
			return jsonMapper.writeValueAsString(reponse);
		}
		// supprimer une demande
				@DeleteMapping("/demande/{id}")
				public String supprimer(@PathVariable("id") Long id) throws JsonProcessingException {

					Reponse<Boolean> reponse = null;

					try {

						reponse = new Reponse<Boolean>(0, null, demandeMetier.supprimer(id));

					} catch (RuntimeException e1) {
						reponse = new Reponse<>(3, Static.getErreursForException(e1), null);
					}

					return jsonMapper.writeValueAsString(reponse);
				}

	// get all demande
		@GetMapping("/demande")
		public String findAll() throws JsonProcessingException {
			Reponse<List<Demande>> reponse;
			try {
				List<Demande> demandes = demandeMetier.findAll();
				if (!demandes.isEmpty()) {
					reponse = new Reponse<List<Demande>>(0, null, demandes);
				} else {
					List<String> messages = new ArrayList<>();
					messages.add("Pas de demande enregistrés");
					reponse = new Reponse<List<Demande>>(1, messages, new ArrayList<>());
				}

			} catch (Exception e) {
				reponse = new Reponse<>(1, Static.getErreursForException(e), null);
			}
			return jsonMapper.writeValueAsString(reponse);

		}
		
}
