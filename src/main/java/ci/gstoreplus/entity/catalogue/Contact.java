package ci.gstoreplus.entity.catalogue;

import javax.persistence.Entity;

import ci.gstoreplus.entity.dashboard.shared.AbstractEntity;

@Entity
public class Contact extends AbstractEntity{

	
	private static final long serialVersionUID = 1L;
    private String lieu;
    private String boitePostal;
    private String telephone;
	public Contact() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Contact(String lieu, String boitePostal, String telephone) {
		super();
		this.lieu = lieu;
		this.boitePostal = boitePostal;
		this.telephone = telephone;
	}
	public String getLieu() {
		return lieu;
	}
	public void setLieu(String lieu) {
		this.lieu = lieu;
	}
	public String getBoitePostal() {
		return boitePostal;
	}
	public void setBoitePostal(String boitePostal) {
		this.boitePostal = boitePostal;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
    
}
