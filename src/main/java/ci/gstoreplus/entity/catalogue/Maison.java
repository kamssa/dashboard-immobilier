package ci.gstoreplus.entity.catalogue;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;



@Entity
@DiscriminatorValue("MA")
public class Maison extends Produit {

	private static final long serialVersionUID = 1L;
	private String surfaceUtile;
	private String surfaceTerrain;
    private String situationGeographique;
    private String typeMaison;
	public Maison() {
		super();
	}
	
  public String getSituationGeographique() {
		return situationGeographique;
	}
	public void setSituationGeographique(String situationGeographique) {
		this.situationGeographique = situationGeographique;
	}
	
	

	public String getTypeMaison() {
		return typeMaison;
	}

	public void setTypeMaison(String typeMaison) {
		this.typeMaison = typeMaison;
	}

	public String getSurfaceUtile() {
		return surfaceUtile;
	}

	public void setSurfaceUtile(String surfaceUtile) {
		this.surfaceUtile = surfaceUtile;
	}

	public String getSurfaceTerrain() {
		return surfaceTerrain;
	}

	public void setSurfaceTerrain(String surfaceTerrain) {
		this.surfaceTerrain = surfaceTerrain;
	}
	
    
	}
