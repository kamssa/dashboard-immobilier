package ci.gstoreplus.dao.dashboard.catalogue;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ci.gstoreplus.entity.catalogue.Ville;

@Repository
public interface VilleRepository extends JpaRepository<Ville, Long>{
	Optional<Ville> findByLibelle(String libelle);
}
