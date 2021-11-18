package ci.gstoreplus.dashboard.metier.catalogue;

import java.util.List;

import ci.gstoreplus.entity.catalogue.TerrainVendu;
import ci.gstoreplus.metier.Imetier;

public interface TerrainVenduMetier extends Imetier<TerrainVendu, Long> {
	 List<TerrainVendu> findTerrainVenduByIdPersonne(long id);
	 public List<TerrainVendu> findAllTerrainAbonneGeo();

}
