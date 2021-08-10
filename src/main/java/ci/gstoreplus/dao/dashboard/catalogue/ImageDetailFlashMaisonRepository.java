package ci.gstoreplus.dao.dashboard.catalogue;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ci.gstoreplus.entity.catalogue.ImageDetailFlashMaison;
import ci.gstoreplus.entity.catalogue.ImageDetailMaison;

public interface ImageDetailFlashMaisonRepository extends JpaRepository<ImageDetailFlashMaison, Long>{
	//recupere l'image par id de article
	@Query("select image from ImageDetailFlashMaison image  where image.detailFashMaison.id=?1")
	List<ImageDetailFlashMaison> findImageByIdDetailFlash(Long id);
}
