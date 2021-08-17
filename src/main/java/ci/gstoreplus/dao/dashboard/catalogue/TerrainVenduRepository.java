package ci.gstoreplus.dao.dashboard.catalogue;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ci.gstoreplus.entity.catalogue.TerrainVendu;

public interface TerrainVenduRepository extends JpaRepository<TerrainVendu, Long>{
	// recupere les abonnement par id de espace
			@Query("select t from TerrainVendu t  where t.personne.id=?1")
			 TerrainVendu findTerrainVenduByIdPersonne(long id);
}
