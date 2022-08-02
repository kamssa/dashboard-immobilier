package ci.gstoreplus.entity.catalogue;

import java.math.BigDecimal;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;



@Entity
@DiscriminatorValue("MA")
public class Maison extends Produit {

	private static final long serialVersionUID = 1L;
	private String libelle;
	private String description;
	private BigDecimal prix;
	private String superficie;
    private Integer nbreChambre;
	private Integer nbreSalleEau;
	private Integer nbreCuisine;
	private Integer nbreSaleMange;
	private Integer nbreBuanderie;
	private Integer nbreTerrasse;
	private String situationGeographique;
	private String maisonType;
	private String typeVente;

	public Maison() {
		super();
	}

	public Maison(String libelle, String description, Ville ville, String type) {
		super(libelle, description, ville, type);
	}

	public Maison(String libelle, String description, BigDecimal prix, String superficie, Integer nbreChambre,
			Integer nbreSalleEau, Integer nbreCuisine, Integer nbreSaleMange, Integer nbreBuanderie,
			Integer nbreTerrasse, String situationGeographique, String maisonType, String typeVente) {
		super();
		this.libelle = libelle;
		this.description = description;
		this.prix = prix;
		this.superficie = superficie;
		this.nbreChambre = nbreChambre;
		this.nbreSalleEau = nbreSalleEau;
		this.nbreCuisine = nbreCuisine;
		this.nbreSaleMange = nbreSaleMange;
		this.nbreBuanderie = nbreBuanderie;
		this.nbreTerrasse = nbreTerrasse;
		this.situationGeographique = situationGeographique;
		this.maisonType = maisonType;
		this.typeVente = typeVente;
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

	public BigDecimal getPrix() {
		return prix;
	}

	public void setPrix(BigDecimal prix) {
		this.prix = prix;
	}

	public String getSuperficie() {
		return superficie;
	}

	public void setSuperficie(String superficie) {
		this.superficie = superficie;
	}

	public Integer getNbreChambre() {
		return nbreChambre;
	}

	public void setNbreChambre(Integer nbreChambre) {
		this.nbreChambre = nbreChambre;
	}

	public Integer getNbreSalleEau() {
		return nbreSalleEau;
	}

	public void setNbreSalleEau(Integer nbreSalleEau) {
		this.nbreSalleEau = nbreSalleEau;
	}

	public Integer getNbreCuisine() {
		return nbreCuisine;
	}

	public void setNbreCuisine(Integer nbreCuisine) {
		this.nbreCuisine = nbreCuisine;
	}

	public Integer getNbreSaleMange() {
		return nbreSaleMange;
	}

	public void setNbreSaleMange(Integer nbreSaleMange) {
		this.nbreSaleMange = nbreSaleMange;
	}

	public Integer getNbreBuanderie() {
		return nbreBuanderie;
	}

	public void setNbreBuanderie(Integer nbreBuanderie) {
		this.nbreBuanderie = nbreBuanderie;
	}

	public Integer getNbreTerrasse() {
		return nbreTerrasse;
	}

	public void setNbreTerrasse(Integer nbreTerrasse) {
		this.nbreTerrasse = nbreTerrasse;
	}

	public String getSituationGeographique() {
		return situationGeographique;
	}

	public void setSituationGeographique(String situationGeographique) {
		this.situationGeographique = situationGeographique;
	}

	public String getMaisonType() {
		return maisonType;
	}

	public void setMaisonType(String maisonType) {
		this.maisonType = maisonType;
	}

	public String getTypeVente() {
		return typeVente;
	}

	public void setTypeVente(String typeVente) {
		this.typeVente = typeVente;
	}

	
	}
