package ci.gstoreplus.dao.dashboard.catalogue;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ci.gstoreplus.entity.catalogue.Categorie;

public interface CatalogueRepository extends JpaRepository<Categorie, Long>{
	Optional<Categorie> findByNom(String nom);

}
