package ci.gstoreplus.dashboard.metier.catalogue;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ci.gstoreplus.dao.dashboard.catalogue.TerrainRepository;
import ci.gstoreplus.entity.catalogue.Terrain;
import ci.gstoreplus.exception.InvalideImmobilierException;

@Service
public class TerrainMetierImpl implements TerrainMetier{
@Autowired
private TerrainRepository terrainRepository;
	@Override
	public Terrain creer(Terrain entity) throws InvalideImmobilierException {
		if ((entity.getLibelle().equals(null)) || (entity.getLibelle()== "")) {
			throw new InvalideImmobilierException("Le libelle ne peut etre null");
		}
		Optional<Terrain> terrain = null;

		terrain = terrainRepository.findByLibelle(entity.getLibelle());
		if (terrain.isPresent()) {
			throw new InvalideImmobilierException("Ce libelle est deja utilise");
		}
		
		return terrainRepository.save(entity);
	}

	@Override
	public Terrain modifier(Terrain modif) throws InvalideImmobilierException {
		Optional<Terrain> terrain = terrainRepository.findById(modif.getId());
		if (terrain.isPresent()) {

			if (terrain.get().getVersion() != modif.getVersion()) {
				throw new InvalideImmobilierException("ce libelle a deja ete modifier");
			}

		} else
			throw new InvalideImmobilierException("modif est un objet null");

		
		return terrainRepository.save(modif);
	}

	@Override
	public List<Terrain> findAll() {
		List<Terrain> ters = null;
		List<Terrain> terrains = terrainRepository.findAll();
	    ters = terrains.stream().collect(Collectors.toList());
	    return ters;
	}

	@Override
	public Terrain findById(Long id) {
		return terrainRepository.findById(id).get();
	}

	@Override
	public boolean supprimer(Long id) {
		terrainRepository.deleteById(id);
		return true;
	}

	@Override
	public boolean supprimer(List<Terrain> entites) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean existe(Long id) {
		terrainRepository.existsById(id);
		return true;
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
	public Optional<Terrain> findByLibelle(String libelle) {
		// TODO Auto-generated method stub
		return terrainRepository.findByLibelle(libelle);
	}


	@Override
	public List<Terrain> findTerrainByIdVille(Long id) {
		List<Terrain> ters = null;
		List<Terrain> terrains = terrainRepository.findAll();
	    ters = terrains.stream().filter(t -> t.getVille().getId()==id).limit(4).collect(Collectors.toList());
	    return ters;		
	}
	@Override
	public List<Terrain> findTerrainByVille() {
		List<Terrain> ters = null;
		List<Terrain> terrains = terrainRepository.findAll();
	    ters = terrains.stream().filter(t -> t.getVille().getLibelle()=="Abidjan").collect(Collectors.toList());
	    return ters;		
	}

	@Override
	public List<Terrain> recherchePrixMax(String type, String ville, double prix) {
		List<Terrain> ters = null;
		List<Terrain> terrains = terrainRepository.findAll();
	    ters = terrains.stream().filter(t -> t.getType().equals(type))
	    		.filter(t -> t.getVille().equals(ville))
	    		.collect(Collectors.toList());
	    return ters;			
	}

	
	
}
