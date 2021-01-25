package ci.gstoreplus.dashboard.metier;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ci.gstoreplus.dao.dashboard.personne.VerificationTokenRepository;
import ci.gstoreplus.entity.dashboard.shared.VerificationToken;
import ci.gstoreplus.exception.InvalideImmobilierException;

@Service
public class VerificationTokenMetierIimpl implements VerificationTokenMetier{
@Autowired
private VerificationTokenRepository verificationTokenRepository;
	@Override
	public VerificationToken creer(VerificationToken entity) throws InvalideImmobilierException {
		// TODO Auto-generated method stub
		return verificationTokenRepository.save(entity);
	}

	@Override
	public VerificationToken modifier(VerificationToken entity) throws InvalideImmobilierException {
		// TODO Auto-generated method stub
		return verificationTokenRepository.save(entity);
	}

	@Override
	public List<VerificationToken> findAll() {
		// TODO Auto-generated method stub
		return verificationTokenRepository.findAll();
	}

	@Override
	public VerificationToken findById(Long id) {
		// TODO Auto-generated method stub
		return verificationTokenRepository.findById(id).get();
	}

	@Override
	public boolean supprimer(Long id) {
		verificationTokenRepository.deleteById(id);
		return true;
	}

	@Override
	public boolean supprimer(List<VerificationToken> entites) {
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
