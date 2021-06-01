package ci.gstoreplus.dashboard.metier.catalogue;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ci.gstoreplus.dao.dashboard.catalogue.FlashTerrainRepository;
import ci.gstoreplus.entity.catalogue.Document;
import ci.gstoreplus.entity.catalogue.FlashTerrain;
import ci.gstoreplus.entity.catalogue.Ville;
import ci.gstoreplus.exception.InvalideImmobilierException;

@Service
public class FlashTerrainMetierImpl implements FlashTerrainMetier{
@Autowired
private FlashTerrainRepository flashTerrainRepository;
	@Override
	public FlashTerrain creer(FlashTerrain entity) throws InvalideImmobilierException {
		if ((entity.getLibelle().equals(null)) || (entity.getLibelle()== "")) {
			throw new InvalideImmobilierException("Le nom ne peut etre null");
		}
		Optional<FlashTerrain> v = null;

		v = flashTerrainRepository.findByLibelle(entity.getLibelle());
		if (v.isPresent()) {
			throw new InvalideImmobilierException("Ce nom est déjà utilisé");
		}
		
		return flashTerrainRepository.save(entity);
	}

	@Override
	public FlashTerrain modifier(FlashTerrain modif) throws InvalideImmobilierException {
		Optional<FlashTerrain> cat = flashTerrainRepository.findById(modif.getId());
		if (cat.isPresent()) {

			if (cat.get().getVersion() != modif.getVersion()) {
				throw new InvalideImmobilierException("ce libelle a deja ete modifier");
			}

		} else
			throw new InvalideImmobilierException("modif est un objet null");

		
		return flashTerrainRepository.save(modif);
	}

	@Override
	public List<FlashTerrain> findAll() {
		// TODO Auto-generated method stub
		return flashTerrainRepository.findAll();
	}

	@Override
	public FlashTerrain findById(Long id) {
		// TODO Auto-generated method stub
		return flashTerrainRepository.findById(id).get();
	}

	@Override
	public boolean supprimer(Long id) {
   flashTerrainRepository.deleteById(id);
		return true;
	}

	@Override
	public boolean supprimer(List<FlashTerrain> entites) {
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
