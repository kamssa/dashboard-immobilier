package ci.gstoreplus.dao.dashboard.catalogue;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ci.gstoreplus.entity.catalogue.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
List<Image> findByOrderById();
//recupere les abonnement par id de espace
	@Query("select image from Image image  where image.idTerrain=?1")
	Image findImageByIdTerrain(Long id);
	
}
