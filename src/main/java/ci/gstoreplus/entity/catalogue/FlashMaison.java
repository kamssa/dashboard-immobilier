package ci.gstoreplus.entity.catalogue;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("FM")
public class FlashMaison extends Produit{

	private static final long serialVersionUID = 1L;

}
