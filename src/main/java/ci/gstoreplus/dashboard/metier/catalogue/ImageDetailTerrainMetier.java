package ci.gstoreplus.dashboard.metier.catalogue;

import java.util.List;

import ci.gstoreplus.entity.catalogue.ImageDetail;
import ci.gstoreplus.metier.Imetier;

public interface ImageDetailTerrainMetier extends Imetier<ImageDetail, Long>{
	List<ImageDetail> findImageByIdDetailTerrain(Long id);
}
