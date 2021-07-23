package ci.gstoreplus.dao.dashboard.catalogue;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import ci.gstoreplus.entity.catalogue.ImageDetailMaison;

public interface ImageDetailMaisonRepository extends JpaRepository<ImageDetailMaison, Long>{
	//recupere l'image par id de article
	@Query("select image from ImageDetailMaison image  where image.detailMaison.id=?1")
	List<ImageDetailMaison> findImageByIdDetailMaisonn(Long id);
}
