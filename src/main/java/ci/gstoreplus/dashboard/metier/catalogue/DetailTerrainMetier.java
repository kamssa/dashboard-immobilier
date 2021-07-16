package ci.gstoreplus.dashboard.metier.catalogue;

import java.util.List;

import ci.gstoreplus.entity.catalogue.DetailTerrain;
import ci.gstoreplus.metier.Imetier;

public interface DetailTerrainMetier extends Imetier<DetailTerrain, Long>{
	List<DetailTerrain> findDetailTerrainIdTerrain(long id);
}
