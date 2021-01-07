package ci.gstoreplus.dashboard.metier;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ci.gstoreplus.dao.dashboard.personne.PersonneRepository;
import ci.gstoreplus.dao.dashboard.personne.VerificationTokenRepository;
import ci.gstoreplus.entity.dashboard.shared.Personne;
import ci.gstoreplus.entity.dashboard.shared.VerificationToken;
import ci.gstoreplus.exception.InvalideImmobilierException;



@Service
public class PersonneMetierImpl implements PersonneMetier{
@Autowired
private PersonneRepository personneRepository;
@Autowired
PasswordEncoder passwordEncoder;
@Autowired
private VerificationTokenRepository tokenRepository;

public static final String TOKEN_INVALID = "INVALID";
public static final String TOKEN_EXPIRED = "EXPIRED";
public static final String TOKEN_VALID = "VALID";

	@Override
	public Personne creer(Personne p) throws InvalideImmobilierException {
		System.out.println("admin a enregistrer" + ":" + p);
		if ((p.getEmail().equals(null)) || (p.getEmail() == "")) {
			throw new InvalideImmobilierException("cet email ne peut etre null");
		}
		
		Optional<Personne> pers = null;

		pers = personneRepository.findByEmail(p.getEmail());
		if (pers.isPresent()) {
			throw new InvalideImmobilierException("Cet email est deja utilisé");
		}

		p.setPassword(passwordEncoder.encode(p.getPassword()));
		
		return personneRepository.save(p);
		
	}


	@Override
	public Personne modifier(Personne entity) throws InvalideImmobilierException {
		// TODO Auto-generated method stub
		return personneRepository.save(entity);
	}

	@Override
	public List<Personne> findAll() {
		return personneRepository.findAll();
	}

	@Override
	public Personne findById(Long id) {
		// TODO Auto-generated method stub
		return personneRepository.findById(id).get();
	}

	@Override
	public boolean supprimer(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean supprimer(List<Personne> entites) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean existe(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Boolean existsByPseudo(String pseudo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean existsByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}
	// creer une cle de verification pour un membre
		@Override
		public void createVerificationToken(Personne peronne, String token) {
			VerificationToken myToken = new VerificationToken(token, peronne);
			tokenRepository.save(myToken);

		}
		@Override
		public Personne getPersonne(String verificationToken) {
			Personne personne = tokenRepository.findByToken(verificationToken).getPersonne();
			if (personne.isActived() == true) {
				throw new RuntimeException("vous etes active");
			}
			return personne;
		}
		// verifier la date d'expiration de la cle du membre
		@Override
		public String validateVerificationToken(String token) {
			VerificationToken verificationToken = tokenRepository.findByToken(token);

			LocalDateTime dateActuelle = LocalDateTime.now();

			if (dateActuelle.isAfter(verificationToken.getExpiryDate())) {
				return TOKEN_EXPIRED;
			} else {

				Personne user = verificationToken.getPersonne();

				user.setActived(true);

				personneRepository.save(user);

			}
			return TOKEN_VALID;
		}


		@Override
		public Personne findByEmail(String email) {
			// TODO Auto-generated method stub
			return personneRepository.findByEmail(email).get();
		}

}
