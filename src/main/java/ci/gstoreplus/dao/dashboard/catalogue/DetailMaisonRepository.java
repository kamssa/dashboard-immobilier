package ci.gstoreplus.dao.dashboard.catalogue;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ci.gstoreplus.entity.catalogue.DetailMaison;


public interface DetailMaisonRepository extends JpaRepository<DetailMaison, Long>{
	@Query("select d from DetailMaison d  where d.maison.id=?1")
	List<DetailMaison> findDetailMaisonIdMaison(long id);
}
