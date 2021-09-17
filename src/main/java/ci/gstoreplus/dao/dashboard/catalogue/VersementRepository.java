package ci.gstoreplus.dao.dashboard.catalogue;

import org.springframework.data.jpa.repository.JpaRepository;

import ci.gstoreplus.entity.catalogue.Versement;

public interface VersementRepository extends JpaRepository<Versement, Long>{

}
