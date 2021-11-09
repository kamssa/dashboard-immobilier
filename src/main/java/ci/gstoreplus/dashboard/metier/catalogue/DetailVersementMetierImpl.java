package ci.gstoreplus.dashboard.metier.catalogue;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ci.gstoreplus.dao.dashboard.catalogue.DetailVersementRepository;
import ci.gstoreplus.dao.dashboard.catalogue.TerrainVenduRepository;
import ci.gstoreplus.dao.dashboard.catalogue.VersementRepository;
import ci.gstoreplus.entity.catalogue.TerrainVendu;
import ci.gstoreplus.entity.client.DetailVersement;
import ci.gstoreplus.entity.client.Versement;
import ci.gstoreplus.exception.InvalideImmobilierException;

@Service
public class DetailVersementMetierImpl implements DetailVersementMetier{
@Autowired
private DetailVersementRepository dversementRepository;
@Autowired
private VersementRepository versementRepository;
@Autowired
private TerrainVenduRepository terrainVenduRepository;
	@Override
	public DetailVersement creer(DetailVersement entity) throws InvalideImmobilierException {
		double solde = 0;
		double prix =0;
		double reste = 0;
		Versement ve = null;
	System.out.println("Voir le detail Versement"+ entity);
		List<TerrainVendu> terrianVendus=terrainVenduRepository.findTerrainVenduByIdPersonne(entity.getPersonne().getId());
		for (TerrainVendu terrainVendu : terrianVendus) {
		 prix +=terrainVendu.getPrix();
		}
		List<DetailVersement> dv = detailVersementByIdPersonne(entity.getPersonne().getId());
		if(dv.isEmpty()) {
			Versement v = new Versement();
			solde = entity.getMontantVerse();
			v.setSolde(solde);
			reste = prix - entity.getMontantVerse();
			v.setReste(reste);
			Versement versment =versementRepository.save(v);
			entity.setVersement(versment);
		}else {
			
			for (DetailVersement detailVersement : dv) {
				solde += detailVersement.getMontantVerse();
				 ve = detailVersement.getVersement();
				}
			solde = solde + entity.getMontantVerse();
			reste= prix - solde;
			ve.setSolde(solde);
			ve.setReste(reste);
			entity.setVersement(ve);
		} 
		
		return dversementRepository.save(entity);

	}

	@Override
	public DetailVersement modifier(DetailVersement entity) throws InvalideImmobilierException {
		double solde = 0;
		double reste = 0;
		Versement ve = null;
		DetailVersement detailV = null;
		  detailV = dversementRepository.findById(entity.getId()).get();
			System.out.println(" detail v:" + detailV);

		  ve = entity.getVersement();
		  solde = ve.getSolde() + entity.getMontantVerse() - detailV.getMontantVerse();
			ve.setSolde(solde);
			reste = ve.getReste() - entity.getMontantVerse() + detailV.getMontantVerse();
			ve.setReste(reste);
			entity.setVersement(ve);
		 
		
		return dversementRepository.save(entity);
	}

	@Override
	public List<DetailVersement> findAll() {
		return dversementRepository.findAll();
	}

	@Override
	public DetailVersement findById(Long id) {
		return dversementRepository.findById(id).get();
	}

	@Override
	public boolean supprimer(Long id) {
		double solde = 0;
		double prix =0;
		double reste = 0;
		Versement ve = null;
		DetailVersement dvt = null;
		double montant = 0;
	    dvt=  dversementRepository.findById(id).get();
        List<DetailVersement> dv = detailVersementByIdPersonne(dvt.getPersonne().getId());
		for (DetailVersement detailVersement : dv) {
				solde += detailVersement.getMontantVerse();
				 ve = detailVersement.getVersement();
				}
			solde = solde - dvt.getMontantVerse();
			montant = ve.getReste()+ dvt.getMontantVerse();
			reste= montant;
			System.out.println("Voir reste"+ reste);

			ve.setSolde(solde);
			ve.setReste(reste);
			dvt.setVersement(ve);
			dversementRepository.save(dvt);
		
		    dversementRepository.deleteById(id);
		
		return true;
	}

	@Override
	public boolean supprimer(List<DetailVersement> entites) {
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
	public List<DetailVersement> detailVersementByIdVersement(Long id) {
		// TODO Auto-generated method stub
		return dversementRepository.detailVersementByIdVersement(id);
	}

	@Override
	public List<DetailVersement> detailVersementByIdPersonne(Long id) {
		// TODO Auto-generated method stub
		return dversementRepository.detailVersementByIdPersonne(id);
	}

}
