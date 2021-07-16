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
	private DetailTerrain detailTerrain;
	@OneToOne(fetch = FetchType.LAZY, cascade =CascadeType.MERGE)
	private Personne personne;
	public TerrainAcheter() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
