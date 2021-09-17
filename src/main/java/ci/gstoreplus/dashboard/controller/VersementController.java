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

import ci.gstoreplus.dashboard.metier.catalogue.VersementMetier;
import ci.gstoreplus.entity.catalogue.Versement;
import ci.gstoreplus.entity.catalogue.Ville;
import ci.gstoreplus.exception.InvalideImmobilierException;
import ci.gstoreplus.models.Reponse;
import ci.gstoreplus.utilitaire.Static;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class VersementController {
	@Autowired
	private VersementMetier versementMetier;
	
	@Autowired
	private ObjectMapper jsonMapper;
	
	// recuper Ville par identifiant
		private Reponse<Versement> getVersementById(Long id) {
			Versement versement = null;

			try {
				versement = versementMetier.findById(id);
				if (versement == null) {
					List<String> messages = new ArrayList<>();
					messages.add(String.format("Versement n'existe pas", id));
					new Reponse<Versement>(2, messages, null);

				}
			} catch (RuntimeException e) {
				new Reponse<Versement>(1, Static.getErreursForException(e), null);
			}

			return new Reponse<Versement>(0, null, versement);
		}

		//////////////////////////////////////////////////////////////////////////////////////////////
		////////////////// enregistrer un versement dans la base de donnee
		////////////////////////////////////////////////////////////////////////////////////////////// donnee////////////////////////////////

		@PostMapping("/versement")
		public String creer(@RequestBody Versement versement) throws JsonProcessingException {
			Reponse<Versement> reponse;
			System.out.println(versement);
			try {

				Versement v = versementMetier.creer(versement);
				List<String> messages = new ArrayList<>();
				messages.add(String.format("%s  à été créer avec succes", v.getId()));
				reponse = new Reponse<Versement>(0, messages, v);

			} catch (InvalideImmobilierException e) {

				reponse = new Reponse<Versement>(1, Static.getErreursForException(e), null);
			}
			return jsonMapper.writeValueAsString(reponse);
		}

		@PutMapping("/versement")
		public String update(@RequestBody Versement modif) throws JsonProcessingException {

			Reponse<Versement> reponse = null;
			Reponse<Versement> reponseCatsModif = null;
			// on recupere autre a modifier
			System.out.println("modif recupere1:" + modif);
			reponseCatsModif = getVersementById(modif.getId());
			if (reponseCatsModif.getBody() != null) {
				try {
					System.out.println("modif recupere2:" + modif);
					Versement v = versementMetier.modifier(modif);
					List<String> messages = new ArrayList<>();
					messages.add(String.format("%s a modifier avec succes", v.getId()));
					reponse = new Reponse<Versement>(0, messages, v);
				} catch (InvalideImmobilierException e) {

					reponse = new Reponse<Versement>(1, Static.getErreursForException(e), null);
				}

			} else {
				List<String> messages = new ArrayList<>();
				messages.add(String.format("Le versement n'existe pas"));
				reponse = new Reponse<Versement>(0, messages, null);
			}

			return jsonMapper.writeValueAsString(reponse);

		}

		// recherche les Ville par id
		@GetMapping("/versement/{id}")
		public String getVersementByIdV(@PathVariable("id") Long id) throws JsonProcessingException {

			Reponse<Versement> reponse;

			try {

				Versement v = versementMetier.findById(id);
				List<String> messages = new ArrayList<>();
				messages.add(String.format(" à été recupere avec succes"));
				reponse = new Reponse<Versement>(0, messages, v);

			} catch (Exception e) {

				reponse = new Reponse<Versement>(1, Static.getErreursForException(e), null);
			}
			return jsonMapper.writeValueAsString(reponse);
		}

		
		// supprimer une ville
		@DeleteMapping("/versement/{id}")
		public String supprimer(@PathVariable("id") Long id) throws JsonProcessingException {

			Reponse<Boolean> reponse = null;

			try {

				reponse = new Reponse<Boolean>(0, null, versementMetier.supprimer(id));

			} catch (RuntimeException e1) {
				reponse = new Reponse<>(3, Static.getErreursForException(e1), null);
			}

			return jsonMapper.writeValueAsString(reponse);
		}

		// get all departement
		@GetMapping("/versement")
		public String findAll() throws JsonProcessingException {
			Reponse<List<Versement>> reponse;
			try {
				List<Versement> vs = versementMetier.findAll();
				if (!vs.isEmpty()) {
					reponse = new Reponse<List<Versement>>(0, null, vs);
				} else {
					List<String> messages = new ArrayList<>();
					messages.add("Pas de categorie enregistrés");
					reponse = new Reponse<List<Versement>>(1, messages, new ArrayList<>());
				}

			} catch (Exception e) {
				reponse = new Reponse<>(1, Static.getErreursForException(e), null);
			}
			return jsonMapper.writeValueAsString(reponse);

		}
}
