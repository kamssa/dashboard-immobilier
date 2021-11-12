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
import ci.gstoreplus.entity.dashboard.shared.Personne;
import ci.gstoreplus.entity.dashboard.shared.Role;
import ci.gstoreplus.entity.dashboard.shared.RoleName;
import ci.gstoreplus.exception.InvalideImmobilierException;
import ci.gstoreplus.models.Reponse;
import ci.gstoreplus.utilitaire.Static;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class EmployeController {
	@Autowired
	AuthenticationManager authenticationManager;
    @Autowired
	PersonneMetier personneMetier;
    @Autowired
    EmployeMetier employeMetier;
	@Autowired
	IRoleMetier roleMetier;
    @Autowired
	private ObjectMapper jsonMapper;
	
 // recuper personne par identifiant
 	private Reponse<Personne> getPersonneById(Long id) {
 		Personne personne = null;

 		try {
 			personne = personneMetier.findById(id);
 			if (personne == null) {
 				List<String> messages = new ArrayList<>();
 				messages.add(String.format("Personne n'existe pas", id));
 				new Reponse<Personne>(2, messages, null);

 			}
 		} catch (RuntimeException e) {
 			new Reponse<Personne>(1, Static.getErreursForException(e), null);
 		}

 		return new Reponse<Personne>(0, null, personne);
 	}

    
	
	@PostMapping("/employe")
	@ResponseStatus(code = HttpStatus.CREATED)
	public String createEmploye(@Valid @RequestBody Personne signUpRequest) throws Exception {
		Reponse<Personne> reponse;
		System.out.println(signUpRequest);
		try {
			Role userRole = roleMetier.findByName(RoleName.ROLE_EMPLOYE).get();
			signUpRequest.setRoles(Collections.singleton(userRole));
			Personne d = personneMetier.creer(signUpRequest);
			List<String> messages = new ArrayList<>();
			messages.add(String.format("%s  à été créer avec succes", d.getId()));
			reponse = new Reponse<Personne>(0, messages, d);

		} catch (InvalideImmobilierException e) {

			reponse = new Reponse<Personne>(1, Static.getErreursForException(e), null);
		}
		return jsonMapper.writeValueAsString(reponse);
			

		
	}
	@PutMapping("/employe")
	public String update(@RequestBody Personne modif) throws JsonProcessingException {

		Reponse<Personne> reponse = null;
		Reponse<Personne> reponsePersModif = null;
		// on recupere autre a modifier
		System.out.println("modif recupere1:" + modif);
		reponsePersModif = getPersonneById(modif.getId());
		if (reponsePersModif.getBody() != null) {
			try {
				System.out.println("modif recupere2:" + modif);
				Personne personne = personneMetier.modifier(modif);
				List<String> messages = new ArrayList<>();
				messages.add(String.format("%s a modifier avec succes", personne.getId()));
				reponse = new Reponse<Personne>(0, messages, personne);
			} catch (InvalideImmobilierException e) {

				reponse = new Reponse<Personne>(1, Static.getErreursForException(e), null);
			}

		} else {
			List<String> messages = new ArrayList<>();
			messages.add(String.format("La personne n'existe pas"));
			reponse = new Reponse<Personne>(0, messages, null);
		}

		return jsonMapper.writeValueAsString(reponse);

	}

	// recherche les employes par id
	@GetMapping("/employe/{id}")
	public String getPersonnesById(@PathVariable("id") Long id) throws JsonProcessingException {

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

	// supprimer une categorie
	@DeleteMapping("/employe/{id}")
	public String supprimer(@PathVariable("id") Long id) throws JsonProcessingException {

		Reponse<Boolean> reponse = null;

		try {

			reponse = new Reponse<Boolean>(0, null, personneMetier.supprimer(id));

		} catch (RuntimeException e1) {
			reponse = new Reponse<>(3, Static.getErreursForException(e1), null);
		}

		return jsonMapper.writeValueAsString(reponse);
	}

	// get all employe
	@GetMapping("/employe")
	public String findAll() throws JsonProcessingException {
		Reponse<List<Personne>> reponse;
		try {
			List<Personne> pers = employeMetier.findAll();
			if (!pers.isEmpty()) {
				reponse = new Reponse<List<Personne>>(0, null, pers);
			} else {
				List<String> messages = new ArrayList<>();
				messages.add("Pas d'employes enregistrés");
				reponse = new Reponse<List<Personne>>(1, messages, new ArrayList<>());
			}

		} catch (Exception e) {
			reponse = new Reponse<>(1, Static.getErreursForException(e), null);
		}
		return jsonMapper.writeValueAsString(reponse);

	}
	@GetMapping("/addRoleToEmploye/{id}/{roleName}")
	public String addRoleToEmploye(@PathVariable("id") Long id,  @PathVariable("roleName")String roleName) throws Exception {
       

		Reponse<Personne> reponse = null;
		Personne empl = null;
		
			if (roleName.equals("ROLE_ADMIN")) {
				Role userRole = roleMetier.findByName(RoleName.ROLE_ADMIN).get();
				Personne signUpRequest = getPersonneById(id).getBody();
				signUpRequest.setRoles(Collections.singleton(userRole));
				empl = personneMetier.modifier(signUpRequest);

				List<String> messages = new ArrayList<>();
				messages.add(String.format("%s  a été créé avec succès", empl.getId()));
				reponse = new Reponse<Personne>(0, messages, empl);
				return jsonMapper.writeValueAsString(reponse);

			}else if (roleName.equals("ROLE_MANAGER")) {
				Role userRole = roleMetier.findByName(RoleName.ROLE_MANAGER).get();
				Personne signUpRequest = getPersonneById(id).getBody();
				signUpRequest.setRoles(Collections.singleton(userRole));
				empl = personneMetier.modifier(signUpRequest);

				List<String> messages = new ArrayList<>();
				messages.add(String.format("%s  a été créé avec succès", empl.getId()));
				reponse = new Reponse<Personne>(0, messages, empl);
				return jsonMapper.writeValueAsString(reponse);

			}else if (roleName.equals("ROLE_COMMERCIAL") ) {
				Role userRole = roleMetier.findByName(RoleName.ROLE_COMMERCIAL).get();
				Personne signUpRequest = personneMetier.findById(id);
				signUpRequest.setRoles(Collections.singleton(userRole));
				empl = personneMetier.modifier(signUpRequest);
				System.out.println("Voir le nom complet de la personne recuperée:" + empl.getNomComplet());

				List<String> messages = new ArrayList<>();
				messages.add(String.format("%s  a été créé avec succès", empl.getId()));
				reponse = new Reponse<Personne>(0, messages, empl);
				return jsonMapper.writeValueAsString(reponse);

			} else if (roleName.equals("ROLE_EXPLOITANT")) {
				Role userRole = roleMetier.findByName(RoleName.ROLE_EXPLOITANT).get();
				Personne signUpRequest = getPersonneById(id).getBody();
				signUpRequest.setRoles(Collections.singleton(userRole));
				empl = personneMetier.modifier(signUpRequest);

				List<String> messages = new ArrayList<>();
				messages.add(String.format("%s  a été créé avec succès", empl.getId()));
				reponse = new Reponse<Personne>(0, messages, empl);
				return jsonMapper.writeValueAsString(reponse);

			}
			
			else if (roleName.equals("ROLE_EMPLOYE")) {
				Role userRole = roleMetier.findByName(RoleName.ROLE_EMPLOYE).get();
				Personne signUpRequest = getPersonneById(id).getBody();
				signUpRequest.setRoles(Collections.singleton(userRole));
				empl = personneMetier.modifier(signUpRequest);

				List<String> messages = new ArrayList<>();
				messages.add(String.format("%s  a été créé avec succès", empl.getId()));
				reponse = new Reponse<Personne>(0, messages, empl);
				return jsonMapper.writeValueAsString(reponse);

			}else {
              jsonMapper.writeValueAsString(reponse);
	
			}
			return jsonMapper.writeValueAsString(reponse);
			
						
			}
}
