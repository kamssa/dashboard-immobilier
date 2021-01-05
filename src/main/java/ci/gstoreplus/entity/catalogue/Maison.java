package ci.gstoreplus.entity.catalogue;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("MA")
public class Maison extends Produit {

	private static final long serialVersionUID = 1L;

	}
