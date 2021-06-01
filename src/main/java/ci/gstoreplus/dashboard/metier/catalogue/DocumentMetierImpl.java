package ci.gstoreplus.dashboard.metier.catalogue;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ci.gstoreplus.dao.dashboard.catalogue.DocumentRepository;
import ci.gstoreplus.entity.catalogue.Document;
import ci.gstoreplus.exception.InvalideImmobilierException;

@Service
public class DocumentMetierImpl implements DocumentMetier {
@Autowired
private DocumentRepository catalogueRepository;
	@Override
	public Document creer(Document entity) throws InvalideImmobilierException {
		if ((entity.getLibelle().equals(null)) || (entity.getLibelle()== "")) {
			throw new InvalideImmobilierException("Le libelle ne peut etre null");
		}
		Optional<Document> cats = null;

		cats = catalogueRepository.findByLibelle(entity.getLibelle());
		if (cats.isPresent()) {
			throw new InvalideImmobilierException("Ce nom est déjà utilisé");
		}
		
		return catalogueRepository.save(entity);
	}

	@Override
	public Document modifier(Document modif) throws InvalideImmobilierException {
		Optional<Document> cat = catalogueRepository.findById(modif.getId());
		if (cat.isPresent()) {

			if (cat.get().getVersion() != modif.getVersion()) {
				throw new InvalideImmobilierException("ce libelle a deja ete modifier");
			}

		} else
			throw new InvalideImmobilierException("modif est un objet null");

		
		return catalogueRepository.save(modif);
	}

	@Override
	public List<Document> findAll() {
		return catalogueRepository.findAll();
	}

	@Override
	public Document findById(Long id) {
		return catalogueRepository.findById(id).get();
	}

	@Override
	public boolean supprimer(Long id) {
		catalogueRepository.deleteById(id);
		return true;
	}

	@Override
	public boolean supprimer(List<Document> entites) {
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
	public boolean existsByEmail(String email) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Optional<Document> findByLibelle(String libelle) {
		// TODO Auto-generated method stub
		return catalogueRepository.findByLibelle(libelle);
	}

	

}
