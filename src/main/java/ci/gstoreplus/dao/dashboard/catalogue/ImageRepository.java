package ci.gstoreplus.dao.dashboard.catalogue;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ci.gstoreplus.entity.catalogue.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
	List<Image> findByOrderById();

	@Query("select image from Image image  where image.idProduit=?1")
	Image findImageByIdProduit(Long id);

}
