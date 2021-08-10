package ci.gstoreplus.dashboard.metier;

import java.util.List;

import ci.gstoreplus.entity.catalogue.DetailFlashMaison;
import ci.gstoreplus.metier.Imetier;

public interface DetailFlashMaisonMetier extends Imetier<DetailFlashMaison, Long>{
	List<DetailFlashMaison> findDetailFashMaisonIdFlashMaison(long id);

}
