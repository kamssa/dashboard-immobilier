package ci.gstoreplus.entity.client;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import ci.gstoreplus.entity.dashboard.admin.Employe;
import ci.gstoreplus.entity.dashboard.shared.AbstractEntity;
import ci.gstoreplus.entity.dashboard.shared.Personne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class DetailVersement extends AbstractEntity {
	private static final long serialVersionUID = 1L;

	private LocalDateTime date;
	private double montantVerse;
	@ManyToOne(fetch = FetchType.LAZY, cascade =CascadeType.MERGE)
	private Versement versement;
	@ManyToOne(fetch = FetchType.LAZY, cascade =CascadeType.MERGE)
	private Personne personne;
	@ManyToOne(fetch = FetchType.LAZY, cascade =CascadeType.MERGE)
	private Employe employe;
	
	}
