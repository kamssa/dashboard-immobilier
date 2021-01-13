package ci.gstoreplus.dashboard.metier;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ci.gstoreplus.dao.dashboard.catalogue.DemandeRepository;
import ci.gstoreplus.entity.catalogue.Demande;
import ci.gstoreplus.entity.dashboard.shared.Personne;
import ci.gstoreplus.exception.InvalideImmobilierException;

@Service
public class DemandeMetierImp implements DemandeMetier{
@Autowired
private DemandeRepository demandeRepository;
	@Override
	public Demande creer(Demande entity) throws InvalideImmobilierException {
		return demandeRepository.save(entity);
	}

	@Override
	public Demande modifier(Demande entity) throws InvalideImmobilierException {
		return demandeRepository.save(entity);
	}

	@Override
	public List<Demande> findAll() {
		return demandeRepository.findAll();
	}

	@Override
	public Demande findById(Long id) {
		return demandeRepository.findById(id).get();
	}

	@Override
	public boolean supprimer(Long id) {
		demandeRepository.deleteById(id);
		return true;
	}

	@Override
	public boolean supprimer(List<Demande> entites) {
		return false;
	}

	@Override
	public boolean existe(Long id) {
		return false;
	}

	@Override
	public Boolean existsByPseudo(String pseudo) {
		return null;
	}

	@Override
	public Boolean existsByEmail(String email) {
		return null;
	}

	@Override
	public List<Demande> demandeParIdPersonne(Long id) {
		List<Demande> demande = null;
		List<Demande> demandes = demandeRepository.findAll();
		demande = demandes.stream().filter(p -> p.getPersonne().getId().equals(id)).collect(Collectors.toList());
        return demande;	
        
	}

}
