package ci.gstoreplus.entity.combo;

import ci.gstoreplus.entity.dashboard.shared.AbstractEntity;

public class Villes extends AbstractEntity{
private String nom;
private String description;

private Pays pays;
public Villes() {
	super();
	// TODO Auto-generated constructor stub
}

public Villes(String nom, String description) {
	super();
	this.nom = nom;
	this.description = description;
}

public String getNom() {
	return nom;
}
public void setNom(String nom) {
	this.nom = nom;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}

}
