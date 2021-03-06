package ci.gstoreplus.entity.dashboard.admin;

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
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name= "id_departement")
	private Departement departement;
	// clé étrangère
	 @Column(name = "id_departement", insertable = false, updatable = false)
	 private long idDepartement;
	public Employe() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Departement getDepartement() {
		return departement;
	}
	public long getIdDepartement() {
		return idDepartement;
	}
	public void setDepartement(Departement departement) {
		this.departement = departement;
	}
	@Override
	public String toString() {
		return String.format("Admin[%s]", super.toString());
	}

}
