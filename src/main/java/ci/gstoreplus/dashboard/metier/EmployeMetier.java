package ci.gstoreplus.dashboard.metier;


import java.util.List;

import ci.gstoreplus.entity.dashboard.admin.Employe;
import ci.gstoreplus.entity.dashboard.shared.Personne;
import ci.gstoreplus.metier.Imetier;

public interface EmployeMetier extends Imetier<Employe, Long>{
	List<Employe> findEmployeByDepartement(long id);

}
