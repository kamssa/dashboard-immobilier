package ci.gstoreplus.dao.dashboard.catalogue;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ci.gstoreplus.entity.catalogue.DetailFlashMaison;


public interface DetailFlashMaisonRepository extends JpaRepository<DetailFlashMaison, Long>{
	@Query("select d from DetailFlashMaison d  where d.flashMaison.id=?1")
	List<DetailFlashMaison> findDetailFashMaisonIdFlashMaison(long id);
	Optional<DetailFlashMaison> findByLibelle(String libelle);

}
