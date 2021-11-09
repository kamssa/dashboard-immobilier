package ci.gstoreplus.dao.dashboard.catalogue;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ci.gstoreplus.entity.client.DetailVersement;
import ci.gstoreplus.entity.client.Versement;


public interface DetailVersementRepository extends JpaRepository<DetailVersement, Long>{
	@Query("select dv from DetailVersement dv  where dv.versement.id=?1")
	List<DetailVersement> detailVersementByIdVersement(Long id);
	@Query("select dv from DetailVersement dv  where dv.personne.id=?1")
	List<DetailVersement> detailVersementByIdPersonne(Long id);
	
}
