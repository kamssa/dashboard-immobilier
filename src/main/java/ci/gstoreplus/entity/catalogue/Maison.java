package ci.gstoreplus.entity.catalogue;

import java.math.BigDecimal;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;



@Entity
@DiscriminatorValue("MA")
public class Maison extends Produit {

	private static final long serialVersionUID = 1L;
	private String surfaceUtile;
	private String surfaceTerrain;
    private String situationGeographique;
    private String maisonType;
    private BigDecimal prix;

	public Maison() {
		super();
	}
	
  public Maison(String surfaceUtile, String surfaceTerrain, String situationGeographique, String maisonType) {
		super();
		this.surfaceUtile = surfaceUtile;
		this.surfaceTerrain = surfaceTerrain;
		this.situationGeographique = situationGeographique;
		this.maisonType = maisonType;
	}

public String getSituationGeographique() {
		return situationGeographique;
	}
	public void setSituationGeographique(String situationGeographique) {
		this.situationGeographique = situationGeographique;
	}
	
	public String getMaisonType() {
		return maisonType;
	}

	public void setMaisonType(String maisonType) {
		this.maisonType = maisonType;
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

	public BigDecimal getPrix() {
		return prix;
	}

	public void setPrix(BigDecimal prix) {
		this.prix = prix;
	}

	
    
	}
