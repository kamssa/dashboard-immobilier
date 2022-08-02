package ci.gstoreplus.entity.catalogue;
import java.math.BigDecimal;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


@Entity
@DiscriminatorValue("TE")
public class Terrain extends Produit{

	
	private static final long serialVersionUID = 1L;
	private String  libelle;
	private String note;
	private String prixParMettreCarre;
	private String superficie;
	private String surfaceUtilise;
	private String description;
	private String numero;
	private BigDecimal prix;
	private String typeVente;
	
	public Terrain() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Terrain(String libelle, String description, Ville ville, String type) {
		super(libelle, description, ville, type);
		// TODO Auto-generated constructor stub
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
	
	
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getPrixParMettreCarre() {
		return prixParMettreCarre;
	}
	public void setPrixParMettreCarre(String prixParMettreCarre) {
		this.prixParMettreCarre = prixParMettreCarre;
	}
	public String getSuperficie() {
		return superficie;
	}
	public void setSuperficie(String superficie) {
		this.superficie = superficie;
	}
	public String getSurfaceUtilise() {
		return surfaceUtilise;
	}
	public void setSurfaceUtilise(String surfaceUtilise) {
		this.surfaceUtilise = surfaceUtilise;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public BigDecimal getPrix() {
		return prix;
	}
	public void setPrix(BigDecimal prix) {
		this.prix = prix;
	}
	public String getTypeVente() {
		return typeVente;
	}
	public void setTypeVente(String typeVente) {
		this.typeVente = typeVente;
	}
	@Override
	public String toString() {
		return "Terrain [libelle=" + libelle + ", note=" + note + ", prixParMettreCarre=" + prixParMettreCarre
				+ ", superficie=" + superficie + ", surfaceUtilise=" + surfaceUtilise + ", description=" + description
				+ ", numero=" + numero + ", prix=" + prix + ", typeVente=" + typeVente + "]";
	}
	
}
