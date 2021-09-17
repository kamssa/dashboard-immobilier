package ci.gstoreplus.dashboard.metier.catalogue;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ci.gstoreplus.dao.dashboard.catalogue.VersementRepository;
import ci.gstoreplus.entity.catalogue.Versement;
import ci.gstoreplus.exception.InvalideImmobilierException;

@Service
public class VersementMetierImpl implements VersementMetier{
@Autowired
private VersementRepository versementRepository;
	@Override
	public Versement creer(Versement entity) throws InvalideImmobilierException {
		// TODO Auto-generated method stub
		return versementRepository.save(entity);
	}

	@Override
	public Versement modifier(Versement entity) throws InvalideImmobilierException {
		// TODO Auto-generated method stub
		return versementRepository.save(entity);
	}

	@Override
	public List<Versement> findAll() {
		// TODO Auto-generated method stub
		return versementRepository.findAll();
	}

	@Override
	public Versement findById(Long id) {
		// TODO Auto-generated method stub
		return versementRepository.findById(id).get();
	}

	@Override
	public boolean supprimer(Long id) {
		// TODO Auto-generated method stub
		versementRepository.deleteById(id);
		return true;
	}

	@Override
	public boolean supprimer(List<Versement> entites) {
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
