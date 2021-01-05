package ci.gstoreplus.entity.catalogue;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

import ci.gstoreplus.entity.dashboard.shared.AbstractEntity;
import ci.gstoreplus.entity.dashboard.shared.Personne;

@Entity
public class TerrainAcheter extends AbstractEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@OneToOne(fetch = FetchType.LAZY, cascade =CascadeType.MERGE)
	private Produit produits;
	@OneToOne(fetch = FetchType.LAZY, cascade =CascadeType.MERGE)
	private Personne personne;
	public TerrainAcheter() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TerrainAcheter(Produit produits, Personne personne) {
		super();
		this.produits = produits;
		this.personne = personne;
	}
	public Produit getTerrains() {
		return produits;
	}
	public void setTerrains(Produit produits) {
		this.produits = produits;
	}
	public Personne getPersonne() {
		return personne;
	}
	public void setPersonne(Personne personne) {
		this.personne = personne;
	}

}
