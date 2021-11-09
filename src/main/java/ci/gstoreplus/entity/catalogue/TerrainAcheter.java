package ci.gstoreplus.entity.catalogue;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import ci.gstoreplus.entity.dashboard.shared.AbstractEntity;
import ci.gstoreplus.entity.dashboard.shared.Personne;

@Entity
public class TerrainAcheter extends AbstractEntity{
	
	private static final long serialVersionUID = 1L;
	@OneToOne(fetch = FetchType.LAZY, cascade =CascadeType.MERGE)
	private DetailTerrain detailTerrain;
	@OneToOne(fetch = FetchType.LAZY, cascade =CascadeType.MERGE)
	private Personne personne;
	public TerrainAcheter() {
		super();
	
	}
	
	public TerrainAcheter(DetailTerrain detailTerrain, Personne personne) {
		super();
		this.detailTerrain = detailTerrain;
		this.personne = personne;
	}

	public DetailTerrain getDetailTerrain() {
		return detailTerrain;
	} 
	public void setDetailTerrain(DetailTerrain detailTerrain) {
		this.detailTerrain = detailTerrain;
	}
	public Personne getPersonne() {
		return personne;
	}
	public void setPersonne(Personne personne) {
		this.personne = personne;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((detailTerrain == null) ? 0 : detailTerrain.hashCode());
		result = prime * result + ((personne == null) ? 0 : personne.hashCode());
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
		TerrainAcheter other = (TerrainAcheter) obj;
		if (detailTerrain == null) {
			if (other.detailTerrain != null)
				return false;
		} else if (!detailTerrain.equals(other.detailTerrain))
			return false;
		if (personne == null) {
			if (other.personne != null)
				return false;
		} else if (!personne.equals(other.personne))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TerrainAcheter [detailTerrain=" + detailTerrain + ", personne=" + personne + "]";
	}
	
	
}
