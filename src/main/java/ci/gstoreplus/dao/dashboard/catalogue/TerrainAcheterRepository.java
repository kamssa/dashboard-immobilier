package ci.gstoreplus.dao.dashboard.catalogue;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ci.gstoreplus.entity.catalogue.TerrainAcheter;

@Repository
public interface TerrainAcheterRepository extends JpaRepository<TerrainAcheter, Long>{
	// recupere les abonnement par id de espace
		@Query("select t from TerrainAcheter t  where t.personne.id=?1")
		TerrainAcheter findTerrainAcheteByIdPersonne(long id);
}
