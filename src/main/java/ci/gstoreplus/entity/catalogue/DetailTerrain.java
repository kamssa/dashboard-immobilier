package ci.gstoreplus.entity.catalogue;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import ci.gstoreplus.entity.dashboard.shared.AbstractEntity;

@Entity
public class DetailTerrain  extends AbstractEntity{

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
	@ManyToOne(fetch = FetchType.LAZY, cascade =CascadeType.MERGE)
	@JoinColumn(name = "id_Terrain", nullable = false)
	private Terrain terrain;
	@OneToOne(fetch = FetchType.LAZY, cascade =CascadeType.MERGE)
	@JoinColumn(name = "id_document")
	private Document document;
	
	public DetailTerrain() {
		super();
		// TODO Auto-generated constructor stub
	}
 
	public DetailTerrain(Terrain terrain) {
		super();
		this.terrain = terrain;
		
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

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
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

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public Terrain getTerrain() {
		return terrain;
	}

	public void setTerrain(Terrain terrain) {
		this.terrain = terrain;
	}

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	
	
	
}
