package ci.gstoreplus.entity.catalogue;

import ci.gstoreplus.entity.dashboard.shared.AbstractEntity;

public class TypeMaison extends AbstractEntity{
private String libelle;
private String description;

public TypeMaison() {
	super();
	// TODO Auto-generated constructor stub
}

public String getLibelle() {
	return libelle;
}

public void setLibelle(String libelle) {
	this.libelle = libelle;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}

}
