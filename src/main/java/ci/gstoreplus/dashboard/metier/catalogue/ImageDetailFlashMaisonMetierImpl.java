package ci.gstoreplus.dashboard.metier.catalogue;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ci.gstoreplus.dao.dashboard.catalogue.ImageDetailFlashMaisonRepository;
import ci.gstoreplus.entity.catalogue.ImageDetailFlashMaison;
import ci.gstoreplus.exception.InvalideImmobilierException;

@Service
public class ImageDetailFlashMaisonMetierImpl implements ImageDetailFlashMaisonMetier{
@Autowired
ImageDetailFlashMaisonRepository imageDetailFlashMaisonRepository;
	
@Override
	public ImageDetailFlashMaison creer(ImageDetailFlashMaison entity) throws InvalideImmobilierException {
		return imageDetailFlashMaisonRepository.save(entity);
	}

	@Override
	public ImageDetailFlashMaison modifier(ImageDetailFlashMaison entity) throws InvalideImmobilierException {
		// TODO Auto-generated method stub
		return imageDetailFlashMaisonRepository.save(entity);
	}

	@Override
	public List<ImageDetailFlashMaison> findAll() {
		// TODO Auto-generated method stub
		return imageDetailFlashMaisonRepository.findAll();
	}

	@Override
	public ImageDetailFlashMaison findById(Long id) {
		// TODO Auto-generated method stub
		return imageDetailFlashMaisonRepository.findById(id).get();
	}

	@Override
	public boolean supprimer(Long id) {
		imageDetailFlashMaisonRepository.deleteById(id);
		return true;
	}

	@Override
	public boolean supprimer(List<ImageDetailFlashMaison> entites) {
		imageDetailFlashMaisonRepository.deleteAll(entites);
		return true;
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
	public List<ImageDetailFlashMaison> findImageByIdDetailFlash(Long id) {
		// TODO Auto-generated method stub
		return imageDetailFlashMaisonRepository.findImageByIdDetailFlash(id);
	}
	
}
