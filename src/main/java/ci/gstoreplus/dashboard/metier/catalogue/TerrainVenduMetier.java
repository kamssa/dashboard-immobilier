package ci.gstoreplus.dashboard.metier.catalogue;

import ci.gstoreplus.entity.catalogue.TerrainVendu;
import ci.gstoreplus.metier.Imetier;

public interface TerrainVenduMetier extends Imetier<TerrainVendu, Long> {
	TerrainVendu findTerrainVenduByIdPersonne(long id);

}
