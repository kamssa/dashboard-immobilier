package ci.gstoreplus.entity.catalogue;

import javax.persistence.Entity;

import ci.gstoreplus.entity.dashboard.shared.AbstractEntity;

@Entity
public class Maison extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String libelle;
	private String description;
	
	public Maison() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Maison(String libelle, String description) {
		super();
		this.libelle = libelle;
		this.description = description;
	}

	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	

}
