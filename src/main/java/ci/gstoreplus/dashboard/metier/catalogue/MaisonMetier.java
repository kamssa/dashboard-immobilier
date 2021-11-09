package ci.gstoreplus.dashboard.metier.catalogue;

import java.util.List;

import ci.gstoreplus.entity.catalogue.Maison;
import ci.gstoreplus.metier.Imetier;

public interface MaisonMetier extends Imetier<Maison, Long>{
	List<Maison> findMaisonByIdVille(Long id);
	List<Maison> recherchePrixMax(String type, String ville, double prix);
}
