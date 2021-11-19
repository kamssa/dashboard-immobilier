package ci.gstoreplus.dashboard.metier;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ci.gstoreplus.dao.dashboard.personne.ProspectRepository;
import ci.gstoreplus.entity.client.Prospect;
import ci.gstoreplus.entity.client.Prospects;
import ci.gstoreplus.entity.dashboard.shared.Personne;
import ci.gstoreplus.exception.InvalideImmobilierException;


@Service
public class ProspectMetierImpl implements ProspectMetier{
@Autowired
private ProspectRepository prospectRepository;

@Override
public Prospects creer(Prospects entity) throws InvalideImmobilierException {
	// TODO Auto-generated method stub
	return prospectRepository.save(entity);
}

@Override
public Prospects modifier(Prospects entity) throws InvalideImmobilierException {
	// TODO Auto-generated method stub
	return prospectRepository.save(entity);
}

@Override
public List<Prospects> findAll() {
	// TODO Auto-generated method stub
	return prospectRepository.findAll();
}

@Override
public Prospects findById(Long id) {
	// TODO Auto-generated method stub
	return prospectRepository.findById(id).get();
}

@Override
public boolean supprimer(Long id) {
	prospectRepository.deleteById(id);
	return true;
}

@Override
public boolean supprimer(List<Prospects> entites) {
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


}
