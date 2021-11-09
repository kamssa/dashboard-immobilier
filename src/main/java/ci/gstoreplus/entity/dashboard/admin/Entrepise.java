package ci.gstoreplus.entity.dashboard.admin;

import javax.persistence.Entity;

import ci.gstoreplus.entity.dashboard.shared.AbstractEntity;

@Entity
public class Entrepise extends AbstractEntity{
	private String nom;
	private String description;
	public Entrepise() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Entrepise(String nom, String description) {
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
	
}
