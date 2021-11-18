package ci.gstoreplus.entity.dashboard.admin;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import ci.gstoreplus.entity.dashboard.shared.Personne;


@Entity
@DiscriminatorValue("EM")
public class Employe extends Personne{

	private static final long serialVersionUID = 1L;
	@ManyToOne(fetch=FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name= "id_departement")
	private Departement departement;
	
	public Employe() {
		super();
	
	}
	
	public Departement getDepartement() {
		return departement;
	}
	
	public void setDepartement(Departement departement) {
		this.departement = departement;
	}
	@Override
	public String toString() {
		return String.format("Admin[%s]", super.toString());
	}

}
