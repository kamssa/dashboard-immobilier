package ci.gstoreplus.dashboard.metier;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ci.gstoreplus.dao.dashboard.catalogue.DetailMaisonRepository;
import ci.gstoreplus.dashboard.metier.catalogue.DetailMaisonMetier;
import ci.gstoreplus.entity.catalogue.DetailMaison;
import ci.gstoreplus.exception.InvalideImmobilierException;

@Service
public class DetailMaisonMetierImpl implements DetailMaisonMetier{
@Autowired
private DetailMaisonRepository detailMaisonRepository;
	@Override
	public DetailMaison creer(DetailMaison entity) throws InvalideImmobilierException {
		// TODO Auto-generated method stub
		return detailMaisonRepository.save(entity);
	}

	@Override
	public DetailMaison modifier(DetailMaison entity) throws InvalideImmobilierException {
		// TODO Auto-generated method stub
		return detailMaisonRepository.save(entity);
	}

	@Override
	public List<DetailMaison> findAll() {
		// TODO Auto-generated method stub
		return detailMaisonRepository.findAll();
	}

	@Override
	public DetailMaison findById(Long id) {
		// TODO Auto-generated method stub
		return detailMaisonRepository.findById(id).get();
	}

	@Override
	public boolean supprimer(Long id) {
		detailMaisonRepository.deleteById(id);
		return true;
	}

	@Override
	public boolean supprimer(List<DetailMaison> entites) {
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
	public DetailMaison findDetailMaisonIdMaison(long id) {
		// TODO Auto-generated method stub
		return detailMaisonRepository.findDetailMaisonIdMaison(id);
	}

}
