package ci.gstoreplus.entity.dashboard.admin;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

import ci.gstoreplus.entity.dashboard.shared.AbstractEntity;

@Entity
public class Departement extends AbstractEntity{
	
	private static final long serialVersionUID = 1L;
    private String libelle;
	private String description;
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "id_Entreprise")
	private Entrepise entreprise;
	
	
	public Departement() {
		super();
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


	public Entrepise getEntreprise() {
		return entreprise;
	}


	public void setEntreprise(Entrepise entreprise) {
		this.entreprise = entreprise;
	}
	
}
