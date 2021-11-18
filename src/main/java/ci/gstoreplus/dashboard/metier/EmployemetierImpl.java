package ci.gstoreplus.dashboard.metier;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ci.gstoreplus.dao.dashboard.personne.EmployeRepository;
import ci.gstoreplus.dao.dashboard.personne.RoleRepository;
import ci.gstoreplus.entity.dashboard.admin.Employe;
import ci.gstoreplus.entity.dashboard.shared.Role;
import ci.gstoreplus.entity.dashboard.shared.RoleName;
import ci.gstoreplus.exception.InvalideImmobilierException;

@Service
public class EmployemetierImpl implements EmployeMetier{
@Autowired
private EmployeRepository employeRepository;
@Autowired
private RoleRepository roleRepository;
@Autowired
PasswordEncoder passwordEncoder;
	@Override
	public Employe creer(Employe entity) throws InvalideImmobilierException {
		System.out.println("voir le client--->"+entity);
		if(entity.getDepartement()!= null) {
			entity.setPassword(passwordEncoder.encode(entity.getPassword()));
			
		}else {
			throw new InvalideImmobilierException("Le departement ne doit pas etre null");
		}
		
		return employeRepository.save(entity);
	}

	@Override
	public Employe modifier(Employe modif) throws InvalideImmobilierException {
		Optional<Employe> personne = employeRepository.findById(modif.getId());
		if (modif.getDepartement()== null) {

			modif.setDepartement(personne.get().getDepartement());

		} 

		modif.setPassword(passwordEncoder.encode(modif.getPassword()));
		Role userRole = roleRepository.findByName(RoleName.ROLE_EMPLOYE).get();
		modif.setRoles(Collections.singleton(userRole));
		return employeRepository.save(modif);
	}

	@Override
	public List<Employe> findAll() {
		return employeRepository.findAll();
	}

	@Override
	public Employe findById(Long id) {
		// TODO Auto-generated method stub
		return employeRepository.findById(id).get();
	}

	@Override
	public boolean supprimer(Long id) {
		employeRepository.deleteById(id);
		return true;
	}

	@Override
	public boolean supprimer(List<Employe> entites) {
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
	public List<Employe> findEmployeByDepartement(long id) {
		// TODO Auto-generated method stub
		return employeRepository.findEmployeByDepartement(id);
	}

}
