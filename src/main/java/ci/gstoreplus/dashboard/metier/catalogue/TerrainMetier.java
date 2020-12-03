package ci.gstoreplus.dashboard.metier.catalogue;

import java.util.List;
import java.util.Optional;

import ci.gstoreplus.entity.catalogue.Terrain;
import ci.gstoreplus.metier.Imetier;

public interface TerrainMetier extends Imetier<Terrain, Long> {
	Optional<Terrain> findByLibelle(String libelle);
	List<Terrain> findTerrainByIdVille(Long id);
	List<Terrain> recherchePrixMax(String type, String ville, double prix);

}
