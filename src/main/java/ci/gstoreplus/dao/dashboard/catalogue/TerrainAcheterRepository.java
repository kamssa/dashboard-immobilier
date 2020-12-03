package ci.gstoreplus.dao.dashboard.catalogue;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ci.gstoreplus.entity.catalogue.TerrainAcheter;

@Repository
public interface TerrainAcheterRepository extends JpaRepository<TerrainAcheter, Long>{

}
