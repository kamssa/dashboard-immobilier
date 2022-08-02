package ci.gstoreplus.entity.catalogue;

import java.math.BigDecimal;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("FT")
@Data @AllArgsConstructor @NoArgsConstructor
public class FlashTerrain extends Produit{
	
	private static final long serialVersionUID = 1L;
	private String surfaceUtile;
	private String surfaceTerrain;
    private String situationGeographique;
    private String flashmaisonType;
    private BigDecimal prix;
   
}
