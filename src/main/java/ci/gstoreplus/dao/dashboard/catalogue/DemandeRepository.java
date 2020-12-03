package ci.gstoreplus.dao.dashboard.catalogue;

import org.springframework.data.jpa.repository.JpaRepository;

import ci.gstoreplus.entity.catalogue.Demande;

public interface DemandeRepository extends JpaRepository<Demande, Long> {

}
