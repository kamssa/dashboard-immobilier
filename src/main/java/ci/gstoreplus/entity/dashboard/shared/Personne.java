package ci.gstoreplus.entity.dashboard.shared;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.NaturalId;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import ci.gstoreplus.entity.client.Abonne;
import ci.gstoreplus.entity.client.Client;
import ci.gstoreplus.entity.client.Prospect;
import ci.gstoreplus.entity.dashboard.admin.Admin;
import ci.gstoreplus.entity.dashboard.admin.Employe;
import ci.gstoreplus.entity.dashboard.admin.Manager;


@Entity
@Table(name = "T_Personnes")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "TYPE_PERSONNE", discriminatorType = DiscriminatorType.STRING, length = 2)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({ 
	@Type(name = "AD", value = Admin.class),
	@Type(name = "MA", value = Manager.class),
	@Type(name = "EM", value = Employe.class), 
	@Type(name = "AB", value = Abonne.class),
	@Type(name = "CL", value = Client.class),
	
})
public class Personne extends AbstractEntity {

	private static final long serialVersionUID = 1L;
	private String titre;
	@NotBlank
	@Size(max = 40)
	private String nom;

	@NotBlank
	@Size(max = 15)
	private String prenom;

	@NaturalId(mutable=true)
	@NotBlank
	@Size(max = 40)
	@Email
	private String email;
	private String numCni;
	private String numPassport;
	private String codePays;
	private String telephone;
	@NotBlank
	@Size(max = 100)
	private String password;
	private String fonction;
	private String nomComplet;

	@Embedded
	private Adresse adresse;
	@Column(name = "actived")
	private boolean actived;
	@Column(name = "desactiver")
	private Boolean desactiver;
	@Column(name = "TYPE_PERSONNE", insertable = false, updatable = false)
	private String type;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();
	 
	    
	public Personne() {
		super();
		 this.actived=false;
		 this.desactiver = false;
	}

	public Personne(@NotBlank @Size(max = 40) String nom, @NotBlank @Size(max = 15) String prenom,
			@NotBlank @Size(max = 40) @Email String email, @NotBlank @Size(max = 100) String password) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.password = password;
	}

	

	public Personne(Long id, @NotBlank @Size(max = 40) String nom, @NotBlank @Size(max = 15) String prenom,
			@NotBlank @Size(max = 40) @Email String email, @NotBlank @Size(max = 100) String password, String fonction,
			Set<Role> roles) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.password = password;
		this.fonction = fonction;
		this.roles = roles;
	}

	public Personne(Long id, @NotBlank @Size(max = 40) String nom, @NotBlank @Size(max = 15) String prenom,
			@NotBlank @Size(max = 40) @Email String email, @NotBlank @Size(max = 100) String password, String fonction,
			String type, Adresse adresse, Set<Role> roles) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.password = password;
		this.fonction = fonction;
		this.type = type;
		this.adresse = adresse;

		this.roles = roles;
	}

	public Personne(String titre, @NotBlank @Size(max = 40) String nom, @NotBlank @Size(max = 15) String prenom,
			@NotBlank @Size(max = 40) @Email String email, String codePays, String telephone,
			@NotBlank @Size(max = 100) String password, String fonction, String nomComplet, Adresse adresse,
			boolean actived, String type, Set<Role> roles) {
		super();
		this.titre = titre;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.codePays = codePays;
		this.telephone = telephone;
		this.password = password;
		this.fonction = fonction;
		this.nomComplet = nomComplet;
		this.adresse = adresse;
		this.actived = actived;
		this.type = type;
		this.roles = roles;
	}

	public String getNumPassport() {
		return numPassport;
	}

	public void setNumPassport(String numPassport) {
		this.numPassport = numPassport;
	}

	

	public Boolean getDesactiver() {
		return desactiver;
	}

	public void setDesactiver(Boolean desactiver) {
		this.desactiver = desactiver;
	}

	public String getNumCni() {
		return numCni;
	}

	public void setNumCni(String numCni) {
		this.numCni = numCni;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFonction() {
		return fonction;
	}

	public void setFonction(String fonction) {
		this.fonction = fonction;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Long getId() {
		return id;
	}

	public Long getVersion() {
		return version;
	}

	public String getNomComplet() {
		return nomComplet;
	}

	

	public boolean isActived() {
		return actived;
	}

	public void setActived(boolean actived) {
		this.actived = actived;
	}

	@PrePersist
	@PreUpdate
	public void setNomComplet() {
		this.nomComplet = nom + " " + prenom;
	}

	public void setNomComplet(String nomComplet) {
		this.nomComplet = nomComplet;
	}

	@Override
	public String toString() {
		return "Personne [id=" + id + ", version=" + version + ", nom=" + nom + ", prenom=" + prenom + ", email="
				+ email + ", password=" + password + ", fonction=" + fonction + ", type=" + type + ", adresse="
				+ adresse + ", roles=" + roles + "]";
	}

}
