package ci.gstoreplus.entity.catalogue;

import javax.persistence.Entity;

import ci.gstoreplus.entity.dashboard.shared.AbstractEntity;

@Entity
public class Ville extends AbstractEntity{

	private static final long serialVersionUID = 1L;
	private String libelle;
	public Ville() {
		super();
	}
	public Ville(String libelle) {
		super();
		this.libelle = libelle;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	@Override
	public String toString() {
		return "Ville [libelle=" + libelle + "]";
	}

}
