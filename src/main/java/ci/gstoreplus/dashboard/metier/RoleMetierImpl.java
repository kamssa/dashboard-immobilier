package ci.gstoreplus.dashboard.metier;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ci.gstoreplus.dao.dashboard.personne.RoleRepository;
import ci.gstoreplus.entity.dashboard.shared.Role;
import ci.gstoreplus.entity.dashboard.shared.RoleName;
import ci.gstoreplus.exception.InvalideImmobilierException;


@Service
public class RoleMetierImpl implements IRoleMetier{
@Autowired
private RoleRepository roleRepository;
	@Override
	public Role creer(Role entity) throws InvalideImmobilierException {
		// TODO Auto-generated method stub
		return roleRepository.save(entity);
	}

	@Override
	public Role modifier(Role entity) throws InvalideImmobilierException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Role> findAll() {
		// TODO Auto-generated method stub
		return roleRepository.findAll();
	}

	@Override
	public Role findById(Long id) {
		// TODO Auto-generated method stub
		return roleRepository.findById(id).get();
	}

	@Override
	public boolean supprimer(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean supprimer(List<Role> entites) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean existe(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Boolean existsByPseudo(String pseudo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existsByEmail(String email) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Optional<Role> findByName(RoleName roleName) {
		// TODO Auto-generated method stub
		return roleRepository.findByName(roleName);
	}

	@Override
	public Optional<Role> findByRoleName(String roleName) {
		// TODO Auto-generated method stub
		return roleRepository.findByRoleName(roleName);
	}

}
