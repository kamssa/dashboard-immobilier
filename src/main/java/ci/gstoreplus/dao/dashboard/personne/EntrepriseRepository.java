package ci.gstoreplus.dao.dashboard.personne;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ci.gstoreplus.entity.dashboard.admin.Entreprise;

public interface EntrepriseRepository extends JpaRepository<Entreprise, Long> {
	Optional<Entreprise> findByNom(String nom);

}
