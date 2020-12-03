package ci.gstoreplus.entity.catalogue;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import ci.gstoreplus.entity.dashboard.shared.AbstractEntity;
import ci.gstoreplus.entity.dashboard.shared.Personne;

public class AutreTerrain extends AbstractEntity{

		private static final long serialVersionUID = 1L;
		private String libelle;
		private String description;
		private String path;
		@ManyToOne(fetch = FetchType.LAZY, cascade =CascadeType.MERGE)
		private Personne personne;
		public AutreTerrain() {
			super();
			// TODO Auto-generated constructor stub
		}
		public AutreTerrain(String libelle, String description, String path, Personne personne) {
			super();
			this.libelle = libelle;
			this.description = description;
			this.path = path;
			this.personne = personne;
		}
		public String getLibelle() {
			return libelle;
		}
		public void setLibelle(String libelle) {
			this.libelle = libelle;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public String getPath() {
			return path;
		}
		public void setPath(String path) {
			this.path = path;
		}
		public Personne getPersonne() {
			return personne;
		}
		public void setPersonne(Personne personne) {
			this.personne = personne;
		}
		
		
	}
