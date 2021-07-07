package ci.gstoreplus.dashboard.metier.catalogue;

import java.util.List;

import ci.gstoreplus.entity.catalogue.ImageDetail;
import ci.gstoreplus.entity.catalogue.ImageDetailMaison;
import ci.gstoreplus.metier.Imetier;

public interface ImageDetailMaisonMetier extends Imetier<ImageDetailMaison, Long>{
	List<ImageDetailMaison> findImageByIdDetailMaisonn(Long id);
}
