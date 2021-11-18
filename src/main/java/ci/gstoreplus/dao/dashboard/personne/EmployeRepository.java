package ci.gstoreplus.dao.dashboard.personne;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ci.gstoreplus.entity.dashboard.admin.Employe;

public interface EmployeRepository extends JpaRepository<Employe, Long>{
	@Query("select e from Employe e  where e.departement.id=?1")
	List<Employe> findEmployeByDepartement(long id);

}
