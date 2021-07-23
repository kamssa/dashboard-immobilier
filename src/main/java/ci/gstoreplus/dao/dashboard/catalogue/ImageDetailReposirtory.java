package ci.gstoreplus.dao.dashboard.catalogue;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ci.gstoreplus.entity.catalogue.ImageDetail;

public interface ImageDetailReposirtory extends JpaRepository<ImageDetail, Long>{
	List<ImageDetail> findByOrderById();
	//recupere l'image par id de article
	@Query("select image from ImageDetail image  where image.detailTerrain.id=?1")
		List<ImageDetail> findImageByIdDetailTerrain(Long id);	
}
