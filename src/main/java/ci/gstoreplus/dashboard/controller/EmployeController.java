package ci.gstoreplus.dashboard.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ci.gstoreplus.dashboard.metier.EmployeMetier;
import ci.gstoreplus.dashboard.metier.IRoleMetier;
import ci.gstoreplus.dashboard.metier.PersonneMetier;
import ci.gstoreplus.entity.dashboard.admin.Employe;
import ci.gstoreplus.entity.dashboard.shared.Personne;
import ci.gstoreplus.entity.dashboard.shared.Role;
import ci.gstoreplus.entity.dashboard.shared.RoleName;
import ci.gstoreplus.exception.InvalideImmobilierException;
import ci.gstoreplus.models.Reponse;
import ci.gstoreplus.utilitaire.Static;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class EmployeController {
	@Autowired
	AuthenticationManager authenticationManager;
   
    @Autowired
    EmployeMetier employeMetier;
    @Autowired
    PersonneMetier personneMetier;
	@Autowired
	IRoleMetier roleMetier;
    @Autowired
	private ObjectMapper jsonMapper;
	
 // recuper personne par identifiant
 	private Reponse<Employe> getEmployeById(Long id) {
 		Employe personne = null;

 		try {
 			personne = employeMetier.findById(id);
 			if (personne == null) {
 				List<String> messages = new ArrayList<>();
 				messages.add(String.format("Personne n'existe pas", id));
 				new Reponse<Employe>(2, messages, null);

 			}
 		} catch (RuntimeException e) {
 			new Reponse<Employe>(1, Static.getErreursForException(e), null);
 		}

 		return new Reponse<Employe>(0, null, personne);
 	}

    
	
	@PostMapping("/employe")
	@ResponseStatus(code = HttpStatus.CREATED)
	public String createEmploye(@Valid @RequestBody Employe signUpRequest) throws Exception {
		Reponse<Employe> reponse;
		System.out.println(signUpRequest);
		try {
			Role userRole = roleMetier.findByName(RoleName.ROLE_EMPLOYE).get();
			signUpRequest.setRoles(Collections.singleton(userRole));
			Employe d = employeMetier.creer(signUpRequest);
			List<String> messages = new ArrayList<>();
			messages.add(String.format("%s  à été créer avec succes", d.getId()));
			reponse = new Reponse<Employe>(0, messages, d);

		} catch (InvalideImmobilierException e) {

			reponse = new Reponse<Employe>(1, Static.getErreursForException(e), null);
		}
		return jsonMapper.writeValueAsString(reponse);
			

		
	}
	@PutMapping("/employe")
	public String update(@RequestBody Employe modif) throws JsonProcessingException {

		Reponse<Employe> reponse = null;
		Reponse<Employe> reponsePersModif = null;
		// on recupere autre a modifier
		System.out.println("modif recupere1:" + modif);
		reponsePersModif = getEmployeById(modif.getId());
		if (reponsePersModif.getBody() != null) {
			try {
				System.out.println("modif recupere2:" + modif);
				Employe personne = employeMetier.modifier(modif);
				List<String> messages = new ArrayList<>();
				messages.add(String.format("%s a modifier avec succes", personne.getId()));
				reponse = new Reponse<Employe>(0, messages, personne);
			} catch (InvalideImmobilierException e) {

				reponse = new Reponse<Employe>(1, Static.getErreursForException(e), null);
			}

		} else {
			List<String> messages = new ArrayList<>();
			messages.add(String.format("La personne n'existe pas"));
			reponse = new Reponse<Employe>(0, messages, null);
		}

		return jsonMapper.writeValueAsString(reponse);

	}

	// recherche les employes par id
	@GetMapping("/employe/{id}")
	public String getPersonnesById(@PathVariable("id") Long id) throws JsonProcessingException {

		Reponse<Employe> reponse;

		try {

			Employe p = employeMetier.findById(id);
			List<String> messages = new ArrayList<>();
			messages.add(String.format("Employé retouvé avec succes"));
			reponse = new Reponse<Employe>(0, messages, p);

		} catch (Exception e) {

			reponse = new Reponse<Employe>(1, Static.getErreursForException(e), null);
		}
		return jsonMapper.writeValueAsString(reponse);
	}

	// supprimer une categorie
	@DeleteMapping("/employe/{id}")
	public String supprimer(@PathVariable("id") Long id) throws JsonProcessingException {

		Reponse<Boolean> reponse = null;

		try {

			reponse = new Reponse<Boolean>(0, null, employeMetier.supprimer(id));

		} catch (RuntimeException e1) {
			reponse = new Reponse<>(3, Static.getErreursForException(e1), null);
		}

		return jsonMapper.writeValueAsString(reponse);
	}

	// get all employe
	@GetMapping("/employe")
	public String findAll() throws JsonProcessingException {
		Reponse<List<Employe>> reponse;
		try {
			List<Employe> pers = employeMetier.findAll();
			if (!pers.isEmpty()) {
				reponse = new Reponse<List<Employe>>(0, null, pers);
			} else {
				List<String> messages = new ArrayList<>();
				messages.add("Pas d'employes enregistrés");
				reponse = new Reponse<List<Employe>>(1, messages, new ArrayList<>());
			}

		} catch (Exception e) {
			reponse = new Reponse<>(1, Static.getErreursForException(e), null);
		}
		return jsonMapper.writeValueAsString(reponse);

	}
	// employe par id departement
	@GetMapping("/employeByDepartement/{id}")
	public String getEmployeByIdDepartement(@PathVariable Long id) throws JsonProcessingException {
		Reponse<List<Employe>> reponse;
		try {
			List<Employe> detailTerrain = employeMetier.findEmployeByDepartement(id);
			
		reponse = new Reponse<List<Employe>>(0, null, detailTerrain);
			
     } catch (Exception e) {
			reponse = new Reponse<>(1, Static.getErreursForException(e), null);
		}
		return jsonMapper.writeValueAsString(reponse);
	}
	// recherche les employes par id
			@GetMapping("/getPersonneByEmail/{email}")
			public String getPersonnesByEmail(@PathVariable("email") String email) throws JsonProcessingException {

				Reponse<Personne> reponse;

				try {

					Personne p = personneMetier.findByEmail(email);
					List<String> messages = new ArrayList<>();
					messages.add(String.format("personne retouvé avec succes"));
					reponse = new Reponse<Personne>(0, messages, p);

				} catch (Exception e) {

					reponse = new Reponse<Personne>(1, Static.getErreursForException(e), null);
				}
				return jsonMapper.writeValueAsString(reponse);
			}
		
	
}
