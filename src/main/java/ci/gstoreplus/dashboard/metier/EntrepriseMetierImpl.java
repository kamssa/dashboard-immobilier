package ci.gstoreplus.dashboard.metier;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ci.gstoreplus.dao.dashboard.personne.EntrepriseRepository;
import ci.gstoreplus.entity.dashboard.admin.Entreprise;
import ci.gstoreplus.exception.InvalideImmobilierException;

@Service
public class EntrepriseMetierImpl implements EntrepriseMetier{
	@Autowired
	private EntrepriseRepository entrepriseRepository;
	@Override
	public Entreprise creer(Entreprise entity) throws InvalideImmobilierException {
		// TODO Auto-generated method stub
		return entrepriseRepository.save(entity);
	}

	@Override
	public Entreprise modifier(Entreprise entity) throws InvalideImmobilierException {
		// TODO Auto-generated method stub
		return entrepriseRepository.save(entity);
	}

	@Override
	public List<Entreprise> findAll() {
		// TODO Auto-generated method stub
		return entrepriseRepository.findAll();
	}

	@Override
	public Entreprise findById(Long id) {
		// TODO Auto-generated method stub
		return entrepriseRepository.findById(id).get();
	}

	@Override
	public boolean supprimer(Long id) {
		entrepriseRepository.deleteById(id);
		return true;
	}

	@Override
	public boolean supprimer(List<Entreprise> entites) {
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
	public Optional<Entreprise> findByNom(String nom) {
		// TODO Auto-generated method stub
		return entrepriseRepository.findByNom(nom);
	}

}
