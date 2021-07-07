package ci.gstoreplus.dashboard.metier;

import java.util.Optional;

import ci.gstoreplus.entity.dashboard.shared.Personne;
import ci.gstoreplus.metier.Imetier;

public interface ClientMetier extends Imetier<Personne, Long>{
	Optional<Personne> findByEmail(String email);

}
