package ci.gstoreplus.entity.dashboard.shared;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import ci.gstoreplus.entity.dashboard.admin.Departement;
import ci.gstoreplus.entity.dashboard.admin.Employe;

@Entity
public class Besoin extends AbstractEntity{
 private String libelle;
 private double montant;
 @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "id_Departement")
	private Departement depaterment;
public Besoin() {
	super();
	// TODO Auto-generated constructor stub
}
public Besoin(String libelle, double montant, Departement depaterment) {
	super();
	this.libelle = libelle;
	this.montant = montant;
	this.depaterment = depaterment;
}
public String getLibelle() {
	return libelle;
}
public void setLibelle(String libelle) {
	this.libelle = libelle;
}
public double getMontant() {
	return montant;
}
public void setMontant(double montant) {
	this.montant = montant;
}
public Departement getDepaterment() {
	return depaterment;
}
public void setDepaterment(Departement depaterment) {
	this.depaterment = depaterment;
}
 
}
