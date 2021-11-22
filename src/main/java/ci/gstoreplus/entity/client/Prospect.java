package ci.gstoreplus.entity.client;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import ci.gstoreplus.entity.dashboard.shared.AbstractEntity;

@Entity
public class Prospect extends AbstractEntity{

	
	private static final long serialVersionUID = 1L;
	private  String nom ;
	private String  prenom ;
	private String  nomComplet ;
    private String  email ;
	private String  codePays;
	private String   telephone;
	private  String    fonction;
            
	public Prospect() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Prospect(String nom, String prenom, String email, String codePays, String telephone, String fonction,
			boolean satisfait, String preocupation) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.codePays = codePays;
		this.telephone = telephone;
		this.fonction = fonction;
		this.satisfait = satisfait;
		this.preocupation = preocupation;
	}

	@Column(name = "satisfait")
	private boolean satisfait;
	private String preocupation;
	
	public boolean isSatisfait() {
		return satisfait;
	}
	public void setSatisfait(boolean satisfait) {
		this.satisfait = satisfait;
	}
	public String getPreocupation() {
		return preocupation;
	}
	public void setPreocupation(String preocupation) {
		this.preocupation = preocupation;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCodePays() {
		return codePays;
	}
	public void setCodePays(String codePays) {
		this.codePays = codePays;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getFonction() {
		return fonction;
	}
	public void setFonction(String fonction) {
		this.fonction = fonction;
	}
	
	public String getNomComplet() {
		return nomComplet;
	}

	
	@PrePersist
	@PreUpdate
	public void setNomComplet() {
		this.nomComplet = nom + " " + prenom;
	}
	@Override
	public String toString() {
		return "Prospect [nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", codePays=" + codePays
				+ ", telephone=" + telephone + ", fonction=" + fonction + ", satisfait=" + satisfait + ", preocupation="
				+ preocupation + "]";
	}
	
}
