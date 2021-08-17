package ci.gstoreplus.dao.dashboard.catalogue;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ci.gstoreplus.entity.catalogue.Produit;
import ci.gstoreplus.entity.catalogue.Terrain;

public interface ProduitRepository extends JpaRepository<Produit, Long>{
	Optional<Produit> findByLibelle(String libelle);
}
