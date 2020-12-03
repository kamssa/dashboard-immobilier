package ci.gstoreplus.dao.dashboard.personne;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ci.gstoreplus.entity.dashboard.admin.Departement;

@Repository
public interface DepartementRepository extends JpaRepository<Departement, Long>{

}
