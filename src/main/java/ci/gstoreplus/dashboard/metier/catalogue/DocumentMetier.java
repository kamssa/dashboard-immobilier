package ci.gstoreplus.dashboard.metier.catalogue;

import java.util.Optional;

import ci.gstoreplus.entity.catalogue.Document;
import ci.gstoreplus.metier.Imetier;

public interface DocumentMetier extends Imetier<Document, Long>{
	Optional<Document> findByLibelle(String libelle);

}
