package ci.gstoreplus.entity.dashboard.admin;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import ci.gstoreplus.entity.dashboard.shared.Personne;

@Entity
@DiscriminatorValue("MA")
public class Manager extends Personne{

	
	private static final long serialVersionUID = 1L;

}
