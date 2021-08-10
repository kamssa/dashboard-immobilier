package ci.gstoreplus.entity.catalogue;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import ci.gstoreplus.entity.dashboard.shared.AbstractEntity;

@Entity
public class DetailFlashMaison extends AbstractEntity{
	private static final long serialVersionUID = 1L;
	private String libelle;
	private String description;
	private double prix;
	
	private int nbreChambre;
	private int nbreSalleEau;
	private int nbreCuisine;
	private int nbreSaleMange;
	private int nbreBuanderie;
	private int nbreTerrasse;
	@ManyToOne(fetch = FetchType.LAZY, cascade =CascadeType.MERGE)
	@JoinColumn(name = "id_FlashMaison", nullable = false)
	private FlashMaison flashMaison;
	@OneToOne(fetch = FetchType.LAZY, cascade =CascadeType.MERGE)
	@JoinColumn(name = "id_document")
	private Document document;
	public DetailFlashMaison() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DetailFlashMaison(String libelle, String description, double prix, int nbreChambre, int nbreSalleEau,
			int nbreCuisine, int nbreSaleMange, int nbreBuanderie, int nbreTerrasse, FlashMaison flashMaison,
			Document document) {
		super();
		this.libelle = libelle;
		this.description = description;
		this.prix = prix;
		this.nbreChambre = nbreChambre;
		this.nbreSalleEau = nbreSalleEau;
		this.nbreCuisine = nbreCuisine;
		this.nbreSaleMange = nbreSaleMange;
		this.nbreBuanderie = nbreBuanderie;
		this.nbreTerrasse = nbreTerrasse;
		this.flashMaison = flashMaison;
		this.document = document;
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
	public double getPrix() {
		return prix;
	}
	public void setPrix(double prix) {
		this.prix = prix;
	}
	public int getNbreChambre() {
		return nbreChambre;
	}
	public void setNbreChambre(int nbreChambre) {
		this.nbreChambre = nbreChambre;
	}
	public int getNbreSalleEau() {
		return nbreSalleEau;
	}
	public void setNbreSalleEau(int nbreSalleEau) {
		this.nbreSalleEau = nbreSalleEau;
	}
	public int getNbreCuisine() {
		return nbreCuisine;
	}
	public void setNbreCuisine(int nbreCuisine) {
		this.nbreCuisine = nbreCuisine;
	}
	public int getNbreSaleMange() {
		return nbreSaleMange;
	}
	public void setNbreSaleMange(int nbreSaleMange) {
		this.nbreSaleMange = nbreSaleMange;
	}
	public int getNbreBuanderie() {
		return nbreBuanderie;
	}
	public void setNbreBuanderie(int nbreBuanderie) {
		this.nbreBuanderie = nbreBuanderie;
	}
	public int getNbreTerrasse() {
		return nbreTerrasse;
	}
	public void setNbreTerrasse(int nbreTerrasse) {
		this.nbreTerrasse = nbreTerrasse;
	}
	public FlashMaison getFlashMaison() {
		return flashMaison;
	}
	public void setFlashMaison(FlashMaison flashMaison) {
		this.flashMaison = flashMaison;
	}
	public Document getDocument() {
		return document;
	}
	public void setDocument(Document document) {
		this.document = document;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((document == null) ? 0 : document.hashCode());
		result = prime * result + ((flashMaison == null) ? 0 : flashMaison.hashCode());
		result = prime * result + ((libelle == null) ? 0 : libelle.hashCode());
		result = prime * result + nbreBuanderie;
		result = prime * result + nbreChambre;
		result = prime * result + nbreCuisine;
		result = prime * result + nbreSaleMange;
		result = prime * result + nbreSalleEau;
		result = prime * result + nbreTerrasse;
		long temp;
		temp = Double.doubleToLongBits(prix);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		DetailFlashMaison other = (DetailFlashMaison) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (document == null) {
			if (other.document != null)
				return false;
		} else if (!document.equals(other.document))
			return false;
		if (flashMaison == null) {
			if (other.flashMaison != null)
				return false;
		} else if (!flashMaison.equals(other.flashMaison))
			return false;
		if (libelle == null) {
			if (other.libelle != null)
				return false;
		} else if (!libelle.equals(other.libelle))
			return false;
		if (nbreBuanderie != other.nbreBuanderie)
			return false;
		if (nbreChambre != other.nbreChambre)
			return false;
		if (nbreCuisine != other.nbreCuisine)
			return false;
		if (nbreSaleMange != other.nbreSaleMange)
			return false;
		if (nbreSalleEau != other.nbreSalleEau)
			return false;
		if (nbreTerrasse != other.nbreTerrasse)
			return false;
		if (Double.doubleToLongBits(prix) != Double.doubleToLongBits(other.prix))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "DetailFashMaison [libelle=" + libelle + ", description=" + description + ", prix=" + prix
				+ ", nbreChambre=" + nbreChambre + ", nbreSalleEau=" + nbreSalleEau + ", nbreCuisine=" + nbreCuisine
				+ ", nbreSaleMange=" + nbreSaleMange + ", nbreBuanderie=" + nbreBuanderie + ", nbreTerrasse="
				+ nbreTerrasse + ", flashMaison=" + flashMaison + ", document=" + document + "]";
	}
	
	
}
