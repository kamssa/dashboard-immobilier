 package ci.gstoreplus.entity.catalogue;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import ci.gstoreplus.entity.client.Client;
import ci.gstoreplus.entity.dashboard.shared.AbstractEntity;
import ci.gstoreplus.entity.dashboard.shared.Personne;

@Entity
 public class Versement extends AbstractEntity{

	
	private static final long serialVersionUID = 1L;
	private BigDecimal montantVerse;
	private BigDecimal resteapaye;
	private LocalDateTime date;
	@ManyToOne(fetch = FetchType.LAZY, cascade =CascadeType.MERGE)
	private TerrainVendu terrainVendu;
	@ManyToOne(fetch = FetchType.LAZY, cascade =CascadeType.MERGE)
	private Personne personne;
	
	
	public Versement() {
		super();
	}
	public Versement(BigDecimal montantVerse, BigDecimal resteapaye, LocalDateTime date, TerrainVendu terrainVendu,
			Personne personne) {
		super();
		this.montantVerse = montantVerse;
		this.resteapaye = resteapaye;
		this.date = date;
		this.terrainVendu = terrainVendu;
		this.personne = personne;
	}
	public BigDecimal getMontantVerse() {
		return montantVerse;
	}
	public void setMontantVerse(BigDecimal montantVerse) {
		this.montantVerse = montantVerse;
	}
	public BigDecimal getResteapaye() {
		return resteapaye;
	}
	public void setResteapaye(BigDecimal resteapaye) {
		this.resteapaye = resteapaye;
	}
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	public TerrainVendu getTerrainVendu() {
		return terrainVendu;
	}
	public void setTerrainVendu(TerrainVendu terrainVendu) {
		this.terrainVendu = terrainVendu;
	}
	public Personne getPersonne() {
		return personne;
	}
	public void setPersonne(Personne personne) {
		this.personne = personne;
	}

}
