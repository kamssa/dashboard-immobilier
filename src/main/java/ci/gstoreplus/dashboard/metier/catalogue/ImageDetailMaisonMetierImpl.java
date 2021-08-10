package ci.gstoreplus.dashboard.metier.catalogue;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ci.gstoreplus.dao.dashboard.catalogue.ImageDetailMaisonRepository;
import ci.gstoreplus.entity.catalogue.ImageDetailMaison;
import ci.gstoreplus.exception.InvalideImmobilierException;

@Service
public class ImageDetailMaisonMetierImpl implements ImageDetailMaisonMetier{
@Autowired
	private ImageDetailMaisonRepository imageDetailMaisonRepository;
	@Override
	public ImageDetailMaison creer(ImageDetailMaison entity) throws InvalideImmobilierException {
		return imageDetailMaisonRepository.save(entity);
	}

	@Override
	public ImageDetailMaison modifier(ImageDetailMaison entity) throws InvalideImmobilierException {
		return imageDetailMaisonRepository.save(entity);
	}

	@Override
	public List<ImageDetailMaison> findAll() {
		return imageDetailMaisonRepository.findAll();
	}

	@Override
	public ImageDetailMaison findById(Long id) {
		return imageDetailMaisonRepository.findById(id).get();
	}

	@Override
	public boolean supprimer(Long id) {
		imageDetailMaisonRepository.deleteById(id);
		return true;
	}

	@Override
	public boolean supprimer(List<ImageDetailMaison> entites) {
		imageDetailMaisonRepository.deleteAll(entites);
		return true;
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
	public boolean existsByEmail(String email) {
		return false;
	}

	@Override
	public List<ImageDetailMaison> findImageByIdDetailMaisonn(Long id) {
		return imageDetailMaisonRepository.findImageByIdDetailMaisonn(id);
	}

}
