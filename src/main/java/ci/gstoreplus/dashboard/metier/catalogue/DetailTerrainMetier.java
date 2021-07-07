package ci.gstoreplus.dashboard.metier.catalogue;

import ci.gstoreplus.entity.catalogue.DetailTerrain;
import ci.gstoreplus.metier.Imetier;

public interface DetailTerrainMetier extends Imetier<DetailTerrain, Long>{
	DetailTerrain findDetailTerrainIdTerrain(long id);
}
