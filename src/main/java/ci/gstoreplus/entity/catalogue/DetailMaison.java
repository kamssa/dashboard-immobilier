package ci.gstoreplus.entity.catalogue;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import ci.gstoreplus.entity.dashboard.shared.AbstractEntity;

@Entity
public class DetailMaison extends AbstractEntity{
	private static final long serialVersionUID = 1L;
	private String description;
	@ManyToOne(fetch = FetchType.LAZY, cascade =CascadeType.MERGE)
	@JoinColumn(name = "id_Maison", nullable = false)
	private Maison maison;
	private int nbreChambre;
	private int nbreSalleEau;
	private int nbreCuisine;
	private int nbreSaleMange;
	private int nbreBuanderie;
	private int nbreTerrasse;
	
	public DetailMaison() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public DetailMaison(String description, Maison maison, int nbreChambre, int nbreSalleEau, int nbreCuisine,
			int nbreSaleMange, int nbreBuanderie, int nbreTerrasse) {
		super();
		this.description = description;
		this.maison = maison;
		this.nbreChambre = nbreChambre;
		this.nbreSalleEau = nbreSalleEau;
		this.nbreCuisine = nbreCuisine;
		this.nbreSaleMange = nbreSaleMange;
		this.nbreBuanderie = nbreBuanderie;
		this.nbreTerrasse = nbreTerrasse;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Maison getMaison() {
		return maison;
	}
	public void setMaison(Maison maison) {
		this.maison = maison;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((maison == null) ? 0 : maison.hashCode());
		result = prime * result + nbreBuanderie;
		result = prime * result + nbreChambre;
		result = prime * result + nbreCuisine;
		result = prime * result + nbreSaleMange;
		result = prime * result + nbreSalleEau;
		result = prime * result + nbreTerrasse;
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
		DetailMaison other = (DetailMaison) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (maison == null) {
			if (other.maison != null)
				return false;
		} else if (!maison.equals(other.maison))
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
		return true;
	}

	@Override
	public String toString() {
		return "DetailMaison [description=" + description + ", maison=" + maison + ", nbreChambre=" + nbreChambre
				+ ", nbreSalleEau=" + nbreSalleEau + ", nbreCuisine=" + nbreCuisine + ", nbreSaleMange=" + nbreSaleMange
				+ ", nbreBuanderie=" + nbreBuanderie + ", nbreTerrasse=" + nbreTerrasse + "]";
	}
	
}
