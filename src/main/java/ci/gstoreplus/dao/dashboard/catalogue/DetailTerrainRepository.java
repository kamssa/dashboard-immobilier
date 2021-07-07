package ci.gstoreplus.dao.dashboard.catalogue;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ci.gstoreplus.entity.catalogue.DetailTerrain;

public interface DetailTerrainRepository extends JpaRepository<DetailTerrain, Long>{
	@Query("select d from DetailTerrain d  where d.terrain.id=?1")
	DetailTerrain findDetailTerrainIdTerrain(long id);
}
