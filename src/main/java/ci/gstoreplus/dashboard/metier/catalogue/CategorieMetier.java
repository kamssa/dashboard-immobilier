package ci.gstoreplus.dashboard.metier.catalogue;

import java.util.Optional;

import ci.gstoreplus.entity.catalogue.Categorie;
import ci.gstoreplus.metier.Imetier;

public interface CategorieMetier extends Imetier<Categorie, Long>{
	Optional<Categorie> findByNom(String nom);

}
