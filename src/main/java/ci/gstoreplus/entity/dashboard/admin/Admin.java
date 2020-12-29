package ci.gstoreplus.entity.dashboard.admin;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import ci.gstoreplus.entity.dashboard.shared.Personne;

@Entity
@DiscriminatorValue("AD")
public class Admin extends Personne {

	
	private static final long serialVersionUID = 1L;

	public Admin() {
		super();
	}

	

	public Admin(@NotBlank @Size(max = 40) String nom, @NotBlank @Size(max = 15) String prenom,
			@NotBlank @Size(max = 40) @Email String email, @NotBlank @Size(max = 100) String password) {
		super(nom, prenom, email, password);
		// TODO Auto-generated constructor stub
	}



	@Override
	public String toString() {
		return String.format("Admin[%s]", super.toString());

	}

}