package ci.gstoreplus.entity.client;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

import ci.gstoreplus.entity.dashboard.shared.AbstractEntity;

@Entity
public class DetailMontantVeser extends AbstractEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    
	@Version
	private Long version;
	private LocalDateTime date;
	private String libelle;
	private double montantVerse;
	private double reste;
	private Long clientId;
	
	
	public DetailMontantVeser() {
		super();
		// TODO Auto-generated constructor stub
	}


	public DetailMontantVeser(Long id, Long version, LocalDateTime date, String libelle, double montantVerse,
			double reste, Long clientId) {
		super();
		this.id = id;
		this.version = version;
		this.date = date;
		this.libelle = libelle;
		this.montantVerse = montantVerse;
		this.reste = reste;
		this.clientId = clientId;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Long getVersion() {
		return version;
	}


	public void setVersion(Long version) {
		this.version = version;
	}


	public LocalDateTime getDate() {
		return date;
	}


	public void setDate(LocalDateTime date) {
		this.date = date;
	}


	public String getLibelle() {
		return libelle;
	}


	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}


	public double getMontantVerse() {
		return montantVerse;
	}


	public void setMontantVerse(double montantVerse) {
		this.montantVerse = montantVerse;
	}


	public double getReste() {
		return reste;
	}


	public void setReste(double reste) {
		this.reste = reste;
	}


	public Long getClientId() {
		return clientId;
	}


		
}
