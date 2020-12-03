package ci.gstoreplus.entity.client;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import ci.gstoreplus.entity.dashboard.shared.Personne;

@Entity
@DiscriminatorValue("AB")
public class Abonne extends Personne{

}
