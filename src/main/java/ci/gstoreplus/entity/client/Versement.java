package ci.gstoreplus.entity.client;

import java.time.LocalDateTime;

import javax.persistence.Entity;

import ci.gstoreplus.entity.dashboard.shared.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Versement extends AbstractEntity {
	private static final long serialVersionUID = 1L;

	private LocalDateTime date;
	private String libelle;
	private double solde;
	private double reste;
	
	
}
