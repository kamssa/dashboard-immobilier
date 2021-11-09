package ci.gstoreplus.dashboard.metier.catalogue;

import java.util.List;

import ci.gstoreplus.entity.client.DetailVersement;
import ci.gstoreplus.metier.Imetier;

public interface DetailVersementMetier extends Imetier<DetailVersement, Long>{
	List<DetailVersement> detailVersementByIdVersement(Long id);
	List<DetailVersement> detailVersementByIdPersonne(Long id);

	
}
