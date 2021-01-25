package ci.gstoreplus.dao.dashboard.catalogue;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ci.gstoreplus.entity.catalogue.Produit;

public interface ProduitRepository extends JpaRepository<Produit, Long>{
	
}
