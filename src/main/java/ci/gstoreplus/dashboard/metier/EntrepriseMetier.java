package ci.gstoreplus.dashboard.metier;

import java.util.Optional;

import ci.gstoreplus.entity.dashboard.admin.Entreprise;
import ci.gstoreplus.metier.Imetier;

public interface EntrepriseMetier extends Imetier<Entreprise, Long> {
	Optional<Entreprise> findByNom(String nom);

}
