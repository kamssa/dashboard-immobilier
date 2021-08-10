package ci.gstoreplus.dashboard.metier.catalogue;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ci.gstoreplus.dao.dashboard.catalogue.DetailFlashMaisonRepository;
import ci.gstoreplus.dashboard.metier.DetailFlashMaisonMetier;
import ci.gstoreplus.entity.catalogue.DetailFlashMaison;
import ci.gstoreplus.exception.InvalideImmobilierException;

@Service
public class DetailFlashMaisonMetierImpl implements DetailFlashMaisonMetier{
@Autowired
DetailFlashMaisonRepository detailFlashMaisonRepository;
	
@Override
	public DetailFlashMaison creer(DetailFlashMaison entity) throws InvalideImmobilierException {
		return detailFlashMaisonRepository.save(entity);
	}

	@Override
	public DetailFlashMaison modifier(DetailFlashMaison entity) throws InvalideImmobilierException {
		// TODO Auto-generated method stub
		return detailFlashMaisonRepository.save(entity);
	}

	@Override
	public List<DetailFlashMaison> findAll() {
		// TODO Auto-generated method stub
		return detailFlashMaisonRepository.findAll();
	}

	@Override
	public DetailFlashMaison findById(Long id) {
		// TODO Auto-generated method stub
		return detailFlashMaisonRepository.findById(id).get();
	}

	@Override
	public boolean supprimer(Long id) {
		detailFlashMaisonRepository.deleteById(id);
		return true;
	}

	@Override
	public boolean supprimer(List<DetailFlashMaison> entites) {
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
	public List<DetailFlashMaison> findDetailFashMaisonIdFlashMaison(long id) {
		// TODO Auto-generated method stub
		return detailFlashMaisonRepository.findDetailFashMaisonIdFlashMaison(id);
	}

}
