package ci.gstoreplus.dao.dashboard.catalogue;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ci.gstoreplus.entity.catalogue.Maison;
import ci.gstoreplus.entity.catalogue.Terrain;

@Repository
public interface MaisonRepository extends JpaRepository<Maison, Long>{
	Optional<Maison> findByLibelle(String libelle);
	@Query("select m from Maison m  where m.ville.id=?1")
	List<Maison> findMaisonByIdVille(Long id);
}
