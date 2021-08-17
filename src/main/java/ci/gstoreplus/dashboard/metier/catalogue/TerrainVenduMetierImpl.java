package ci.gstoreplus.dashboard.metier.catalogue;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ci.gstoreplus.dao.dashboard.catalogue.TerrainVenduRepository;
import ci.gstoreplus.entity.catalogue.TerrainVendu;
import ci.gstoreplus.exception.InvalideImmobilierException;

@Service
public class TerrainVenduMetierImpl implements TerrainVenduMetier{
@Autowired
private TerrainVenduRepository terrainVenduRepository;
	@Override
	public TerrainVendu creer(TerrainVendu entity) throws InvalideImmobilierException {
		// TODO Auto-generated method stub
		return terrainVenduRepository.save(entity);
	}

	@Override
	public TerrainVendu modifier(TerrainVendu entity) throws InvalideImmobilierException {
		// TODO Auto-generated method stub
		return terrainVenduRepository.save(entity);
	}

	@Override
	public List<TerrainVendu> findAll() {
		// TODO Auto-generated method stub
		return terrainVenduRepository.findAll();
	}

	@Override
	public TerrainVendu findById(Long id) {
		// TODO Auto-generated method stub
		return terrainVenduRepository.findById(id).get();
	}

	@Override
	public boolean supprimer(Long id) {
		terrainVenduRepository.deleteById(id);
		return true;
	}

	@Override
	public boolean supprimer(List<TerrainVendu> entites) {
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
	public List<TerrainVendu> findTerrainVenduByIdPersonne(long id) {
		// TODO Auto-generated method stub
		return terrainVenduRepository.findTerrainVenduByIdPersonne(id);
	}

}
