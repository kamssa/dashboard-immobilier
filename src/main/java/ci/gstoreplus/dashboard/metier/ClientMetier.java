package ci.gstoreplus.dashboard.metier;

import java.util.List;
import java.util.Optional;

import ci.gstoreplus.entity.dashboard.shared.Personne;
import ci.gstoreplus.metier.Imetier;

public interface ClientMetier extends Imetier<Personne, Long>{
	Optional<Personne> findByEmail(String email);
	Personne modifPassword(Personne p);
	List<Personne> findAllPersonnesParMc( String mc);
}
