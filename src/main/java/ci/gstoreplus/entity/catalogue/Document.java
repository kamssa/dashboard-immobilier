package ci.gstoreplus.entity.catalogue;

import javax.persistence.Entity;

import ci.gstoreplus.entity.dashboard.shared.AbstractEntity;

@Entity
public class Document extends AbstractEntity{

	private static final long serialVersionUID = 1L;
	private String libelle;
	private String description;
	
	
	public Document() {
		super();
	}


	public Document(String libelle, String description) {
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
