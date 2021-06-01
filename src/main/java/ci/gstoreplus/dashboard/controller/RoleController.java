package ci.gstoreplus.dashboard.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ci.gstoreplus.dashboard.metier.IRoleMetier;
import ci.gstoreplus.entity.dashboard.shared.Role;
import ci.gstoreplus.exception.InvalideImmobilierException;
import ci.gstoreplus.models.Reponse;
import ci.gstoreplus.utilitaire.Static;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class RoleController {
	@Autowired
	private IRoleMetier roleMetier;
	@Autowired
	private ObjectMapper jsonMapper;

// recuper Demande par identifiant
	private Reponse<Role> getRoleById(Long id) {
		Role role = null;

		try {
			role = roleMetier.findById(id);
			if (role == null) {
				List<String> messages = new ArrayList<>();
				messages.add(String.format("role n'existe pas", id));
				new Reponse<Role>(2, messages, null);

			}
		} catch (RuntimeException e) {
			new Reponse<Role>(1, Static.getErreursForException(e), null);
		}

		return new Reponse<Role>(0, null, role);
	}

//////////////////////////////////////////////////////////////////////////////////////////////
////////////////// enregistrer un role  dans la base de donnee
////////////////////////////////////////////////////////////////////////////////////////////// donnee////////////////////////////////

	@PostMapping("/role")
	public String creer(@RequestBody Role role) throws JsonProcessingException {
		Reponse<Role> reponse;
		System.out.println(role);
		try {

			Role d = roleMetier.creer(role);
			List<String> messages = new ArrayList<>();
			messages.add(String.format("%s  à été créer avec succes", d.getId()));
			reponse = new Reponse<Role>(0, messages, d);

		} catch (InvalideImmobilierException e) {

			reponse = new Reponse<Role>(1, Static.getErreursForException(e), null);
		}
		return jsonMapper.writeValueAsString(reponse);
	}
	// get all demande
			@GetMapping("/role")
			public String findAll() throws JsonProcessingException {
				Reponse<List<Role>> reponse;
				try {
					List<Role> demandes = roleMetier.findAll();
					if (!demandes.isEmpty()) {
						reponse = new Reponse<List<Role>>(0, null, demandes);
					} else {
						List<String> messages = new ArrayList<>();
						messages.add("Pas de demande enregistrés");
						reponse = new Reponse<List<Role>>(1, messages, new ArrayList<>());
					}

				} catch (Exception e) {
					reponse = new Reponse<>(1, Static.getErreursForException(e), null);
				}
				return jsonMapper.writeValueAsString(reponse);

			}

			
}
