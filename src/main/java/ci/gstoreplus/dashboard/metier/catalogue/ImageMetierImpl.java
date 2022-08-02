package ci.gstoreplus.dashboard.metier.catalogue;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ci.gstoreplus.dao.dashboard.catalogue.ImageRepository;
import ci.gstoreplus.entity.catalogue.Image;
import ci.gstoreplus.exception.InvalideImmobilierException;

@Service
public class ImageMetierImpl implements ImageMetier{
@Autowired
ImageRepository imageRepository;
	@Override
	public Image creer(Image entity) throws InvalideImmobilierException {
		// TODO Auto-generated method stub
		return imageRepository.save(entity);
	}

	@Override
	public Image modifier(Image entity) throws InvalideImmobilierException {
		// TODO Auto-generated method stub
		return imageRepository.save(entity);
	}

	@Override
	public List<Image> findAll() {
		// TODO Auto-generated method stub
		return imageRepository.findAll();
	}

	@Override
	public Image findById(Long id) {
		// TODO Auto-generated method stub
		return imageRepository.findById(id).get();
	}

	@Override
	public boolean supprimer(Long id) {
		imageRepository.deleteById(id);
		return true;
	}

	@Override
	public boolean supprimer(List<Image> entites) {
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
