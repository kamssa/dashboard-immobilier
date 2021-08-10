package ci.gstoreplus.dashboard.metier.catalogue;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ci.gstoreplus.dao.dashboard.catalogue.ImageDetailReposirtory;
import ci.gstoreplus.entity.catalogue.Image;
import ci.gstoreplus.entity.catalogue.ImageDetail;
import ci.gstoreplus.exception.InvalideImmobilierException;

@Service
public class ImageDetailMetierImpl  implements ImageDetailTerrainMetier{
@Autowired
private ImageDetailReposirtory imageDetailReposirtory;
	@Override
	public ImageDetail creer(ImageDetail entity) throws InvalideImmobilierException {
		// TODO Auto-generated method stub
		return imageDetailReposirtory.save(entity);
	}

	@Override
	public ImageDetail modifier(ImageDetail entity) throws InvalideImmobilierException {
		// TODO Auto-generated method stub
		return imageDetailReposirtory.save(entity);
	}

	@Override
	public List<ImageDetail> findAll() {
		// TODO Auto-generated method stub
		return imageDetailReposirtory.findAll();
	}

	@Override
	public ImageDetail findById(Long id) {
		// TODO Auto-generated method stub
		return imageDetailReposirtory.findById(id).get();
	}

	@Override
	public boolean supprimer(Long id) {
		imageDetailReposirtory.deleteById(id);
		return true;
	}

	@Override
	public boolean supprimer(List<ImageDetail> entites) {
		imageDetailReposirtory.deleteAll(entites);;
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
	public List<ImageDetail> findImageByIdDetailTerrain(Long id) {
		// TODO Auto-generated method stub
		return imageDetailReposirtory.findImageByIdDetailTerrain(id);
	}

	

	

}
