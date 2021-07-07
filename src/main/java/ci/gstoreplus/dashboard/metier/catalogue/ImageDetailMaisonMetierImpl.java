package ci.gstoreplus.dashboard.metier.catalogue;

import java.util.List;

import org.springframework.stereotype.Service;

import ci.gstoreplus.dao.dashboard.catalogue.ImageDetailMaisonRepository;
import ci.gstoreplus.entity.catalogue.ImageDetailMaison;
import ci.gstoreplus.exception.InvalideImmobilierException;

@Service
public class ImageDetailMaisonMetierImpl implements ImageDetailMaisonMetier{
private ImageDetailMaisonRepository imageDetailMaisonRepository;
	@Override
	public ImageDetailMaison creer(ImageDetailMaison entity) throws InvalideImmobilierException {
		// TODO Auto-generated method stub
		return imageDetailMaisonRepository.save(entity);
	}

	@Override
	public ImageDetailMaison modifier(ImageDetailMaison entity) throws InvalideImmobilierException {
		// TODO Auto-generated method stub
		return imageDetailMaisonRepository.save(entity);
	}

	@Override
	public List<ImageDetailMaison> findAll() {
		// TODO Auto-generated method stub
		return imageDetailMaisonRepository.findAll();
	}

	@Override
	public ImageDetailMaison findById(Long id) {
		// TODO Auto-generated method stub
		return imageDetailMaisonRepository.findById(id).get();
	}

	@Override
	public boolean supprimer(Long id) {
		imageDetailMaisonRepository.deleteById(id);
		return true;
	}

	@Override
	public boolean supprimer(List<ImageDetailMaison> entites) {
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
	public List<ImageDetailMaison> findImageByIdDetailMaisonn(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
