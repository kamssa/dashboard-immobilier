package ci.gstoreplus.dashboard.metier;

import java.util.List;

import ci.gstoreplus.entity.catalogue.Demande;
import ci.gstoreplus.metier.Imetier;

public interface DemandeMetier extends Imetier<Demande, Long>{
List<Demande> demandeParIdPersonne(Long id);
}
