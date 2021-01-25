package ci.gstoreplus.dashboard.metier;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ci.gstoreplus.dao.dashboard.personne.PersonneRepository;
import ci.gstoreplus.entity.dashboard.shared.Personne;
import ci.gstoreplus.exception.InvalideImmobilierException;

@Service
public class MembreMetierImpl implements MembreMetier{
	@Autowired
	private PersonneRepository personneRepository;
	

		@Override
		public Personne creer(Personne p) throws InvalideImmobilierException {
			return personneRepository.save(p);
		}


		@Override
		public Personne modifier(Personne entity) throws InvalideImmobilierException {
			return personneRepository.save(entity);
		}

		@Override
		public List<Personne> findAll() {
			List<Personne> pers = null;
			List<Personne> personne = personneRepository.findAll();
		    pers = personne.stream().filter(p -> p.getType().equals("ME")).collect(Collectors.toList());
		    return pers;
		}

		@Override
		public Personne findById(Long id) {
			return personneRepository.findById(id).get();
		}

		@Override
		public boolean supprimer(Long id) {
        personneRepository.deleteById(id);
         return true;
		}

		@Override
		public boolean supprimer(List<Personne> entites) {
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
			return false;
		}

		@Override
		public boolean getMembreByEmail(String email) {
		    personneRepository.existsByEmail(email);
			return true;
		}


		@Override
		public boolean existsByEmail(String email) {
			// TODO Auto-generated method stub
			return false;
		}

}
