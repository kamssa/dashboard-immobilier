package ci.gstoreplus.dashboard.metier.catalogue;

import java.util.List;

import org.springframework.stereotype.Service;

import ci.gstoreplus.dao.dashboard.catalogue.FlashTerrainRepository;
import ci.gstoreplus.entity.catalogue.FlashTerrain;
import ci.gstoreplus.exception.InvalideImmobilierException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class FlashTerrainMetierImpl implements FlashTerrainMetier{
	private FlashTerrainRepository flashTerrainRepository;
	@Override
	public FlashTerrain creer(FlashTerrain entity) throws InvalideImmobilierException {
		// TODO Auto-generated method stub
		return flashTerrainRepository.save(entity);
	}

	@Override
	public FlashTerrain modifier(FlashTerrain entity) throws InvalideImmobilierException {
		// TODO Auto-generated method stub
		return flashTerrainRepository.save(entity);
	}

	@Override
	public List<FlashTerrain> findAll() {
		// TODO Auto-generated method stub
		return flashTerrainRepository.findAll();
	}

	@Override
	public FlashTerrain findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean supprimer(Long id) {
		// TODO Auto-generated method stub
		return false;
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
