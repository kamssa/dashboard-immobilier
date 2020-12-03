package ci.gstoreplus.dashboard.metier;

import ci.gstoreplus.entity.dashboard.shared.Personne;
import ci.gstoreplus.metier.Imetier;

public interface PersonneMetier  extends Imetier<Personne, Long>{
	void createVerificationToken(Personne personne, String token);
	 Personne getPersonne(String verificationToken);
	 String validateVerificationToken(String token);
	 
}
