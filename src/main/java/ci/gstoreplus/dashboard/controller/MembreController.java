package ci.gstoreplus.dashboard.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ci.gstoreplus.dashboard.metier.EmployeMetier;
import ci.gstoreplus.dashboard.metier.IRoleMetier;
import ci.gstoreplus.dashboard.metier.MembreMetier;
import ci.gstoreplus.dashboard.metier.PersonneMetier;
import ci.gstoreplus.entity.dashboard.shared.Personne;
import ci.gstoreplus.entity.dashboard.shared.Role;
import ci.gstoreplus.entity.dashboard.shared.RoleName;
import ci.gstoreplus.exception.InvalideImmobilierException;
import ci.gstoreplus.listener.OnRegistrationCompleteEvent;
import ci.gstoreplus.models.ApiResponse;
import ci.gstoreplus.models.JwtAuthenticationResponse;
import ci.gstoreplus.models.Reponse;
import ci.gstoreplus.security.JwtTokenProvider;
import ci.gstoreplus.utilitaire.Static;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class MembreController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	PersonneMetier personneMetier;
	@Autowired
	private MembreMetier membreMetier;
	@Autowired
	IRoleMetier roleMetier;

	@Autowired
	JwtTokenProvider tokenProvider;
	@Autowired
	private ObjectMapper jsonMapper;
	@Autowired
	ApplicationEventPublisher eventPublisher;
	// recuper personne par identifiant
	 	private Reponse<Personne> getMembreById(Long id) {
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

	@PostMapping("auth/signinM")
	public String authenticateUser(@Valid @RequestBody Personne loginRequest) throws JsonProcessingException {
		Reponse<ResponseEntity<?>> reponse;
		Authentication authentication = authenticationManager.authenticate(

				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = tokenProvider.generateToken(authentication);
		reponse = new Reponse<ResponseEntity<?>>(0, null, ResponseEntity.ok(new JwtAuthenticationResponse(jwt)));
		return jsonMapper.writeValueAsString(reponse);

	}

	@PostMapping("auth/signupM")
	@ResponseStatus(code = HttpStatus.CREATED)
	public String registerUser(@Valid @RequestBody Personne signUpRequest,
			@RequestParam(value = "action") String action, BindingResult result1, HttpServletRequest request,
			Errors errors) throws Exception {

		Reponse<ResponseEntity<?>> reponse;
		Reponse<Personne> reponse1 = null;

		 if (result1.hasErrors()) {
			throw new RuntimeException("erreur");
		}

		Role userRole = roleMetier.findByName(RoleName.ROLE_MEMBRE).get();
		signUpRequest.setRoles(Collections.singleton(userRole));

		Personne registered =  personneMetier.creer(signUpRequest);
		//////////////////////////////////////////////////
		if (registered == null) {
			result1.rejectValue("email", "message.regError");
		}
		URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/{username}")
				.buildAndExpand(registered.getEmail()).toUri();
		reponse = new Reponse<ResponseEntity<?>>(0, null,
				ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully")));

		try {

			String appUrl = request.getContextPath();
			System.out.println(request.getContextPath());

			eventPublisher.publishEvent(new OnRegistrationCompleteEvent(registered, request.getLocale(), action));
			reponse = new Reponse<ResponseEntity<?>>(0, null,
					ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully")));
			System.out.println(request.getContextPath());
		} catch (Exception e) {

			reponse = new Reponse<ResponseEntity<?>>(1, Static.getErreursForException(e), null);

		}

		return jsonMapper.writeValueAsString(reponse);
	}
	@GetMapping("auth/registrationConfirmM")
	public String confirmRegistration(@RequestParam(value = "email") String email,
			@RequestParam(value = "code") String code) throws InvalideImmobilierException, JsonProcessingException {

		Reponse<Personne> reponse = null;

		Personne user = null;
		// on recupre le membre a patir de son token dans la base 
		user = personneMetier.getPersonne(code);
		if (user.getEmail().equals(email)) {

			if (user.isActived() == false) {

				final String result = personneMetier.validateVerificationToken(code);
				if (result.equals("VALID")) {
					// user = membreMetier.getMembre(token);

					List<String> messages = new ArrayList<>();
					messages.add(String.format("%s à été créer avec succes avec statut membres", user.getEmail()));
					reponse = new Reponse<Personne>(0, messages, user);

				} else {
					throw new RuntimeException("votre code a expire" + result);
				}
			} else {
				throw new RuntimeException("vous etes deja active");
			}
		} else {
			throw new RuntimeException("mauvais login");
		}

		return jsonMapper.writeValueAsString(reponse);
	}
	@PutMapping("/membre")
	public String update(@RequestBody Personne modif) throws JsonProcessingException {

		Reponse<Personne> reponse = null;
		Reponse<Personne> reponsePersModif = null;
		// on recupere autre a modifier
		System.out.println("modif recupere1:" + modif);
		reponsePersModif = getMembreById(modif.getId());
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

	// recherche le membre par id
	@GetMapping("/membre/{id}")
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
	// recherche le membre par id
		@GetMapping("/getMembre/{email}")
		public String getPersonnesByEmail(@PathVariable("email") String email) throws JsonProcessingException {

			Reponse<Personne> reponse;

			try {

				Personne p = personneMetier.findByEmail(email);
				List<String> messages = new ArrayList<>();
				messages.add(String.format(" à été créer avec succes"));
				reponse = new Reponse<Personne>(0, messages, p);

			} catch (Exception e) {

				reponse = new Reponse<Personne>(1, Static.getErreursForException(e), null);
			}
			return jsonMapper.writeValueAsString(reponse);
		}

	// supprimer une categorie
	@DeleteMapping("/membre/{id}")
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
	@GetMapping("/membre")
	public String findAll() throws JsonProcessingException {
		Reponse<List<Personne>> reponse;
		try {
			List<Personne> pers = membreMetier.findAll();
			if (!pers.isEmpty()) {
				reponse = new Reponse<List<Personne>>(0, null, pers);
			} else {
				List<String> messages = new ArrayList<>();
				messages.add("Pas de membre enregistrés");
				reponse = new Reponse<List<Personne>>(1, messages, new ArrayList<>());
			}

		} catch (Exception e) {
			reponse = new Reponse<>(1, Static.getErreursForException(e), null);
		}
		return jsonMapper.writeValueAsString(reponse);

	}
}
