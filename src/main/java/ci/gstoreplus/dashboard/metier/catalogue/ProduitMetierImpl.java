package ci.gstoreplus.dashboard.metier.catalogue;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ci.gstoreplus.dao.dashboard.catalogue.ProduitRepository;
import ci.gstoreplus.entity.catalogue.Produit;
import ci.gstoreplus.exception.InvalideImmobilierException;

@Service
public class ProduitMetierImpl implements ProduitMetier{
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
	// TODO Auto-generated method stub
	return false;
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
public Boolean existsByEmail(String email) {
	// TODO Auto-generated method stub
	return null;
}


}
