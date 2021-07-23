package ci.gstoreplus.dashboard.metier.catalogue;

import java.util.List;

import ci.gstoreplus.entity.catalogue.DetailMaison;
import ci.gstoreplus.metier.Imetier;

public interface DetailMaisonMetier extends Imetier<DetailMaison, Long>{
	List<DetailMaison> findDetailMaisonIdMaison(long id);
}
