package ci.gstoreplus.dashboard.metier;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ci.gstoreplus.dao.dashboard.catalogue.VilleRepository;
import ci.gstoreplus.entity.catalogue.Categorie;
import ci.gstoreplus.entity.catalogue.Ville;
import ci.gstoreplus.exception.InvalideImmobilierException;

@Service
public class VilleMetierImpl implements VilleMetier{
@Autowired
private VilleRepository villeRepository;
	@Override
	public Ville creer(Ville entity) throws InvalideImmobilierException {
		if ((entity.getLibelle().equals(null)) || (entity.getLibelle()== "")) {
			throw new InvalideImmobilierException("Le nom ne peut etre null");
		}
		Optional<Ville> v = null;

		v = villeRepository.findByLibelle(entity.getLibelle());
		if (v.isPresent()) {
			throw new InvalideImmobilierException("Ce nom est déjà utilisé");
		}
		
		return villeRepository.save(entity);
	}

	@Override
	public Ville modifier(Ville modif) throws InvalideImmobilierException {
		Optional<Ville> cat = villeRepository.findById(modif.getId());
		if (cat.isPresent()) {

			if (cat.get().getVersion() != modif.getVersion()) {
				throw new InvalideImmobilierException("ce libelle a deja ete modifier");
			}

		} else
			throw new InvalideImmobilierException("modif est un objet null");

		
		return villeRepository.save(modif);
	}

	@Override
	public List<Ville> findAll() {
		// TODO Auto-generated method stub
		return villeRepository.findAll();
	}

	@Override
	public Ville findById(Long id) {
		// TODO Auto-generated method stub
		return villeRepository.findById(id).get();
	}

	@Override
	public boolean supprimer(Long id) {
     villeRepository.deleteById(id);
		return true;
	}

	@Override
	public boolean supprimer(List<Ville> entites) {
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
	public Optional<Ville> findByLibelle(String libelle) {
		// TODO Auto-generated method stub
		return villeRepository.findByLibelle(libelle);
	}

}
