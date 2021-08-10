package ci.gstoreplus.dashboard.metier.catalogue;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ci.gstoreplus.dao.dashboard.catalogue.FlashMaisonRepository;
import ci.gstoreplus.entity.catalogue.FlashMaison;
import ci.gstoreplus.exception.InvalideImmobilierException;

@Service
public class FlashMaisonMetierImpl implements FlashMaisonMetier{
@Autowired
private FlashMaisonRepository flashMaisonRepository;
	@Override
	public FlashMaison creer(FlashMaison entity) throws InvalideImmobilierException {
		if ((entity.getLibelle().equals(null)) || (entity.getLibelle()== "")) {
			throw new InvalideImmobilierException("Le nom ne peut etre null");
		}
		Optional<FlashMaison> v = null;

		v = flashMaisonRepository.findByLibelle(entity.getLibelle());
		if (v.isPresent()) {
			throw new InvalideImmobilierException("Ce nom est déjà utilisé");
		}
		
		return flashMaisonRepository.save(entity);
	}

	@Override
	public FlashMaison modifier(FlashMaison modif) throws InvalideImmobilierException {
		Optional<FlashMaison> cat = flashMaisonRepository.findById(modif.getId());
		if (cat.isPresent()) {

			if (cat.get().getVersion() != modif.getVersion()) {
				throw new InvalideImmobilierException("ce libelle a deja ete modifier");
			}

		} else
			throw new InvalideImmobilierException("modif est un objet null");

		
		return flashMaisonRepository.save(modif);
	}

	@Override
	public List<FlashMaison> findAll() {
		// TODO Auto-generated method stub
		return flashMaisonRepository.findAll();
	}

	@Override
	public FlashMaison findById(Long id) {
		// TODO Auto-generated method stub
		return flashMaisonRepository.findById(id).get();
	}

	@Override
	public boolean supprimer(Long id) {
   flashMaisonRepository.deleteById(id);
		return true;
	}

	@Override
	public boolean supprimer(List<FlashMaison> entites) {
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
