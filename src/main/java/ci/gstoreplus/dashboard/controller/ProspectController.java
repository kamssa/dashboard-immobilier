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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ci.gstoreplus.dashboard.metier.IRoleMetier;
import ci.gstoreplus.dashboard.metier.PersonneMetier;
import ci.gstoreplus.dashboard.metier.ProspectMetier;
import ci.gstoreplus.entity.catalogue.Categorie;
import ci.gstoreplus.entity.catalogue.Demande;
import ci.gstoreplus.entity.client.Prospect;
import ci.gstoreplus.entity.dashboard.shared.Personne;
import ci.gstoreplus.entity.dashboard.shared.Role;
import ci.gstoreplus.entity.dashboard.shared.RoleName;
import ci.gstoreplus.exception.InvalideImmobilierException;
import ci.gstoreplus.listener.OnRegistrationCompleteEvent;
import ci.gstoreplus.models.ApiResponse;
import ci.gstoreplus.models.JwtAuthenticationResponse;
import ci.gstoreplus.models.Reponse;
import ci.gstoreplus.models.Reponses;
import ci.gstoreplus.security.JwtTokenProvider;
import ci.gstoreplus.utilitaire.Static;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class ProspectController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	PersonneMetier personneMetier;
	@Autowired
	private ProspectMetier prospectMetier;
	
	@Autowired
	IRoleMetier roleMetier;

	@Autowired
	JwtTokenProvider tokenProvider;
	@Autowired
	private ObjectMapper jsonMapper;
	@Autowired
	ApplicationEventPublisher eventPublisher;

	@PostMapping("/signinp")
	public String authenticateUser(@Valid @RequestBody Personne loginRequest) throws JsonProcessingException {
		Reponse<ResponseEntity<?>> reponse;
		Authentication authentication = authenticationManager.authenticate(

				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = tokenProvider.generateToken(authentication);
		reponse = new Reponse<ResponseEntity<?>>(0, null, ResponseEntity.ok(new JwtAuthenticationResponse(jwt)));
		return jsonMapper.writeValueAsString(reponse);

	}

	@PostMapping("/signupp")
	@ResponseStatus(code = HttpStatus.CREATED)
	public String registerUser(@Valid @RequestBody Personne signUpRequest,
			@RequestParam(value = "action") String action, BindingResult result1, HttpServletRequest request,
			Errors errors) throws Exception {

		Reponse<ResponseEntity<?>> reponse;
		Reponse<Personne> reponse1 = null;
		Reponses<ResponseEntity<?>, Personne> reponses;

		 if (result1.hasErrors()) {
			throw new RuntimeException("erreur");
		}

		Role userRole = roleMetier.findByName(RoleName.ROLE_CLIENT).get();
		signUpRequest.setRoles(Collections.singleton(userRole));

		Personne registered =  personneMetier.creer(signUpRequest);
		

		//////////////////////////////////////////////////
		if (registered == null) {
			result1.rejectValue("email", "message.regError");
		}
		URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/{username}")
				.buildAndExpand(registered.getEmail()).toUri();
		reponses = new Reponses<ResponseEntity<?>, Personne>(0, null,
				ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully")), registered);

		try {

			String appUrl = request.getContextPath();
			System.out.println(request.getContextPath());

			eventPublisher.publishEvent(new OnRegistrationCompleteEvent(registered, request.getLocale(), action));
			reponses = new Reponses<ResponseEntity<?>,Personne>(0, null,
					ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully")), registered);
			System.out.println(request.getContextPath());
			
		} catch (Exception e) {

			reponse = new Reponse<ResponseEntity<?>>(1, Static.getErreursForException(e), null);

		}

		return jsonMapper.writeValueAsString(reponses);
	}
	@PostMapping("/prospect")
	public String creer(@RequestBody Personne prospect) throws JsonProcessingException {
		Reponse<Personne> reponse;
		System.out.println(prospect);
		try {

			Personne d = prospectMetier.creer(prospect);
			List<String> messages = new ArrayList<>();
			messages.add(String.format("%s  à été créer avec succes", d.getId()));
			reponse = new Reponse<Personne>(0, messages, d);

		} catch (InvalideImmobilierException e) {

			reponse = new Reponse<Personne>(1, Static.getErreursForException(e), null);
		}
		return jsonMapper.writeValueAsString(reponse);
	}
	// get all demande
			@GetMapping("/prospect")
			public String findAll() throws JsonProcessingException {
				Reponse<List<Personne>> reponse;
				try {
					List<Personne> prospects = prospectMetier.findAll();
					if (!prospects.isEmpty()) {
						reponse = new Reponse<List<Personne>>(0, null, prospects);
					} else {
						List<String> messages = new ArrayList<>();
						messages.add("Pas de Prospect enregistrés");
						reponse = new Reponse<List<Personne>>(1, messages, new ArrayList<>());
					}

				} catch (Exception e) {
					reponse = new Reponse<>(1, Static.getErreursForException(e), null);
				}
				return jsonMapper.writeValueAsString(reponse);

			}
			// supprimer une demande
			@DeleteMapping("/prospect/{id}")
			public String supprimer(@PathVariable("id") Long id) throws JsonProcessingException {

				Reponse<Boolean> reponse = null;

				try {

					reponse = new Reponse<Boolean>(0, null, prospectMetier.supprimer(id));

				} catch (RuntimeException e1) {
					reponse = new Reponse<>(3, Static.getErreursForException(e1), null);
				}

				return jsonMapper.writeValueAsString(reponse);
			}
			// recherche le prospect par id
			@GetMapping("/prospect/{id}")
			public String getProspectById(@PathVariable("id") Long id) throws JsonProcessingException {

				Reponse<Personne> reponse;

				try {

					Personne c = prospectMetier.findById(id);
					List<String> messages = new ArrayList<>();
					messages.add(String.format(" à été créer avec succes"));
					reponse = new Reponse<Personne>(0, messages, c);

				} catch (Exception e) {

					reponse = new Reponse<Personne>(1, Static.getErreursForException(e), null);
				}
				return jsonMapper.writeValueAsString(reponse);
			}

			// recherche le membre par email
			@GetMapping("/getProspect/{email}")
			public String getProspectByEmail(@PathVariable("email") String email) throws JsonProcessingException {

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

	
}
