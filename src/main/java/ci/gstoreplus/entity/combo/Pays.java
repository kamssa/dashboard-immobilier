package ci.gstoreplus.entity.combo;

import javax.persistence.Entity;

import ci.gstoreplus.entity.dashboard.shared.AbstractEntity;

@Entity
public class Pays extends AbstractEntity{
private String nom;
private String descrition;


public Pays() {
	super();
	// TODO Auto-generated constructor stub
}

public Pays(String nom, String descrition) {
	super();
	this.nom = nom;
	this.descrition = descrition;
}

public String getNom() {
	return nom;
}
public void setNom(String nom) {
	this.nom = nom;
}
public String getDescrition() {
	return descrition;
}
public void setDescrition(String descrition) {
	this.descrition = descrition;
}

@Override
public int hashCode() {
	final int prime = 31;
	int result = super.hashCode();
	result = prime * result + ((descrition == null) ? 0 : descrition.hashCode());
	result = prime * result + ((nom == null) ? 0 : nom.hashCode());
	return result;
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (!super.equals(obj))
		return false;
	if (getClass() != obj.getClass())
		return false;
	Pays other = (Pays) obj;
	if (descrition == null) {
		if (other.descrition != null)
			return false;
	} else if (!descrition.equals(other.descrition))
		return false;
	if (nom == null) {
		if (other.nom != null)
			return false;
	} else if (!nom.equals(other.nom))
		return false;
	return true;
}

@Override
public String toString() {
	return "Pays [nom=" + nom + ", descrition=" + descrition + "]";
}

}
