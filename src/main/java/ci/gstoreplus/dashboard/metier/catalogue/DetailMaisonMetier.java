package ci.gstoreplus.dashboard.metier.catalogue;

import ci.gstoreplus.entity.catalogue.DetailMaison;
import ci.gstoreplus.metier.Imetier;

public interface DetailMaisonMetier extends Imetier<DetailMaison, Long>{
	DetailMaison findDetailMaisonIdMaison(long id);
}
