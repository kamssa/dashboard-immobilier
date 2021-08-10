package ci.gstoreplus.dashboard.metier.catalogue;

import java.util.List;

import ci.gstoreplus.entity.catalogue.ImageDetail;
import ci.gstoreplus.entity.catalogue.ImageDetailFlashMaison;
import ci.gstoreplus.entity.catalogue.ImageDetailMaison;
import ci.gstoreplus.metier.Imetier;

public interface ImageDetailFlashMaisonMetier extends Imetier<ImageDetailFlashMaison, Long>{
	List<ImageDetailFlashMaison> findImageByIdDetailFlash(Long id);}
