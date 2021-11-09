package ci.gstoreplus.entity.catalogue;

import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import ci.gstoreplus.entity.dashboard.shared.AbstractEntity;
import ci.gstoreplus.entity.dashboard.shared.Personne;

@Entity
public class TerrainVendu extends AbstractEntity{
    private static final long serialVersionUID = 1L;
	private String  libelle;
	private boolean paye;
	private boolean abonneGeo; 
	private String  unite;
	private String note;
	private String prixParMettreCarre;
	private String superficie;
	private String surfaceUtilise;
	private String description;
	private double latitude;
	private double longitude;
	private String numero;
	private double prix;
	private String path;
	private String nomVille;
	private String typeDocument;
	@ManyToOne(fetch = FetchType.LAZY, cascade =CascadeType.MERGE)
	private Personne personne;
	public TerrainVendu() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TerrainVendu(String libelle, boolean paye, boolean abonneGeo, String unite, String note,
			String prixParMettreCarre, String superficie, String surfaceUtilise, String description, double latitude,
			double longitude, String numero, double prix, String path, String nomVille, String typeDocument,
			Personne personne) {
		super();
		this.libelle = libelle;
		this.paye = paye;
		this.abonneGeo = abonneGeo;
		this.unite = unite;
		this.note = note;
		this.prixParMettreCarre = prixParMettreCarre;
		this.superficie = superficie;
		this.surfaceUtilise = surfaceUtilise;
		this.description = description;
		this.latitude = latitude;
		this.longitude = longitude;
		this.numero = numero;
		this.prix = prix;
		this.path = path;
		this.nomVille = nomVille;
		this.typeDocument = typeDocument;
		this.personne = personne;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public boolean isPaye() {
		return paye;
	}
	public void setPaye(boolean paye) {
		this.paye = paye;
	}
	public boolean isAbonneGeo() {
		return abonneGeo;
	}
	public void setAbonneGeo(boolean abonneGeo) {
		this.abonneGeo = abonneGeo;
	}
	public String getUnite() {
		return unite;
	}
	public void setUnite(String unite) {
		this.unite = unite;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getPrixParMettreCarre() {
		return prixParMettreCarre;
	}
	public void setPrixParMettreCarre(String prixParMettreCarre) {
		this.prixParMettreCarre = prixParMettreCarre;
	}
	public String getSuperficie() {
		return superficie;
	}
	public void setSuperficie(String superficie) {
		this.superficie = superficie;
	}
	public String getSurfaceUtilise() {
		return surfaceUtilise;
	}
	public void setSurfaceUtilise(String surfaceUtilise) {
		this.surfaceUtilise = surfaceUtilise;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public String getNumero() {
		return numero;
	}
	@PrePersist
	@PreUpdate
	public void setNumero() {
		this.numero = UUID.randomUUID().toString();
	}
	public double getPrix() {
		return prix;
	}
	public void setPrix(double prix) {
		this.prix = prix;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getNomVille() {
		return nomVille;
	}
	public void setNomVille(String nomVille) {
		this.nomVille = nomVille;
	}
	public String getTypeDocument() {
		return typeDocument;
	}
	public void setTypeDocument(String typeDocument) {
		this.typeDocument = typeDocument;
	}
	public Personne getPersonne() {
		return personne;
	}
	public void setPersonne(Personne personne) {
		this.personne = personne;
	}

	
}
