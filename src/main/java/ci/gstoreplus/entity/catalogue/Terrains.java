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
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import ci.gstoreplus.entity.dashboard.shared.AbstractEntity;
import ci.gstoreplus.entity.dashboard.shared.Personne;

@Entity
@Table(name = "T_Terrains")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "TYPE_TERRAIN", discriminatorType = DiscriminatorType.STRING, length = 2)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({ 
	@Type(name = "TT", value = TopTerrain.class),
	@Type(name = "FT", value = FlashTerrain.class),
	@Type(name = "TE", value = Terrain.class)
	
})
public class Terrains extends AbstractEntity{
	private static final long serialVersionUID = 1L;
	private String libelle;
	private String description;
	private double prix;
	private String path;
	private double latitude;
	private double longitude;
	private String numero;
	@ManyToOne(fetch = FetchType.LAZY, cascade =CascadeType.MERGE)
	@JoinColumn(name = "id_categorie")
	private Categorie categorie;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade =CascadeType.MERGE)
	@JoinColumn(name = "id_ville")
	private  Ville ville;
	private boolean paye;
	private boolean abonneGeo; 
	private String  unite;
	private String note;
	@Column(name = "id_categorie", insertable = false, updatable = false)
	private long idCategorie;
	
	@Column(name = "id_ville", insertable = false, updatable = false)
	private long idVille;
	
	
	@Column(name = "TYPE_TERRAIN", insertable = false, updatable = false)
	private String type;
	public Terrains() {
		super();
	}
	
	public Terrains(String libelle, String description, double prix, Categorie categorie) {
		super();
		this.libelle = libelle;
		this.description = description;
		this.prix = prix;
		this.categorie = categorie;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Categorie getCategorie() {
		return categorie;
	}
	
	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
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
	public double getPrix() {
		return prix;
	}
	public void setPrix(double prix) {
		this.prix = prix;
	}

	public long getIdCategorie() {
		return idCategorie;
	}

	
	
	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public boolean isPaye() {
		return paye;
	}

	public void setPaye(boolean paye) {
		this.paye = paye;
	}

	public boolean isAbonneGeo() {
		return abonneGeo;
	}

	public void setAbonneGeo(boolean abonneGeo) {
		this.abonneGeo = abonneGeo;
	}

	public Ville getVille() {
		return ville;
	}

	public void setVille(Ville ville) {
		this.ville = ville;
	}

	public long getIdVille() {
		return idVille;
	}

	public String getType() {
		return type;
	}

	public String getUnite() {
		return unite;
	}

	public void setUnite(String unite) {
		this.unite = unite;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero() {
		this.numero = "T"+ (Math.random() * (10));
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public String toString() {
		return "Terrain [libelle=" + libelle + ", description=" + description + ", prix=" + prix + ", categorie="
				+ categorie + "]";
	}

}