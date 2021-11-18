package ci.gstoreplus.dashboard.metier;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ci.gstoreplus.dao.dashboard.personne.DepartementRepository;
import ci.gstoreplus.dao.dashboard.personne.EntrepriseRepository;
import ci.gstoreplus.entity.dashboard.admin.Departement;
import ci.gstoreplus.entity.dashboard.admin.Entreprise;
import ci.gstoreplus.exception.InvalideImmobilierException;



@Service
public class DepartementMetierImpl implements IDepartementMetier{
@Autowired
private DepartementRepository departementRepository;
@Autowired
private EntrepriseRepository entrepriseRepository;
	@Override
	public Departement creer(Departement entity) throws InvalideImmobilierException {
		
        Entreprise entreprise = entrepriseRepository.findByNom("GSTORE PLUS SARL").get();
        System.out.println("Voir entreprise"+entreprise);
        entity.setEntreprise(entreprise);
		System.out.println("Voir departement"+entity);

		return departementRepository.save(entity);
	}
  
	@Override
	public Departement modifier(Departement entity) throws InvalideImmobilierException {
		// TODO Auto-generated method stub
		
		 Entreprise entreprise = entrepriseRepository.findByNom("GSTORE PLUS SARL").get();
	        System.out.println("Voir entreprise:"+entreprise);
	        entity.setEntreprise(entreprise);
	        System.out.println("Voir departement modif:"+entity);
		return departementRepository.save(entity);
	}

	@Override
	public List<Departement> findAll() {
		// TODO Auto-generated method stub
		return departementRepository.findAll();
	}

	@Override
	public Departement findById(Long id) {
		// TODO Auto-generated method stub
		return departementRepository.findById(id).get();
	}

	@Override
	public boolean supprimer(Long id) {
    departementRepository.deleteById(id);
		return true;
	}

	@Override
	public boolean supprimer(List<Departement> entites) {
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

}
