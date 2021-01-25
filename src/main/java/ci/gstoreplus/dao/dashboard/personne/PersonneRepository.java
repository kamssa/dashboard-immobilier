package ci.gstoreplus.dao.dashboard.personne;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ci.gstoreplus.entity.dashboard.shared.Personne;

@Repository
public interface PersonneRepository extends JpaRepository<Personne, Long>{
	Optional<Personne> findByEmail(String email);
    List<Personne> findByIdIn(List<Long> userIds);
    boolean existsByEmail(String email);
}
