package ci.gstoreplus.dao.dashboard.catalogue;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ci.gstoreplus.entity.catalogue.FlashMaison;

public interface FlashMaisonRepository extends JpaRepository<FlashMaison, Long>{
	Optional<FlashMaison> findByLibelle(String libelle);

}
