package ci.gstoreplus.dao.dashboard.catalogue;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ci.gstoreplus.entity.catalogue.FlashTerrain;

public interface FlashTerrainRepository extends JpaRepository<FlashTerrain, Long>{
	Optional<FlashTerrain> findByLibelle(String libelle);

}
