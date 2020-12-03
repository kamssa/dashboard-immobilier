package ci.gstoreplus.entity.client;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import ci.gstoreplus.entity.dashboard.shared.Personne;

@Entity
@DiscriminatorValue("ME")
public class Membre extends Personne {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    
}
