package ci.gstoreplus.dashboard.metier.catalogue;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ci.gstoreplus.dao.dashboard.catalogue.TerrainVenduRepository;
import ci.gstoreplus.entity.catalogue.TerrainVendu;
import ci.gstoreplus.entity.dashboard.admin.Employe;
import ci.gstoreplus.entity.dashboard.shared.Personne;
import ci.gstoreplus.exception.InvalideImmobilierException;

@Service
public class TerrainVenduMetierImpl implements TerrainVenduMetier{
@Autowired
private TerrainVenduRepository terrainVenduRepository;
	@Override
	public TerrainVendu creer(TerrainVendu entity) throws InvalideImmobilierException {
		if(entity.getPersonne()== null) {
			throw new InvalideImmobilierException("Le terrain doit avoir un client");
		}
		return terrainVenduRepository.save(entity);
	}

	@Override
	public TerrainVendu modifier(TerrainVendu entity) throws InvalideImmobilierException {
		Optional<TerrainVendu> t = terrainVenduRepository.findById(entity.getId());
		if(entity.getPersonne()== null) {
			entity.setPersonne(t.get().getPersonne());
		} 
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
	@Override
	public List<TerrainVendu> findAllTerrainAbonneGeo() {
		List<TerrainVendu> t = null;
		List<TerrainVendu> terrainvendus = terrainVenduRepository.findAll();
	    t = terrainvendus.stream().filter(p -> p.isAbonneGeo() == true).collect(Collectors.toList());
	    return t;		
	    }

}
