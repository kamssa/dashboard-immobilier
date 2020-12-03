package ci.gstoreplus.entity.dashboard.admin;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import ci.gstoreplus.entity.dashboard.shared.Personne;

@Entity
@DiscriminatorValue("AD")
public class Admin extends Personne {

	
	private static final long serialVersionUID = 1L;

	public Admin() {
		super();
	}

	

	@Override
	public String toString() {
		return String.format("Admin[%s]", super.toString());

	}

}