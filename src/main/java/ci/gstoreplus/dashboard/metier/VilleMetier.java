package ci.gstoreplus.dashboard.metier;

import java.util.Optional;

import ci.gstoreplus.entity.catalogue.Ville;
import ci.gstoreplus.metier.Imetier;

public interface VilleMetier extends Imetier<Ville, Long>{
	Optional<Ville> findByLibelle(String libelle);

}
