package ci.gstoreplus.entity.catalogue;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import ci.gstoreplus.entity.dashboard.shared.AbstractEntity;

@Entity
public class DetailTerrain  extends AbstractEntity{

	private static final long serialVersionUID = 1L;
	private String description;
	@OneToOne(fetch = FetchType.LAZY, cascade =CascadeType.MERGE)
	@JoinColumn(name = "id_Terrain", nullable = false)
	private Terrain terrain;
	
	
	public DetailTerrain(Terrain terrain) {
		super();
		this.terrain = terrain;
		
	}
	
	public DetailTerrain(String description, Terrain terrain) {
		super();
		this.description = description;
		this.terrain = terrain;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public DetailTerrain() {
		super();
	}
	public Terrain getTerrain() {
		return terrain;
	}
	public void setTerrain(Terrain terrain) {
		this.terrain = terrain;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((terrain == null) ? 0 : terrain.hashCode());
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
		DetailTerrain other = (DetailTerrain) obj;
		if (terrain == null) {
			if (other.terrain != null)
				return false;
		} else if (!terrain.equals(other.terrain))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "DetailTerrain [terrain=" + terrain + "]";
	}
	
}
