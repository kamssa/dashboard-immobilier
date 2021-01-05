package ci.gstoreplus.dao.dashboard.catalogue;

import org.springframework.data.jpa.repository.JpaRepository;

import ci.gstoreplus.entity.catalogue.Produit;

public interface ProduitRepository extends JpaRepository<Produit, Long>{

}
