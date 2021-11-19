package ci.gstoreplus.dao.dashboard.personne;

import org.springframework.data.jpa.repository.JpaRepository;

import ci.gstoreplus.entity.client.Prospects;

public interface ProspectRepository extends JpaRepository<Prospects, Long>{

}
