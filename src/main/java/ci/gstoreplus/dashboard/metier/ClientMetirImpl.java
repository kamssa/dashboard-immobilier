package ci.gstoreplus.dashboard.metier;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ci.gstoreplus.dao.dashboard.catalogue.ClientRepository;
import ci.gstoreplus.dao.dashboard.personne.PersonneRepository;
import ci.gstoreplus.entity.client.Client;
import ci.gstoreplus.entity.dashboard.shared.Personne;
import ci.gstoreplus.exception.InvalideImmobilierException;

@Service
public class ClientMetirImpl implements ClientMetier {

@Autowired
private ClientRepository clientRepository;
@Autowired
private PersonneRepository personneRepository;
@Autowired
PasswordEncoder passwordEncoder;
@Override
public Personne creer(Personne entity) throws InvalideImmobilierException {
	// TODO Auto-generated method stub
	entity.setPassword(passwordEncoder.encode(entity.getPassword()));
	return clientRepository.save(entity);
}

@Override
public Personne modifier(Personne modif) throws InvalideImmobilierException {
	Optional<Personne> personne = clientRepository.findById(modif.getId());
	if (personne.isPresent()) {

		if (personne.get().getVersion() != modif.getVersion()) {
			throw new InvalideImmobilierException("cette personne a deja ete modifier");
		}

	} else
		throw new InvalideImmobilierException("modif est un objet null");

	modif.setPassword(passwordEncoder.encode(modif.getPassword()));
	return clientRepository.save(modif);
}

@Override
public List<Personne> findAll() {
	List<Personne> pers = null;
	List<Personne> personne = clientRepository.findAll();
    pers = personne.stream().filter(p -> p.getType().equals("CL")).collect(Collectors.toList());
    return pers;
}

@Override
public Personne findById(Long id) {
	// TODO Auto-generated method stub
	return clientRepository.findById(id).get();
}

@Override
public boolean supprimer(Long id) {
	clientRepository.deleteById(id);
	return true;
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
public boolean existsByEmail(String email) {
	// TODO Auto-generated method stub
	return false;
}

@Override
public Optional<Personne> findByEmail(String email) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public Personne modifPassword(Personne p) {
	Personne pers = personneRepository.findByEmail(p.getEmail()).get();
	pers.setPassword(passwordEncoder.encode(p.getPassword()));
	return personneRepository.save(pers);
	
}
	
}
