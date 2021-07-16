package ci.gstoreplus.dashboard.metier.catalogue;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ci.gstoreplus.dao.dashboard.catalogue.ProduitRepository;
import ci.gstoreplus.entity.catalogue.Produit;
import ci.gstoreplus.entity.catalogue.Terrain;
import ci.gstoreplus.exception.InvalideImmobilierException;

@Service
public class ProduitsMetierImpl implements ProduitsMetier{
@Autowired
private ProduitRepository produitRepository;

@Override
public Produit creer(Produit entity) throws InvalideImmobilierException {
	// TODO Auto-generated method stub
	return produitRepository.save(entity);
}

@Override
public Produit modifier(Produit entity) throws InvalideImmobilierException {
	// TODO Auto-generated method stub
	return produitRepository.save(entity);
}

@Override
public List<Produit> findAll() {
	// TODO Auto-generated method stub
	return produitRepository.findAll();
}

@Override
public Produit findById(Long id) {
	// TODO Auto-generated method stub
	return produitRepository.findById(id).get();
}

@Override
public boolean supprimer(Long id) {
    produitRepository.deleteById(id);
	return true;
}

@Override
public boolean supprimer(List<Produit> entites) {
	// TODO Auto-generated method stub
	return false;
}

@Override
public boolean existe(Long id) {
	// TODO Auto-generated method stub
	return false;
}

@Override
public Boolean existsByPseudo(String pseudo) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public boolean existsByEmail(String email) {
	// TODO Auto-generated method stub
	return false;
}

@Override
public List<Produit> produitRecherche(String type, String libelle, double prix) {
	List<Produit> produitRecherches = null;
	List<Produit> resultatRecherches = produitRepository.findAll();
	/*produitRecherches = resultatRecherches.stream()
			.filter(p -> p.getType().equals(type))
			.filter(p -> p.getVille().getLibelle().equals(libelle) )
			.filter(p -> p.getPrix() <= prix).
			limit(50).collect(Collectors.toList());*/

	return produitRecherches;
}

@Override
public List<Produit> produitGeo() {
	List<Produit> produits = null;
	List<Produit> resultat = produitRepository.findAll();
	/*produits = resultat.stream()
			.filter(p -> p.isAbonneGeo()== true)
			.limit(50).collect(Collectors.toList());*/

	return produits;
}

@Override
public List<Produit> findProduitByVille() {
	List<Produit> ters = null;
	List<Produit> terrains = produitRepository.findAll();
    ters = terrains.stream().filter(t -> t.getVille().getLibelle()=="Abidjan").collect(Collectors.toList());
    return ters;
}



}
