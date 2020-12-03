package ci.gstoreplus.dao.dashboard.catalogue;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ci.gstoreplus.entity.catalogue.Terrain;

@Repository
public interface TerrainRepository extends JpaRepository<Terrain, Long>{
	Optional<Terrain> findByLibelle(String libelle);
	@Query("select t from Terrain t  where t.ville.id=?1")
	List<Terrain> findTerrainByIdVille(Long id);
	
}
