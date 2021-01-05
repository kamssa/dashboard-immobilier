package ci.gstoreplus.dashboard.metier.catalogue;

import ci.gstoreplus.entity.catalogue.TerrainAcheter;
import ci.gstoreplus.metier.Imetier;

public interface ProduitAcheterMetier extends Imetier<TerrainAcheter, Long >{
	TerrainAcheter findTerrainAcheteByIdPersonne(long id);

}
