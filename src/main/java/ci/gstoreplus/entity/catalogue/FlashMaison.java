package ci.gstoreplus.entity.catalogue;

import java.math.BigDecimal;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("FM")
public class FlashMaison extends Produit{

	private static final long serialVersionUID = 1L;
	private String surfaceUtile;
	private String surfaceTerrain;
    private String situationGeographique;
    private String flashmaisonType;
    private BigDecimal prix;
    
	public FlashMaison() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public FlashMaison(String surfaceUtile, String surfaceTerrain, String situationGeographique, String flashmaisonType,
			BigDecimal prix) {
		super();
		this.surfaceUtile = surfaceUtile;
		this.surfaceTerrain = surfaceTerrain;
		this.situationGeographique = situationGeographique;
		this.flashmaisonType = flashmaisonType;
		this.prix = prix;
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
	public String getSituationGeographique() {
		return situationGeographique;
	}
	public void setSituationGeographique(String situationGeographique) {
		this.situationGeographique = situationGeographique;
	}
	
	public BigDecimal getPrix() {
		return prix;
	}
	public void setPrix(BigDecimal prix) {
		this.prix = prix;
	}

}
