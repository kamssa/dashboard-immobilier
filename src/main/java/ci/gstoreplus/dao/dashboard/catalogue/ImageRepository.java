package ci.gstoreplus.dao.dashboard.catalogue;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ci.gstoreplus.entity.catalogue.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
List<Image> findByOrderById();
}
