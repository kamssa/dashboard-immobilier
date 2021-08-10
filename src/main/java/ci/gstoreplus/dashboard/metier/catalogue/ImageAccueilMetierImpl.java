package ci.gstoreplus.dashboard.metier.catalogue;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ci.gstoreplus.dao.dashboard.catalogue.ImageAccueilRepository;
import ci.gstoreplus.entity.catalogue.ImageAccueil;
import ci.gstoreplus.exception.InvalideImmobilierException;

@Service
public class ImageAccueilMetierImpl implements ImageAccueilMetier{
@Autowired
private ImageAccueilRepository imageAccueilRepository;
	@Override
	public ImageAccueil creer(ImageAccueil entity) throws InvalideImmobilierException {
	return imageAccueilRepository.save(entity);
	}

	@Override
	public ImageAccueil modifier(ImageAccueil entity) throws InvalideImmobilierException {
		
		return imageAccueilRepository.save(entity);
	}

	@Override
	public List<ImageAccueil> findAll() {
		
		return imageAccueilRepository.findAll();
	}

	@Override
	public ImageAccueil findById(Long id) {
		
		return imageAccueilRepository.findById(id).get();
	}

	@Override
	public boolean supprimer(Long id) {
	imageAccueilRepository.deleteById(id);
		return true;
	}

	@Override
	public boolean supprimer(List<ImageAccueil> entites) {
		
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
