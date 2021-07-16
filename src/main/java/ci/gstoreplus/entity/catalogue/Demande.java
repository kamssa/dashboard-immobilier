package ci.gstoreplus.entity.catalogue;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;

import ci.gstoreplus.entity.dashboard.shared.AbstractEntity;

@Entity
public class Demande extends AbstractEntity{

private static final long serialVersionUID = 1L;
private Long produitId;
private String nomComplet;
private String email;
private String code;
private String telephone;
@ElementCollection
private List<String> selectionner;
private String message;
private String lu;


public Demande() {
	super();
	
}

public Demande(Long produitId, String nomComplet, String email, String code, String telephone,
		List<String> selectionner, String message, String lu) {
	super();
	this.produitId = produitId;
	this.nomComplet = nomComplet;
	this.email = email;
	this.code = code;
	this.telephone = telephone;
	this.selectionner = selectionner;
	this.message = message;
	this.lu = lu;
}



public String getCode() {
	return code;
}


public void setCode(String code) {
	this.code = code;
}

public String getMessage() {
	return message;
}


public void setMessage(String message) {
	this.message = message;
}



public String getNomComplet() {
	return nomComplet;
}


public void setNomComplet(String nomComplet) {
	this.nomComplet = nomComplet;
}


public String getEmail() {
	return email;
}


public void setEmail(String email) {
	this.email = email;
}


public String getTelephone() {
	return telephone;
}


public void setTelephone(String telephone) {
	this.telephone = telephone;
}

public Long getProduitId() {
	return produitId;
}

public List<String> getSelectionner() {
	return selectionner;
}

public void setSelectionner(List<String> selectionner) {
	this.selectionner = selectionner;
}

public void setProduitId(Long produitId) {
	this.produitId = produitId;
}


public String getLu() {
	return lu;
}


public void setLu(String lu) {
	this.lu = lu;
}




}
