package ci.gstoreplus.entity.catalogue;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("TT")
public class TopTerrain extends Terrains{

	
	private static final long serialVersionUID = 1L;

}
