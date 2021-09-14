package ci.gstoreplus.entity.catalogue;

import javax.persistence.Entity;

import ci.gstoreplus.entity.dashboard.shared.AbstractEntity;

@Entity
public class TypeMaison extends AbstractEntity{

private static final long serialVersionUID = 1L;
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
