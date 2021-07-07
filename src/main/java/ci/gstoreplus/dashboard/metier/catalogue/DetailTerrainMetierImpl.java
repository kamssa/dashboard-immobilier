package ci.gstoreplus.dashboard.metier.catalogue;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ci.gstoreplus.dao.dashboard.catalogue.DetailTerrainRepository;
import ci.gstoreplus.entity.catalogue.DetailTerrain;
import ci.gstoreplus.exception.InvalideImmobilierException;

@Service
public class DetailTerrainMetierImpl implements DetailTerrainMetier{
@Autowired
private DetailTerrainRepository detailTerrainRepository;

@Override
public DetailTerrain creer(DetailTerrain entity) throws InvalideImmobilierException {
	// TODO Auto-generated method stub
	return detailTerrainRepository.save(entity);
}

@Override
public DetailTerrain modifier(DetailTerrain entity) throws InvalideImmobilierException {
	// TODO Auto-generated method stub
	return detailTerrainRepository.save(entity);
}

@Override
public List<DetailTerrain> findAll() {
	// TODO Auto-generated method stub
	return detailTerrainRepository.findAll();
}

@Override
public DetailTerrain findById(Long id) {
	// TODO Auto-generated method stub
	return detailTerrainRepository.findById(id).get();
}

@Override
public boolean supprimer(Long id) {
  detailTerrainRepository.deleteById(id);
	return true;
}

@Override
public boolean supprimer(List<DetailTerrain> entites) {
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
public DetailTerrain findDetailTerrainIdTerrain(long id) {
	// TODO Auto-generated method stub
	return detailTerrainRepository.findDetailTerrainIdTerrain(id);
}
}
