package ci.gstoreplus.dao.dashboard.catalogue;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ci.gstoreplus.entity.catalogue.Document;

public interface DocumentRepository extends JpaRepository<Document, Long>{
	Optional<Document> findByLibelle(String libelle);

}
