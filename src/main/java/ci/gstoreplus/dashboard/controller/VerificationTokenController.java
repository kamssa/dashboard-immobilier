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

import ci.gstoreplus.dashboard.metier.VerificationTokenMetier;
import ci.gstoreplus.dashboard.metier.VilleMetier;
import ci.gstoreplus.entity.catalogue.Ville;
import ci.gstoreplus.entity.dashboard.shared.VerificationToken;
import ci.gstoreplus.exception.InvalideImmobilierException;
import ci.gstoreplus.models.Reponse;
import ci.gstoreplus.utilitaire.Static;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class VerificationTokenController {
	@Autowired
	private VerificationTokenMetier verificationTokenMetier;
	@Autowired
	private ObjectMapper jsonMapper;

	// recuper Ville par identifiant
	private Reponse<VerificationToken> getVerificationTokenById(Long id) {
		VerificationToken verificationToken = null;

		try {
			verificationToken = verificationTokenMetier.findById(id);
			if (verificationToken == null) {
				List<String> messages = new ArrayList<>();
				messages.add(String.format("VerificationToken n'existe pas", id));
				new Reponse<VerificationToken>(2, messages, null);

			}
		} catch (RuntimeException e) {
			new Reponse<VerificationToken>(1, Static.getErreursForException(e), null);
		}

		return new Reponse<VerificationToken>(0, null, verificationToken);
	}

	//////////////////////////////////////////////////////////////////////////////////////////////
	////////////////// enregistrer une verificationToken dans la base de donnee
	////////////////////////////////////////////////////////////////////////////////////////////// donnee////////////////////////////////

	@PostMapping("/verificationToken")
	public String creer(@RequestBody VerificationToken verificationToken) throws JsonProcessingException {
		Reponse<VerificationToken> reponse;
		System.out.println(verificationToken);
		try {

			VerificationToken v = verificationTokenMetier.creer(verificationToken);
			List<String> messages = new ArrayList<>();
			messages.add(String.format("%s  à été créer avec succes", v.getId()));
			reponse = new Reponse<VerificationToken>(0, messages, v);

		} catch (InvalideImmobilierException e) {

			reponse = new Reponse<VerificationToken>(1, Static.getErreursForException(e), null);
		}
		return jsonMapper.writeValueAsString(reponse);
	}

	@PutMapping("/verificationToken")
	public String update(@RequestBody VerificationToken modif) throws JsonProcessingException {

		Reponse<VerificationToken> reponse = null;
		Reponse<VerificationToken> reponseCatsModif = null;
		// on recupere autre a modifier
		System.out.println("modif recupere1:" + modif);
		reponseCatsModif = getVerificationTokenById(modif.getId());
		if (reponseCatsModif.getBody() != null) {
			try {
				System.out.println("modif recupere2:" + modif);
				VerificationToken v = verificationTokenMetier.modifier(modif);
				List<String> messages = new ArrayList<>();
				messages.add(String.format("%s a modifier avec succes", v.getId()));
				reponse = new Reponse<VerificationToken>(0, messages, v);
			} catch (InvalideImmobilierException e) {

				reponse = new Reponse<VerificationToken>(1, Static.getErreursForException(e), null);
			}

		} else {
			List<String> messages = new ArrayList<>();
			messages.add(String.format("La VerificationToken n'existe pas"));
			reponse = new Reponse<VerificationToken>(0, messages, null);
		}

		return jsonMapper.writeValueAsString(reponse);

	}

	// recherche les Ville par id
	@GetMapping("/verificationToken/{id}")
	public String getVilleByIdVille(@PathVariable("id") Long id) throws JsonProcessingException {

		Reponse<VerificationToken> reponse;

		try {

			VerificationToken v = verificationTokenMetier.findById(id);
			List<String> messages = new ArrayList<>();
			messages.add(String.format(" à été recupere avec succes"));
			reponse = new Reponse<VerificationToken>(0, messages, v);

		} catch (Exception e) {

			reponse = new Reponse<VerificationToken>(1, Static.getErreursForException(e), null);
		}
		return jsonMapper.writeValueAsString(reponse);
	}

	
	// supprimer une verificationToken
	@DeleteMapping("/verificationToken/{id}")
	public String supprimer(@PathVariable("id") Long id) throws JsonProcessingException {

		Reponse<Boolean> reponse = null;

		try {

			reponse = new Reponse<Boolean>(0, null, verificationTokenMetier.supprimer(id));

		} catch (RuntimeException e1) {
			reponse = new Reponse<>(3, Static.getErreursForException(e1), null);
		}

		return jsonMapper.writeValueAsString(reponse);
	}

	// get all departement
	@GetMapping("/verificationToken")
	public String findAll() throws JsonProcessingException {
		Reponse<List<VerificationToken>> reponse;
		try {
			List<VerificationToken> v = verificationTokenMetier.findAll();
			if (!v.isEmpty()) {
				reponse = new Reponse<List<VerificationToken>>(0, null, v);
			} else {
				List<String> messages = new ArrayList<>();
				messages.add("Pas de verificationToken enregistrés");
				reponse = new Reponse<List<VerificationToken>>(1, messages, new ArrayList<>());
			}

		} catch (Exception e) {
			reponse = new Reponse<>(1, Static.getErreursForException(e), null);
		}
		return jsonMapper.writeValueAsString(reponse);

	}
}
