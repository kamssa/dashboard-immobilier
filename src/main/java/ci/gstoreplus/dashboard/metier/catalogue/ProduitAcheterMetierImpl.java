package ci.gstoreplus.dashboard.metier.catalogue;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ci.gstoreplus.dao.dashboard.catalogue.PoduitAcheterRepository;
import ci.gstoreplus.entity.catalogue.TerrainAcheter;
import ci.gstoreplus.exception.InvalideImmobilierException;

@Service
public class ProduitAcheterMetierImpl implements ProduitAcheterMetier {
@Autowired
private PoduitAcheterRepository terrainAcheterRepository;
	@Override
	public TerrainAcheter creer(TerrainAcheter entity) throws InvalideImmobilierException {
		// TODO Auto-generated method stub
		return terrainAcheterRepository.save(entity);
	}

	@Override
	public TerrainAcheter modifier(TerrainAcheter entity) throws InvalideImmobilierException {
		// TODO Auto-generated method stub
		return terrainAcheterRepository.save(entity);
	}

	@Override
	public List<TerrainAcheter> findAll() {
		// TODO Auto-generated method stub
		return terrainAcheterRepository.findAll();
	}

	@Override
	public TerrainAcheter findById(Long id) {
		// TODO Auto-generated method stub
		return terrainAcheterRepository.findById(id).get();
	}

	@Override
	public boolean supprimer(Long id) {
		terrainAcheterRepository.deleteById(id);
		return true;
	}

	@Override
	public boolean supprimer(List<TerrainAcheter> entites) {
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
	public TerrainAcheter findTerrainAcheteByIdPersonne(long id) {
		// TODO Auto-generated method stub
		return terrainAcheterRepository.findTerrainAcheteByIdPersonne(id);
	}

}
