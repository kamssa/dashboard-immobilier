package ci.gstoreplus.dao.dashboard.catalogue;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ci.gstoreplus.entity.catalogue.DetailTerrain;

public interface DetailTerrainRepository extends JpaRepository<DetailTerrain, Long>{
	@Query("select d from DetailTerrain d  where d.terrain.id=?1")
	List<DetailTerrain> findDetailTerrainIdTerrain(long id);
	Optional<DetailTerrain> findByLibelle(String libelle);

}
