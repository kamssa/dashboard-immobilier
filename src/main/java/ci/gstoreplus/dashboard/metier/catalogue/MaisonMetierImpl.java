package ci.gstoreplus.dashboard.metier.catalogue;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ci.gstoreplus.dao.dashboard.catalogue.MaisonRepository;
import ci.gstoreplus.entity.catalogue.Maison;
import ci.gstoreplus.entity.catalogue.Terrain;
import ci.gstoreplus.exception.InvalideImmobilierException;

@Service
public class MaisonMetierImpl implements MaisonMetier{
@Autowired
private MaisonRepository maisonRepository;
	@Override
	public Maison creer(Maison entity) throws InvalideImmobilierException {
		// TODO Auto-generated method stub
		return maisonRepository.save(entity);
	}

	@Override
	public Maison modifier(Maison entity) throws InvalideImmobilierException {
		// TODO Auto-generated method stub
		return maisonRepository.save(entity);
	}

	@Override
	public List<Maison> findAll() {
		// TODO Auto-generated method stub
		return maisonRepository.findAll();
	}

	@Override
	public Maison findById(Long id) {
		// TODO Auto-generated method stub
		return maisonRepository.findById(id).get();
	}

	@Override
	public boolean supprimer(Long id) {
		maisonRepository.deleteById(id);
		return true;
	}

	@Override
	public boolean supprimer(List<Maison> entites) {
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

	@Override
	public List<Maison> findMaisonByIdVille(Long id) {
		// TODO Auto-generated method stub
		return maisonRepository.findMaisonByIdVille(id);
	}

	@Override
	public List<Maison> recherchePrixMax(String type, String ville, double prix) {
		// TODO Auto-generated method stub
		return null;
	}

}
