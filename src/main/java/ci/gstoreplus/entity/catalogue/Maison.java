package ci.gstoreplus.entity.catalogue;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;



@Entity
@DiscriminatorValue("MA")
public class Maison extends Produit {

	private static final long serialVersionUID = 1L;
    private String nbrePieces;
    private String situationGeographique;
    private String typeChambre;
    private String nbreSalleEau;
    private TypeMaison typeMaison;
	public Maison() {
		super();
	}
	public String getNbrePieces() {
		return nbrePieces;
	}
	public void setNbrePieces(String nbrePieces) {
		this.nbrePieces = nbrePieces;
	}
	public String getSituationGeographique() {
		return situationGeographique;
	}
	public void setSituationGeographique(String situationGeographique) {
		this.situationGeographique = situationGeographique;
	}
	public String getTypeChambre() {
		return typeChambre;
	}
	public void setTypeChambre(String typeChambre) {
		this.typeChambre = typeChambre;
	}
	public String getNbreSalleEau() {
		return nbreSalleEau;
	}
	public void setNbreSalleEau(String nbreSalleEau) {
		this.nbreSalleEau = nbreSalleEau;
	}
	public TypeMaison getTypeMaison() {
		return typeMaison;
	}
	public void setTypeMaison(TypeMaison typeMaison) {
		this.typeMaison = typeMaison;
	}
	
    
	}
