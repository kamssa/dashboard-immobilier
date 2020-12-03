package ci.gstoreplus.entity.client;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import ci.gstoreplus.entity.dashboard.shared.Personne;

@Entity
@DiscriminatorValue("CL")
public class Client extends Personne{

	private static final long serialVersionUID = 1L;

}
