package ci.gstoreplus.dao.dashboard.personne;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ci.gstoreplus.entity.dashboard.shared.Personne;

@Repository
public interface PersonneRepository extends JpaRepository<Personne, Long>{
	Optional<Personne> findByEmail(String email);
    List<Personne> findByIdIn(List<Long> userIds);
    boolean existsByEmail(String email);
 // recupere une personne par son type et son nomComplet
 	List<Personne> findByNomCompletLike(String mc);
 	Personne findByNumCni(String numCni);
 // recupere une personne par son type et son nomComplet
 	@Query("select p from Personne p where p.nomComplet like %:nomComplet%")
 	List<Personne> findAllPersonnesParMc(@Param("nomComplet")String mc);

}
