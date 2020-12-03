package ci.gstoreplus.dashboard.metier;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ci.gstoreplus.dao.dashboard.catalogue.ClientRepository;
import ci.gstoreplus.entity.client.Client;
import ci.gstoreplus.entity.dashboard.shared.Personne;
import ci.gstoreplus.exception.InvalideImmobilierException;

@Service
public class ClientMetirImpl implements ClientMetier {

@Autowired
private ClientRepository clientRepository;

@Override
public Personne creer(Personne entity) throws InvalideImmobilierException {
	// TODO Auto-generated method stub
	return null;
}

@Override
public Personne modifier(Personne entity) throws InvalideImmobilierException {
	// TODO Auto-generated method stub
	return null;
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
	return null;
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
	
}
