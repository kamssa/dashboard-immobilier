package ci.gstoreplus.entity.dashboard.admin;

import javax.persistence.Entity;

import ci.gstoreplus.entity.dashboard.shared.AbstractEntity;

@Entity
public class Entreprise extends AbstractEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nom;
	private String description;
	public Entreprise() {
		super();
	}
	
	public Entreprise(String nom) {
		super();
		this.nom = nom;
	}

	public Entreprise(String nom, String description) {
		super();
		this.nom = nom;
		this.description = description;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Entreprise [nom=" + nom + ", description=" + description + "]";
	}
	
}
