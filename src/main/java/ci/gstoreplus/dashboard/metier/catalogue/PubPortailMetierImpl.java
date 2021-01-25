package ci.gstoreplus.dashboard.metier.catalogue;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ci.gstoreplus.dao.dashboard.catalogue.CatalogueRepository;
import ci.gstoreplus.dao.dashboard.catalogue.PubPortailRepository;
import ci.gstoreplus.entity.catalogue.PubPortail;
import ci.gstoreplus.exception.InvalideImmobilierException;

@Service
public class PubPortailMetierImpl implements PubPortailMetier {
	@Autowired
	private PubPortailRepository pubPortailRepository;
	@Override
	public PubPortail creer(PubPortail entity) throws InvalideImmobilierException {
		// TODO Auto-generated method stub
		return pubPortailRepository.save(entity);
	}

	@Override
	public PubPortail modifier(PubPortail entity) throws InvalideImmobilierException {
		// TODO Auto-generated method stub
		return pubPortailRepository.save(entity);
	}

	@Override
	public List<PubPortail> findAll() {
		// TODO Auto-generated method stub
		return pubPortailRepository.findAll();
	}

	@Override
	public PubPortail findById(Long id) {
		// TODO Auto-generated method stub
		return pubPortailRepository.findById(id).get();
	}

	@Override
	public boolean supprimer(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean supprimer(List<PubPortail> entites) {
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
