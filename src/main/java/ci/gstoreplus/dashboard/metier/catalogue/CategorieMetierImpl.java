package ci.gstoreplus.dashboard.metier.catalogue;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ci.gstoreplus.dao.dashboard.catalogue.CatalogueRepository;
import ci.gstoreplus.entity.catalogue.Categorie;
import ci.gstoreplus.exception.InvalideImmobilierException;

@Service
public class CategorieMetierImpl implements CategorieMetier {
@Autowired
private CatalogueRepository catalogueRepository;
	@Override
	public Categorie creer(Categorie entity) throws InvalideImmobilierException {
		if ((entity.getNom().equals(null)) || (entity.getNom()== "")) {
			throw new InvalideImmobilierException("Le nom ne peut etre null");
		}
		Optional<Categorie> cats = null;

		cats = catalogueRepository.findByNom(entity.getNom());
		if (cats.isPresent()) {
			throw new InvalideImmobilierException("Ce nom est déjà utilisé");
		}
		
		return catalogueRepository.save(entity);
	}

	@Override
	public Categorie modifier(Categorie modif) throws InvalideImmobilierException {
		Optional<Categorie> cat = catalogueRepository.findById(modif.getId());
		if (cat.isPresent()) {

			if (cat.get().getVersion() != modif.getVersion()) {
				throw new InvalideImmobilierException("ce libelle a deja ete modifier");
			}

		} else
			throw new InvalideImmobilierException("modif est un objet null");

		
		return catalogueRepository.save(modif);
	}

	@Override
	public List<Categorie> findAll() {
		return catalogueRepository.findAll();
	}

	@Override
	public Categorie findById(Long id) {
		return catalogueRepository.findById(id).get();
	}

	@Override
	public boolean supprimer(Long id) {
		catalogueRepository.deleteById(id);
		return true;
	}

	@Override
	public boolean supprimer(List<Categorie> entites) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean existe(Long id) {
		catalogueRepository.existsById(id);
		return true;
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
	public Optional<Categorie> findByNom(String nom) {
		// TODO Auto-generated method stub
		return catalogueRepository.findByNom(nom);
	}

	

}
