package ci.gstoreplus.dashboard.metier.catalogue;

import java.util.List;

import ci.gstoreplus.entity.catalogue.Produit;
import ci.gstoreplus.entity.catalogue.Terrain;
import ci.gstoreplus.metier.Imetier;

public interface ProduitsMetier extends Imetier<Produit, Long>{
	List<Produit> produitRecherche(String type, String libelle, double prix);
	List<Produit> produitGeo();
	public List<Produit> findProduitByVille();

}
