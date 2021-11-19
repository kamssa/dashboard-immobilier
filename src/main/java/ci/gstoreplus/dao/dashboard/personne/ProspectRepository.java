package ci.gstoreplus.dao.dashboard.personne;

import org.springframework.data.jpa.repository.JpaRepository;

import ci.gstoreplus.entity.client.Prospect;
import ci.gstoreplus.entity.dashboard.shared.Personne;

public interface ProspectRepository extends JpaRepository<Prospect, Long>{

}
