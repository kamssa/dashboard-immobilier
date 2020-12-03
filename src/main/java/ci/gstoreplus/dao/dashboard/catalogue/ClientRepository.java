package ci.gstoreplus.dao.dashboard.catalogue;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ci.gstoreplus.entity.client.Client;
import ci.gstoreplus.entity.dashboard.shared.Personne;

@Repository
public interface ClientRepository extends JpaRepository<Personne, Long> {

}
