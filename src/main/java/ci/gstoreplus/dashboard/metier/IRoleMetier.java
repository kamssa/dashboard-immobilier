package ci.gstoreplus.dashboard.metier;

import java.util.Optional;

import ci.gstoreplus.entity.dashboard.shared.Role;
import ci.gstoreplus.entity.dashboard.shared.RoleName;
import ci.gstoreplus.metier.Imetier;


public interface IRoleMetier  extends Imetier<Role, Long>{
	Optional<Role> findByName(RoleName roleName);

}
