package ci.gstoreplus.entity.catalogue;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

import ci.gstoreplus.entity.dashboard.shared.AbstractEntity;
import ci.gstoreplus.entity.dashboard.shared.Personne;

@Entity
public class Demande extends AbstractEntity{

private static final long serialVersionUID = 1L;
@OneToOne(fetch = FetchType.LAZY, cascade =CascadeType.MERGE)
private Produit terrains;
@OneToOne(fetch = FetchType.LAZY, cascade =CascadeType.MERGE)
private Personne personne;

public Demande() {
	super();
	
}

public Demande(Produit terrains, Personne personne) {
	super();
	this.terrains = terrains;
	this.personne = personne;
}

public Produit getTerrains() {
	return terrains;
}

public void setTerrains(Produit terrains) {
	this.terrains = terrains;
}

public Personne getPersonne() {
	return personne;
}

public void setPersonne(Personne personne) {
	this.personne = personne;
}


}
