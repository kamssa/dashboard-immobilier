package ci.gstoreplus.entity.catalogue;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import ci.gstoreplus.entity.dashboard.shared.AbstractEntity;

@Entity
@Table(name = "T_Produit")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "TYPE_PRODUIT", discriminatorType = DiscriminatorType.STRING, length = 2)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({ 
	@Type(name = "TE", value = Terrain.class),
	@Type(name = "MA", value = Maison.class),
	@Type(name = "FT", value = FlashTerrain.class),
	
	
})
public class Produit extends AbstractEntity{
	private static final long serialVersionUID = 1L;
	private String libelle;
	@Column(columnDefinition="TEXT")
	private String description;
	@ManyToOne(fetch = FetchType.LAZY, cascade =CascadeType.MERGE)
	@JoinColumn(name = "id_ville")
	private  Ville ville;
	@OneToOne(fetch = FetchType.LAZY, cascade =CascadeType.MERGE)
	@JoinColumn(name = "id_document")
	private Document document;
	
	@Column(name = "TYPE_PRODUIT", insertable = false, updatable = false)
	private String type;
	public Produit() {
		super();
	} 
	
	public Produit(String libelle, String description, Ville ville, String type) {
		super();
		this.libelle = libelle;
		this.description = description;
		this.ville = ville;
		this.type = type;
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
	
	
	public Ville getVille() {
		return ville;
	}
	public void setVille(Ville ville) {
		this.ville = ville;
	}
	
	public String getType() {
		return type;
	}

	@Override
	public String toString() {
		return "Produit [libelle=" + libelle + ", description=" + description + ", ville=" + ville + ", type=" + type
				+ "]";
	}
	
	
	
}
